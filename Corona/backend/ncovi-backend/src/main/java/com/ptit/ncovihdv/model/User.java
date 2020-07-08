package com.ptit.ncovihdv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "user", schema = "ncovi", catalog = "")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "user_email", nullable = true, length = 100)
    private String userEmail;

    @Column(name = "user_fullname", nullable = true, length = 100)
    private String userFullname;

    @Column(name = "user_birthday", nullable = true)
    private Date userBirthday;

    @Column(name = "user_address", nullable = true, length = 100)
    private String userAddress;

    @Column(name = "user_phone_no", nullable = true, length = 100)
    private String userPhoneNo;

    @Column(name = "user_cmt", nullable = true, length = 100)
    private String userCmt;

    @Column(name = "user_bhxh", nullable = true, length = 100)
    private String userBhxh;

    @Column(name = "user_gender", nullable = true)
    private Integer userGender;

    @Column(name = "user_created_at", nullable = true)
    private LocalDateTime userCreatedAt;

    @Column(name = "user_device_token", nullable = true, length = 1000)
    private String userDeviceToken;

    @OneToMany(mappedBy = "userByUserId")
    private List<Account> accountsByUserId;

    @OneToMany(mappedBy = "userByUser")
    private List<HealthMonitor> healthMonitorsByUserId;

    @OneToMany(mappedBy = "userByUser")
    private List<Reflection> reflectionsByUserId;

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "role_id", nullable = false)
    private Role roleByRole;
}
