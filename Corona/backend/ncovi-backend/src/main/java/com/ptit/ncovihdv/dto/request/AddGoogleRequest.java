package com.ptit.ncovihdv.dto.request;

import lombok.Data;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 05-Jun-2020
 */
@Data
public class AddGoogleRequest implements IRequestData {
    private String token;

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(token);
    }
}
