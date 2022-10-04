package com.fan.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义的认证过滤器，实现前后端分离的json格式登录请求解析
 */
public class MyAuthFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 请求体中的用户名字段名
     */
    private String usernameParameter = "userName";

    /**
     * 请求体中的密码字段名
     */
    private String passwordParameter = "passWord";

    /**
     * 创建自定义认证过滤器
     */
    public MyAuthFilter() {

    }

    /**
     * 创建自定义认证过滤器，并自定义用户名、密码字段名
     *
     * @param usernameParameter 自定义用户名字段名
     * @param passwordParameter 自定义密码字段名
     */
    public MyAuthFilter(String usernameParameter, String passwordParameter) {
        this.usernameParameter = usernameParameter;
        this.passwordParameter = passwordParameter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("请求体 用户名=======" + request.getParameter("userName"));
        System.out.println("请求体 密码=======" + request.getParameter("passWord"));
        // 获取请求体中的用户名/密码字段值，并执行认证
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getParameter("userName"), request.getParameter("passWord"));
        System.out.println("auth====" + this.getAuthenticationManager().authenticate(auth));
        // 返回认证结果
        return this.getAuthenticationManager().authenticate(auth);
    }

}
