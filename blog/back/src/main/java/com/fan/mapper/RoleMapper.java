package com.fan.mapper;

import com.fan.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleMapper {

    // 查询权限
    List<Role> selectByName(String username);
}
