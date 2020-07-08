package com.ptit.ncovihdv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 03-Jun-2020
 */
@Data
@Entity
@Table(name = "role", schema = "ncovi", catalog = "")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "role_code", nullable = false, length = 50)
    private String roleCode;

    @Column(name = "role_name", nullable = true, length = 50)
    private String roleName;

    @Column(name = "role_description", nullable = true, length = 50)
    private String roleDescription;

    @Column(name = "role_created_at", nullable = true)
    private LocalDateTime roleCreatedAt;

    @OneToMany(mappedBy = "roleByRole")
    private List<User> usersByRoleId;

}
