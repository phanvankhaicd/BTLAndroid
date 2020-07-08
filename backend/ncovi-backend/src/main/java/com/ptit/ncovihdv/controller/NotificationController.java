package com.ptit.ncovihdv.controller;

import com.ptit.ncovihdv.dto.request.SendMultiDevicesRequest;
import com.ptit.ncovihdv.dto.request.SendNotificationRequest;
import com.ptit.ncovihdv.dto.request.UpdateTokenDeviceRequest;
import com.ptit.ncovihdv.dto.response.BaseResponseData;
import com.ptit.ncovihdv.exception.ApplicationException;
import com.ptit.ncovihdv.service.NotificationService;
import com.ptit.ncovihdv.util.firebase.FCMService;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
public class NotificationController {
    @Autowired
    FCMService fcmService;

    @Autowired
    NotificationService notificationService;

    @PostMapping("/sendNotification")
    public ResponseEntity<?> sendNotification(@RequestBody SendNotificationRequest request) {
        BaseResponseData baseResponseData = new BaseResponseData();
        try {
            if (!request.isValid()) {
                throw new ApplicationException(ApplicationCode.ERROR,
                        ApplicationCode.REQUEST_ERROR_MSG);
            }
            fcmService.sendNotification(request);
            baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
            baseResponseData.setMessage(ApplicationCode.SUCCESS_MSG);
        } catch (ApplicationException e) {
            baseResponseData.setErrorCode(e.getCode());
            baseResponseData.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
    }

    @PostMapping("/sendMultiDevices")
    public ResponseEntity<?> sendMultiDevices(@RequestBody SendMultiDevicesRequest request) {
        BaseResponseData baseResponseData = new BaseResponseData();
        try {
            if (!request.isValid()) {
                throw new ApplicationException(ApplicationCode.ERROR,
                        ApplicationCode.REQUEST_ERROR_MSG);
            }
            fcmService.sendMultiDevices(request);
            baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
            baseResponseData.setMessage(ApplicationCode.SUCCESS_MSG);
        } catch (ApplicationException e) {
            baseResponseData.setErrorCode(e.getCode());
            baseResponseData.setMessage(e.getMessage());
        } finally {

        }
        return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
    }

    @PostMapping("/updateTokenDevice")
    public ResponseEntity<?> updateTokenDevice(@RequestBody UpdateTokenDeviceRequest request) {
        BaseResponseData baseResponseData = new BaseResponseData();
        try {
            if (!request.isValid()) {
                throw new ApplicationException(ApplicationCode.ERROR,
                        ApplicationCode.REQUEST_ERROR_MSG);
            }
            notificationService.updateTokenDevice(request);

            baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
            baseResponseData.setMessage(ApplicationCode.SUCCESS_MSG);

        } catch (ApplicationException e) {
            baseResponseData.setErrorCode(e.getCode());
            baseResponseData.setMessage(e.getMessage());
        } finally {

        }
        return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
    }
}
