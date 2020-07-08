package com.ptit.ncovihdv.util.firebase;

import com.google.firebase.messaging.*;
import com.ptit.ncovihdv.dto.request.SendMultiDevicesRequest;
import com.ptit.ncovihdv.dto.request.SendNotificationRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * 03-Jun-2020
 */
@Log4j2
@Service
public class FCMService {
    public String sendNotification(SendNotificationRequest request) {
        try {
            Message message = Message.builder()
                    .putData("data", request.getData())
                    .setToken(request.getToken())
                    .setAndroidConfig(getAndroidConfig())
                    .setApnsConfig(getApnsConfig())
                    .setNotification(Notification.builder().setTitle(request.getTitle()).setBody(request.getBody()).build())
                    .build();
            return FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.error(e);
            return null;
        }
    }

    public BatchResponse sendMultiDevices(SendMultiDevicesRequest request) {
        try {
            MulticastMessage multicastMessage = MulticastMessage.builder()
                    .setAndroidConfig(getAndroidConfig())
                    .setApnsConfig(getApnsConfig())
                    .addAllTokens(request.getTokens())
                    .putData("data", request.getData())
                    .setNotification(Notification.builder().setTitle(request.getTitle()).setBody(request.getBody()).build())
                    .build();
            return FirebaseMessaging.getInstance().sendMulticast(multicastMessage);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    //config notification for android
    private AndroidConfig getAndroidConfig() {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis())
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound("default")
                        .setColor("#11E0B7").build()).build();
    }

    //config notification for ios app
    private ApnsConfig getApnsConfig() {
        return ApnsConfig.builder()
                .setAps(Aps.builder().build())
                .build();
    }
}
