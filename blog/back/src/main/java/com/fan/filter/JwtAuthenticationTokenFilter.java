package com.fan.filter;

import com.fan.entity.LoginUser;
import com.fan.utils.JwtUtil;
import com.fan.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //我们先拿到请求头中的token
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){
            //说明没有携带token 那么直接放行 之后的过滤器肯定会报错，那么就说明用户没有登录
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid  = claims.getSubject();
//            System.out.println("解析出的userId=="+userid);
//            System.out.println("解析token后的claims="+claims);
//            System.out.println("解析token后的uerId="+userid);
        } catch (Exception e) {
            e.printStackTrace();
            //就说明token失效了 或者是token无效
//            System.out.println("token过期");
            throw new BadCredentialsException("token无效");
        }
        //从redis中拿到用户的信息，给SecurityContextHolder设置上下文
//        System.out.println( "redisCache"+redisCache.getCacheObject("login:" + userid));
        LoginUser loginUser = redisCache.getCacheObject("login:" + userid);
//        System.out.println("从redis解析出来的loginUser=="+loginUser);
//        System.out.println("Objects.isNull(loginUser)=="+Objects.isNull(loginUser));
        if(Objects.isNull(loginUser)){
            throw new BadCredentialsException("用户未登录");
        }
        //存入SecurityContextHolder上下文当中  注意 这里必须得使用三个参数的authentication
        //第三个参数为授权 也就是用户是啥身份 先不管
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
//        System.out.println("authentication"+authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //放行
        filterChain.doFilter(request,response); //那么就正常的请求接口去啦！！！
    }
}
