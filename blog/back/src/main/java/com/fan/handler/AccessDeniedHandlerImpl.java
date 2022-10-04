package com.fan.handler;

import com.alibaba.fastjson.JSON;
import com.fan.utils.ResponseResult;
import com.fan.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value()); //403
        ResponseResult result = new ResponseResult(403, "权限不足无法访问");
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
