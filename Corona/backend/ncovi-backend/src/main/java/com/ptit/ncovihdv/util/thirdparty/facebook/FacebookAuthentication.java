package com.ptit.ncovihdv.util.thirdparty.facebook;

import com.ptit.ncovihdv.exception.ApplicationException;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import com.ptit.ncovihdv.util.thirdparty.UserInfo;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * 03-Jun-2020
 */
@Log4j2
@Component
public class FacebookAuthentication {

    public FacebookAuthentication() {
    }

    public static FacebookAuthentication getInstance() {
        return new FacebookAuthentication();
    }

    public UserInfo getUser(String accessToken, String appSecret) throws ApplicationException {
        try {
            FacebookClient facebookClient =
                    new DefaultFacebookClient(accessToken, appSecret, Version.LATEST);
            com.restfb.types.User userFb =
                    facebookClient.fetchObject("me", com.restfb.types.User.class,
                            Parameter.with("fields", "first_name,last_name,middle_name,name,picture"));
            UserInfo user = new UserInfo();
            user.setName((userFb.getName()));
            user.setId(userFb.getId());
            user.setPictureUrl(userFb.getPicture().getUrl());
            return user;
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException(ApplicationCode.ERROR, e.toString());
        }
    }
}
