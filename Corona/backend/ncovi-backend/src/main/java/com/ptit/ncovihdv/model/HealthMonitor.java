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
@Table(name = "health_monitor", schema = "ncovi", catalog = "")
public class HealthMonitor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_monitor_id", nullable = false)
    private Integer healthMonitorId;

    @Column(name = "health_monitor_sick", nullable = true)
    private Integer healthMonitorSick;

    @Column(name = "health_monitor_cough", nullable = true)
    private Integer healthMonitorCough;

    @Column(name = "health_monitor_sultry", nullable = true)
    private Integer healthMonitorSultry;

    @Column(name = "health_monitor_tired", nullable = true)
    private Integer healthMonitorTired;

    @Column(name = "health_monitor_good", nullable = true)
    private Integer healthMonitorGood;

    @Column(name = "health_monitor_status", nullable = true)
    private Integer healthMonitorStatus;

    @Column(name = "health_monitor_description", nullable = true, length = 50)
    private String healthMonitorDescription;

    @Column(name = "health_monitor_create_at", nullable = true)
    private LocalDateTime healthMonitorCreateAt;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id", nullable = false)
    private User userByUser;

}
