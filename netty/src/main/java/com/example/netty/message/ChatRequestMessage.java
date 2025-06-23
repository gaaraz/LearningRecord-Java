package com.example.netty.message;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/20
 */
@Data
@ToString(callSuper = true)
public class ChatRequestMessage extends Message{
    private String content;
    private String to;
    private String from;

    public ChatRequestMessage() {
    }

    public ChatRequestMessage(String content, String to, String from) {
        this.content = content;
        this.to = to;
        this.from = from;
    }

    @Override
    public int getMessageType() {
        return ChatRequestMessage;
    }
}
