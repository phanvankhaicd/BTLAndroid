package com.ptit.ncovihdv.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponse {

    private String userFullname;
    private String userEmail;
    private Date userBirthday;
    private String userAddress;
    private String userPhoneNo;
    private String userCmt;
    private String userBhxh;
    private Integer userGender;
    private String username;
}
