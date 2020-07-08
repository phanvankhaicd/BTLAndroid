package com.ptit.ncovihdv.repository;

import com.ptit.ncovihdv.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 04-Jun-2020
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    @Query(value = "Select * From notification no Where no.notification_status = :status And no.notification_type = :type And no.notification_send_at <= now()", nativeQuery = true)
    List<Notification> findNotificationWaiting(@Param("status") Integer status, @Param("type") Integer type);

    @Query(value = "Select * From notification Where DATE(notification_created_at) = CURDATE()", nativeQuery = true)
    List<Notification> findNotificationCurrentDate();
}
