package com.example.netty.server.session;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
public abstract class GroupSessionFactory {
    private static GroupSession session = new GroupSessionMemoryImpl();

    public static GroupSession getGroupSession(){
        return session;
    }
}
