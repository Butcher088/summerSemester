package com.fan.controller;

import com.fan.utils.AjaxResult;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "这是hello Controller")
public class HelloController {

    @GetMapping("/hello")
//    @ApiOperation(value = "这是其中一个hello接口")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "返回所有年份的总产量产值", response = HelloController.class)
//    })
    public AjaxResult hello(@ApiParam(value = "用户名") @Param("username") String username, @ApiParam(value = "密码") @Param("password") String password) {
        System.out.println("hello " + username);
        return AjaxResult.success("操作成功");
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/helloworld")
    public String helloworld() {
        return "携带token登录";
    }

}

