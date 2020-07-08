package com.ptit.ncovihdv.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class CreateAccountRequest implements IRequestData {

    private String username;
    private String password;
    private String fullname;
    private String email;

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) && !StringUtils.isEmpty(fullname);
    }

}
