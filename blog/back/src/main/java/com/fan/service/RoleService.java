package com.fan.service;

import com.fan.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> selectByName(String username);
}
