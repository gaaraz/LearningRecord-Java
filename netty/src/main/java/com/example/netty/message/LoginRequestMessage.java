package com.example.netty.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/20
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestMessage extends Message{
    private String username;
    private String password;


    @Override
    public int getMessageType() {
        return LoginRequestMessage;
    }
}
