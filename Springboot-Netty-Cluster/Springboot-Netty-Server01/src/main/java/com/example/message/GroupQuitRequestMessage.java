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
public class GroupQuitRequestMessage extends Message{
    private String groupName;

    private String username;


    @Override
    public int getMessageType() {
        return GroupQuitRequestMessage;
    }
}
