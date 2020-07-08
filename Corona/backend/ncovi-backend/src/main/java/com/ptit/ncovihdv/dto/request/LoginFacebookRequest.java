package com.ptit.ncovihdv.dto.request;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 04-Jun-2020
 */
@Data
public class LoginFacebookRequest implements IRequestData {
    private String token;

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(token);
    }
}
