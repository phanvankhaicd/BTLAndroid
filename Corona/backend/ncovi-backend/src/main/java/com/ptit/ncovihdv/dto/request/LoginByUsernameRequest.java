package com.ptit.ncovihdv.dto.request;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 06-Jun-2020
 */
@Data
public class LoginByUsernameRequest implements IRequestData {
    private String username;
    private String password;

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(username) && !StringUtils.isEmpty(password);
    }
}
