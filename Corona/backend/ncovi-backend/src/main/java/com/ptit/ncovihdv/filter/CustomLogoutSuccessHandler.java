package com.ptit.ncovihdv.filter;

import com.google.gson.Gson;
import com.ptit.ncovihdv.dto.response.BaseResponseData;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 13-May-2020
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
        }
        BaseResponseData responseData = new BaseResponseData();
        responseData.setErrorCode(ApplicationCode.SUCCESS);
        responseData.setMessage("Success");
        responseData.setData(null);

        String json = new Gson().toJson(responseData);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);
    }
}
