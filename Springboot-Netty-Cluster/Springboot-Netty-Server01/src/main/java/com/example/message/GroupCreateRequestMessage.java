package com.example.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/20
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GroupCreateRequestMessage extends Message{
    private String groupName;
    private Set<String> members;


    @Override
    public int getMessageType() {
        return GroupCreateRequestMessage;
    }
}
