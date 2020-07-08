package com.ptit.ncovihdv.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAccountRequest implements IRequestData {

    private String userFullname;
    private String userEmail;
    private Date userBirthday;
    private String userAddress;
    private String userPhoneNo;
    private String userCmt;
    private String userBhxh;
    private Integer userGender;

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(userFullname) && !StringUtils.isEmpty(userEmail);
    }

}
