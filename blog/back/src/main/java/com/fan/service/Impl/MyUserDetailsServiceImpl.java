package com.fan.service.Impl;

import com.fan.entity.LoginUser;
import com.fan.entity.Role;
import com.fan.entity.User;
import com.fan.service.RoleService;
import com.fan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自行实现Spring Security的登录逻辑
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 取出用户
     *
     * @param username 用户名
     */
    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userService.selectByName(username);
        if(myUser == null) {
            throw new RuntimeException("用户不存在");
        }
//        System.out.println("当前登录的用户是"+myUser.getUserName());
        // 查询username权限
        List<Role> listRole = roleService.selectByName(username);
        myUser.setRoles(listRole);
//        System.out.println("用户"+username+"权限有"+listRole);
        if (myUser == null) {
            throw new UsernameNotFoundException("找不到用户！");
        }
        // 组装权限
        List<String> authorities = new ArrayList<>();
        List<Role> roles = myUser.getRoles();
        for (Role role : roles) {
            authorities.add(role.getRole());
        }
//        System.out.println("authrities="+authorities);
        return new LoginUser(myUser,authorities);
    }

}
