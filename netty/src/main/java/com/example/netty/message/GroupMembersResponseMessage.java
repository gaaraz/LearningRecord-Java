package com.example.netty.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/21
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GroupMembersResponseMessage extends Message{

    private Set<String> members;


    @Override
    public int getMessageType() {
        return GroupMembersResponseMessage;
    }
}
