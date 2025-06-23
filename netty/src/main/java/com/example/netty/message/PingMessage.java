package com.example.netty.message;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/21
 */
public class PingMessage extends Message{
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
