package com.ptit.ncovihdv.service.impl;

import com.google.firebase.messaging.BatchResponse;
import com.google.gson.Gson;
import com.ptit.ncovihdv.config.PropertiesConfig;
import com.ptit.ncovihdv.dto.request.SendMultiDevicesRequest;
import com.ptit.ncovihdv.dto.request.UpdateTokenDeviceRequest;
import com.ptit.ncovihdv.dto.response.crawl.corona.CoronaApiResponse;
import com.ptit.ncovihdv.dto.response.crawl.corona.Timeline;
import com.ptit.ncovihdv.exception.ApplicationException;
import com.ptit.ncovihdv.model.Account;
import com.ptit.ncovihdv.model.Notification;
import com.ptit.ncovihdv.model.User;
import com.ptit.ncovihdv.repository.AccountRepository;
import com.ptit.ncovihdv.repository.NotificationRepository;
import com.ptit.ncovihdv.repository.UserRepository;
import com.ptit.ncovihdv.service.NotificationService;
import com.ptit.ncovihdv.util.common.CallService;
import com.ptit.ncovihdv.util.common.Constant;
import com.ptit.ncovihdv.util.common.DateUtil;
import com.ptit.ncovihdv.util.common.NotificationMessage;
import com.ptit.ncovihdv.util.common.type.NotificationStatus;
import com.ptit.ncovihdv.util.common.type.NotificationType;
import com.ptit.ncovihdv.util.common.type.StatusEnum;
import com.ptit.ncovihdv.util.firebase.FCMService;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 04-Jun-2020
 */
@Log4j2
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FCMService fcmService;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    PropertiesConfig propertiesConfig;

    @Override
    public void updateTokenDevice(UpdateTokenDeviceRequest request) throws ApplicationException {
        try {
            String username = Constant.getCurrentUser();
            Account accountToken = accountRepository.findAccountByUsernameAndStatus(username, StatusEnum.ACTIVE.getValue());
            if (ObjectUtils.isEmpty(accountToken)) {
                throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
            }
            User user = accountToken.getUserByUserId();
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException(ApplicationCode.ERROR, ApplicationCode.USER_NOT_FOUND_MSG);
            }
            user.setUserDeviceToken(request.getDeviceToken());
            userRepository.save(user);
        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException(ApplicationCode.ERROR, e.toString());
        }
    }

    //job gui notify, hien tai chi co gui all
    @Scheduled(initialDelay = 1000, fixedDelayString = "${time.delay.notification}")
    public void notificationJob() {
        System.out.println("Notification job, start time: " + LocalDateTime.now());
        List<Notification> listNotification = new ArrayList<>();
        try {
            listNotification = notificationRepository.findNotificationWaiting(NotificationStatus.WAIT.getValue(), NotificationType.ALL.getValue());
            if (listNotification.isEmpty()) {
                System.out.println("No notification found");
                return;
            }
            //lay danh sach user co device token
            List<User> listUser = userRepository.findUserHaveDeviceToken();
            if (listUser.isEmpty()) {
                System.out.println("No user found");
                return;
            }
            //lay danh sach token
            List<String> listToken = new ArrayList<>();
            for (User user : listUser) {
                listToken.add(user.getUserDeviceToken());
            }
            if (listToken.isEmpty()) {
                System.out.println("No token found");
                return;
            }
            for (Notification notification : listNotification) {
                SendMultiDevicesRequest request = new SendMultiDevicesRequest();
                request.setBody(notification.getNotificationBody());
                request.setData(notification.getNotificationData());
                request.setTitle(notification.getNotificationTitle());
                request.setTokens(listToken);
                //send notify
                BatchResponse response = fcmService.sendMultiDevices(request);
                log.error(response);

                notification.setNotificationStatus(NotificationStatus.DONE.getValue());
                notification.setNotificationSendAt(LocalDateTime.now());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (!CollectionUtils.isEmpty(listNotification)) {
                notificationRepository.saveAll(listNotification);
            }
        }
        System.out.println("Notification job, done time: " + LocalDateTime.now());
    }

    @Scheduled(initialDelay = 1000, fixedDelayString = "${time.delay.crawldata}")
    public void crawlDataJob() {
        System.out.println("Crawl data job, start time: " + LocalDateTime.now());
        Gson gson = new Gson();
        List<Notification> listNotification = new ArrayList<>();
        try {
            //kiem tra job da tao notify ngay hom nay chua
            List<Notification> notificationCurrentDate = notificationRepository.findNotificationCurrentDate();
            if (!CollectionUtils.isEmpty(notificationCurrentDate)) {
                System.out.println("Da tao notification");
                return;
            }
            //corona api
            String responseCoronaApi = CallService.callGet(propertiesConfig.getCoronaCrawlApiCoronaApi());
            JSONObject jsonObjectCoronaApi = new JSONObject(responseCoronaApi);
            CoronaApiResponse coronaApiResponse = gson.fromJson(jsonObjectCoronaApi.getJSONObject("data").toString(), CoronaApiResponse.class);

            List<Timeline> listTimeline = coronaApiResponse.getTimeline();
            Timeline day1 = listTimeline.get(0);
            Timeline day7 = listTimeline.get(6);
            LocalDate day1Date = DateUtil.formatStringToLocalDate(day1.getDate());
            LocalDate day7Date = DateUtil.formatStringToLocalDate(day7.getDate());

            if (day1Date.compareTo(LocalDate.now()) == 0) {
                //notify ca nhiem moi, hoi phuc, tu vong
                if (day1.getNewConfirmed() != 0) {
                    createNotification(listNotification, NotificationMessage.NEW_CONFIRM_TITLE, String.format(NotificationMessage.NEW_CONFIRM_BODY, day1.getNewConfirmed()), "",
                            NotificationStatus.WAIT.getValue(), NotificationType.ALL.getValue(), null, LocalDateTime.now().plusMinutes(20));
                }
                if (day1.getNewRecovered() != 0) {
                    createNotification(listNotification, NotificationMessage.NEW_RECOVERED_TITLE, String.format(NotificationMessage.NEW_RECOVERED_BODY, day1.getNewRecovered()), "",
                            NotificationStatus.WAIT.getValue(), NotificationType.ALL.getValue(), null, LocalDateTime.now().plusMinutes(35));
                }
                if (day1.getNewDeaths() != 0) {
                    createNotification(listNotification, NotificationMessage.NEW_DEATH_TITLE, String.format(NotificationMessage.NEW_DEATH_BODY, day1.getNewDeaths()), "",
                            NotificationStatus.WAIT.getValue(), NotificationType.ALL.getValue(), null, LocalDateTime.now().plusMinutes(50));
                }
                //notify thong ke
                String reportBody = String.format(NotificationMessage.REPORT_BODY, DateUtil.formatLocalDateToString(day1Date),
                        day1.getConfirmed(), day1.getRecovered(), day1.getActive(), day1.getDeaths());
                createNotification(listNotification, NotificationMessage.REPORT_TITLE, reportBody, "",
                        NotificationStatus.WAIT.getValue(), NotificationType.ALL.getValue(), null, LocalDate.now().atTime(12, 0));

                //neu la thu 2, tao thong bao tong hop theo tuan
                if (day1Date.getDayOfWeek() == DayOfWeek.MONDAY && day1Date.compareTo(day7Date) == 6) {
                    int differentConfirmed = day1.getConfirmed() - day7.getConfirmed();
                    int differentRecovered = day1.getRecovered() - day7.getRecovered();
                    int differentDeath = day1.getDeaths() - day7.getDeaths();

                    StringBuilder body = new StringBuilder();
                    body.append("Trong tuần qua, Việt Nam ");

                    if (differentConfirmed > 0) {
                        body.append("có thêm " + differentConfirmed + " ca nhiễm mới");
                    } else if (differentConfirmed == 0) {
                        body.append("không có ca nhiễm mới");
                    }

                    if (differentRecovered > 0) {
                        body.append(", có " + differentRecovered + " bệnh nhân hồi phục");
                    } else if (differentConfirmed == 0) {
                        body.append(", chưa có bệnh nhân hồi phục");
                    }

                    if (differentDeath > 0) {
                        body.append(" và có " + differentDeath + " ca tử vong");
                    } else if (differentDeath == 0) {
                        body.append(" và không có ca tử vong nào");
                    }
                    createNotification(listNotification, NotificationMessage.REPORT_WEEK_TITLE, body.toString(), "",
                            NotificationStatus.WAIT.getValue(), NotificationType.ALL.getValue(), null, LocalDate.now().atTime(9, 0));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (!CollectionUtils.isEmpty(listNotification)) {
                notificationRepository.saveAll(listNotification);
            }
        }
        System.out.println("Crawl data job, done time: " + LocalDateTime.now());
    }

    public void createNotification(List<Notification> listNotification, String title, String body, String data, int status, int type, String user, LocalDateTime sendAt) {
        Notification notification = new Notification();
        notification.setNotificationTitle(title);
        notification.setNotificationBody(body);
        notification.setNotificationData(data);
        notification.setNotificationCreatedAt(LocalDateTime.now());
        notification.setNotificationStatus(status);
        notification.setNotificationType(type);
        notification.setNotificationUser(user);
        notification.setNotificationSendAt(sendAt);
        listNotification.add(notification);
    }

}
