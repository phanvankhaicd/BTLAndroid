package com.ptit.ncovihdv.filter;

import com.google.gson.Gson;
import com.ptit.ncovihdv.dto.response.BaseResponseData;
import com.ptit.ncovihdv.util.message.ApplicationCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {
        BaseResponseData responseData = new BaseResponseData();
        responseData.setErrorCode(ApplicationCode.ERROR);
        responseData.setMessage("Unauthorized");
        responseData.setData(null);

        String json = new Gson().toJson(responseData);

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);
    }
}
