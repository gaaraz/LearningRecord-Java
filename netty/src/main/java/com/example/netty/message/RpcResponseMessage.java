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
public class RpcResponseMessage extends Message{
    // 返回值
    private Object returnValue;

    // 异常值
    private Exception exceptionValue;

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }
}
