package com.example.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
public class UserDto implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String username;
    private String password;
}
