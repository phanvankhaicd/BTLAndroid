package com.ptit.ncovihdv.dto.request;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 16-May-2020
 */
@Data
public class SendNotificationRequest implements IRequestData {
    private String token;
    private String data;
    private String title;
    private String body;

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(token) && !StringUtils.isEmpty(title) && !StringUtils.isEmpty(body);
    }
}
