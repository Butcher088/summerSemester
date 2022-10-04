package com.fan.controller;

import com.fan.entity.User;
import com.fan.service.RoleService;
import com.fan.service.UserService;
import com.fan.utils.ResponseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('admin')")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/role")
    public ResponseResult getRole(@Param("userName") String userName) {
            return new ResponseResult(200,roleService.selectByName(userName));
    }


}
