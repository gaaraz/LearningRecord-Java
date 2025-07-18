package com.example.springcloud.service;

import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;

public interface RoleService {
    Collection<ConfigAttribute> getRoleByPath(String path);
}
