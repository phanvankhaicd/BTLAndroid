package com.ptit.ncovihdv.dto.response;

import lombok.Data;

/**
 * 06-Jun-2020
 */
@Data
public class LoginResponse {
    private Integer accountId;
    private Integer accountType;
    private String accountName;
    private String token;
    private Integer gender;
    private Integer userId;
    private String fullName;
}
