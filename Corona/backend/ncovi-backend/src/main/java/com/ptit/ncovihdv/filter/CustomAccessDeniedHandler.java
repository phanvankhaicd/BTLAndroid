package com.ptit.ncovihdv.filter;

import com.google.gson.Gson;
import com.ptit.ncovihdv.dto.response.BaseResponseData;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 12-May-2020
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        BaseResponseData responseData = new BaseResponseData();
        responseData.setErrorCode(ApplicationCode.ERROR);
        responseData.setMessage("Access Denied");
        responseData.setData(null);

        String json = new Gson().toJson(responseData);

        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);
    }
}
