package com.ptit.ncovihdv.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 *  26-Jun-2020
 */
@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Integer userId;
    private String userEmail;
    private String userFullname;
    private Date userBirthday;
    private String userAddress;
    private String userPhoneNo;
    private String userCmt;
    private String userBhxh;
    private Integer userGender;
}
