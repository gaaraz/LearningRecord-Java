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
public class GroupQuitRequestMessage extends Message{
    private String groupName;

    private String username;

    public GroupQuitRequestMessage(String groupName, String username) {
        this.groupName = groupName;
        this.username = username;
    }

    @Override
    public int getMessageType() {
        return GroupQuitRequestMessage;
    }
}
