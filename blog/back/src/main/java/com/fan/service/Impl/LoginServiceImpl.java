package com.fan.service.Impl;

import com.fan.entity.LoginUser;
import com.fan.entity.User;
import com.fan.utils.JwtUtil;
import com.fan.utils.RedisCache;
import com.fan.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl {


    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;


    public ResponseResult login(User user) {
//        System.out.println("你的=" + user.getPassWord());
        LoginUser loginUser = myUserDetailsService.loadUserByUsername(user.getUserName());
//        System.out.println("userDetails.getAuthorities====" + loginUser.getAuthorities());
//        System.out.println("数据库的=" + loginUser.getPassword());

        // 判断是不是BC加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassWord());
//        System.out.println(encode); // $2a$10$ODNox74WrVz/c0hL8E0CzOWqoMUg89YTHhkc7ZsUf0BCe6W.V17yC
//        System.out.println("match="+encoder.matches("root","$2a$10$ODNox74WrVz/c0hL8E0CzOWqoMUg89YTHhkc7ZsUf0BCe6W.V17yC"));
//
        if (!user.getPassWord().equals(loginUser.getPassword()) && !encoder.matches(user.getPassWord(),loginUser.getPassword())) {
            return new ResponseResult(401, "用户名或密码错误");
        } else {
            System.out.println("用户名密码正确");
        }
        int userId = loginUser.getUser().getUserId();
        String jwt = JwtUtil.createJWT(String.valueOf(userId));
        redisCache.setCacheObject("login:" + userId, loginUser); //将用户信息直接存入redis
//        System.out.println("缓存中的userid==" + userId + "  " + redisCache.getCacheObject("login:" + userId));
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        map.put("userId", String.valueOf(userId));
        map.put("userName", user.getUserName());
        return new ResponseResult(200, map);
    }

    public ResponseResult logout(int userId) {
//        System.out.println("这是登出接口");
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult(200, "退出成功");
    }

}

