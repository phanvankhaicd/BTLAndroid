package com.ptit.ncovihdv.dto.request;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 16-May-2020
 */
@Data
public class SendMultiDevicesRequest implements IRequestData {
    private String title;
    private String body;
    private String data;
    private List<String> tokens;

    @Override
    public boolean isValid() {
        return !CollectionUtils.isEmpty(tokens) && !StringUtils.isEmpty(title) && !StringUtils.isEmpty(body);
    }
}
