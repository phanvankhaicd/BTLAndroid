package com.ptit.ncovihdv.dto.response;

import lombok.Data;

import java.util.List;

/**
 * 05-Jun-2020
 */
@Data
public class GetListSocialAccountResponse {
    private List<SocialAccountResponse> listFacebook;
    private List<SocialAccountResponse> listGoogle;
}
