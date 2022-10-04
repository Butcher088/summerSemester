package com.fan.service.Impl;

import com.fan.entity.Role;
import com.fan.mapper.RoleMapper;
import com.fan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectByName(String username) {
        return roleMapper.selectByName(username);
    }
}
