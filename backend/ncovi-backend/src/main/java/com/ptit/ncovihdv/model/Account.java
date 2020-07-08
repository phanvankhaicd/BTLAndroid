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
@Table(name = "account", schema = "ncovi", catalog = "")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name = "account_username", nullable = false, length = 45)
    private String accountUsername;

    @Column(name = "account_password", length = 100)
    private String accountPassword;

    @Column(name = "account_name", nullable = false, length = 45)
    private String accountName;

    @Column(name = "account_status", nullable = false)
    private Integer accountStatus;

    @Column(name = "account_type", nullable = false)
    private Integer accountType;

    @Column(name = "account_created_at", nullable = false)
    private LocalDateTime accountCreatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userByUserId;

}
