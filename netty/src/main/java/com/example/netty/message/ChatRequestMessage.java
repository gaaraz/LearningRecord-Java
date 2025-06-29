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
public class ChatRequestMessage extends Message{
    private String from;
    private String to;
    private String content;

    @Override
    public int getMessageType() {
        return ChatRequestMessage;
    }
}
