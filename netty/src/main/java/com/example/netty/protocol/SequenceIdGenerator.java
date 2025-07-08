package com.example.netty.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/8
 */
public abstract class SequenceIdGenerator {
    private static final AtomicInteger id = new AtomicInteger();

    public static int nextId() {
        return id.incrementAndGet();
    }
}
