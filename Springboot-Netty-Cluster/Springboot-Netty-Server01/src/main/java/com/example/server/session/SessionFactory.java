package com.example.server.session;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
public abstract class SessionFactory {
    private static Session session = new SessionMemoryImpl();

    public static Session getSession(){
        return session;
    }
}
