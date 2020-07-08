package com.ptit.ncovihdv.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordRequest implements IRequestData {

    private String oldPassword;
    private String newPassword;

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(oldPassword) && !StringUtils.isEmpty(newPassword);
    }

}
