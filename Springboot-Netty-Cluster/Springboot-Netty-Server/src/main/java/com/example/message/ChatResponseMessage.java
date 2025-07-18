package com.example.message;

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
public class ChatResponseMessage extends AbstractResponseMessage{
    private String from;
    private String content;


    public ChatResponseMessage(boolean success, String reason) {
        super(success, reason);
    }


    @Override
    public int getMessageType() {
        return ChatResponseMessage;
    }
}
