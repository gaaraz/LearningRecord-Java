package com.example.springcloud.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springcloud.vo.LoginUserDetails;
import com.example.springcloud.dto.Role;
import com.example.springcloud.dto.User;
import com.example.springcloud.mapper.RoleMapper;
import com.example.springcloud.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService{
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)){
            throw new RuntimeException("=====用户名或密码错误=====");
        }
        List<Role> roleList = roleMapper.selectRolesByUserId(user.getId());
        List<String> roles = roleList.stream().map(Role::getRoleKey).collect(Collectors.toList());
        return new LoginUserDetails(user, roles);
    }
}
