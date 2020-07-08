package com.ptit.ncovihdv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 03-Jun-2020
 */
@Data
@Entity
@Table(name = "notification", schema = "ncovi", catalog = "")
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false)
    private Integer notificationId;

    @Column(name = "notification_title", nullable = false, length = 100)
    private String notificationTitle;

    @Column(name = "notification_body", nullable = false, length = 1000)
    private String notificationBody;

    @Column(name = "notification_data", length = 1000)
    private String notificationData;

    @Column(name = "notification_created_at", nullable = false)
    private LocalDateTime notificationCreatedAt;

    @Column(name = "notification_status", nullable = false)
    private Integer notificationStatus;

    @Column(name = "notification_type", nullable = false)
    private Integer notificationType;

    @Column(name = "notification_user", length = 1000)
    private String notificationUser;

    @Column(name = "notification_send_at", nullable = false)
    private LocalDateTime notificationSendAt;
}
