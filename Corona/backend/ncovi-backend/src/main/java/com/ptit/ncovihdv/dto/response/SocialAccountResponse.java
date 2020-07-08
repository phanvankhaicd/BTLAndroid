package com.ptit.ncovihdv.dto.response;

import lombok.Data;

/**
 * 05-Jun-2020
 */
@Data
public class SocialAccountResponse {
    private Integer userId;
    private Integer accountId;
    private String name;
    private String username;
    private Integer type;

}
