package com.example.netty.message;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/21
 */
@Data
@ToString(callSuper = true)
public class GroupMembersRequestMessage extends Message{
    private String groupName;

    public GroupMembersRequestMessage(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int getMessageType() {
        return GroupMembersRequestMessage;
    }
}
