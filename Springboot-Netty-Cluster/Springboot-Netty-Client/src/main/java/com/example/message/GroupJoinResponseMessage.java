package com.example.message;

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
public class GroupJoinResponseMessage extends AbstractResponseMessage {

    public GroupJoinResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return GroupJoinResponseMessage;
    }
}
