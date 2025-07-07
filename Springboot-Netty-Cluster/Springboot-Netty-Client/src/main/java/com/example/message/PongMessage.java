package com.example.message;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/21
 */
public class PongMessage extends Message {
    @Override
    public int getMessageType() {
        return PongMessage;
    }
}
