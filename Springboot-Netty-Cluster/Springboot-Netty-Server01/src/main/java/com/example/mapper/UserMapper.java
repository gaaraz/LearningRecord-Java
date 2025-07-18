package com.example.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.dto.UserDto;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/27
 */
@Repository
public interface UserMapper extends BaseMapper<UserDto> {
}
