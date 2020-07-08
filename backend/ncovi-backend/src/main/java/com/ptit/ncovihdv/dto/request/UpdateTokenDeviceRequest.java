package com.ptit.ncovihdv.dto.request;

import lombok.Data;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 04-Jun-2020
 */
@Data
public class UpdateTokenDeviceRequest implements IRequestData {
    private String deviceToken;

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(deviceToken);
    }
}
