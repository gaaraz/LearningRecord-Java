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
public abstract class AbstractResponseMessage extends Message{
    private boolean success;
    private String reason;

    public AbstractResponseMessage(){

    }

    public AbstractResponseMessage(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }
}
