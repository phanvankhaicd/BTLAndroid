package com.ptit.ncovihdv.dto.request;

import lombok.Data;
import org.springframework.util.ObjectUtils;

/**
 * 06-Jun-2020
 */
@Data
public class DeleteAccountRequest implements IRequestData {
    private Integer accountId;

    @Override
    public boolean isValid() {
        return !ObjectUtils.isEmpty(accountId);
    }
}
