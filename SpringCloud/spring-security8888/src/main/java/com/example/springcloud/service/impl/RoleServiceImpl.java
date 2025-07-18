package com.example.springcloud.service.impl;

import com.example.springcloud.dto.Role;
import com.example.springcloud.mapper.RoleMapper;
import com.example.springcloud.service.RoleService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Collection<ConfigAttribute> getRoleByPath(String path) {
        if ("/login".equalsIgnoreCase(path)){
            return Collections.unmodifiableCollection(SecurityConfig.createList("ROLE_ANONYMOUS"));
        }
        List<Role> roleList = roleMapper.selectRolesByPath(path);
        List<ConfigAttribute> roles = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleList)){
            roleList.stream().forEach(role -> roles.add(new SecurityConfig(role.getRoleKey().trim().toUpperCase())));
        }

        return Collections.unmodifiableCollection(roles);
    }


}
