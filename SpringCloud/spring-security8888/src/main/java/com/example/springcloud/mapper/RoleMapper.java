package com.example.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springcloud.dto.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role>{
    List<Role> selectRolesByPath(String path);
    List<Role> selectRolesByUserId(Long userId);
}
