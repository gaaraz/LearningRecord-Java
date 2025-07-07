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
public class RpcResponseMessage extends Message {
    // 返回值
    private Object returnValue;

    // 异常值
    private Exception exceptionValue;

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }
}
