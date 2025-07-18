package com.example.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/21
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatRequestMessage extends Message{
    private String groupName;
    private String from;
    private String content;


    @Override
    public int getMessageType() {
        return GroupChatRequestMessage;
    }
}
