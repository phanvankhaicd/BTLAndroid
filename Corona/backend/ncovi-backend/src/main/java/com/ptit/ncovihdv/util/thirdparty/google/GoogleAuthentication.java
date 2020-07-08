package com.ptit.ncovihdv.util.thirdparty.google;

import com.google.gson.Gson;
import com.ptit.ncovihdv.config.PropertiesConfig;
import com.ptit.ncovihdv.exception.ApplicationException;
import com.ptit.ncovihdv.util.common.CallService;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import com.ptit.ncovihdv.util.thirdparty.UserInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 03-Jun-2020
 */
@Log4j2
@Component
public class GoogleAuthentication {
    private static final String GG_AUTH_TOKEN = "https://oauth2.googleapis.com/tokeninfo?id_token=%s";
    @Autowired
    PropertiesConfig propertiesConfig;

    public GoogleAuthentication() {
    }

    public static GoogleAuthentication getInstance() {
        return new GoogleAuthentication();
    }

    public UserInfo getUser(String token) throws ApplicationException {
        UserInfo user = new UserInfo();
        try {
            String url = String.format(GG_AUTH_TOKEN, token);
            String response = CallService.callGet(url);
            Gson gson = new Gson();
            GooglePojo googlePojo = gson.fromJson(response, GooglePojo.class);
            //lay email lam id cua user
            user.setId(googlePojo.getEmail());
            user.setEmail(googlePojo.getEmail());
            user.setPictureUrl(googlePojo.getPicture());
            user.setName(googlePojo.getName());
            return user;
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException(ApplicationCode.ERROR, e.toString());
        }
    }
}
