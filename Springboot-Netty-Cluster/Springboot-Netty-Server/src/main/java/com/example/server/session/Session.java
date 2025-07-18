package com.example.server.session;

import io.netty.channel.Channel;

/**
 * @description: 会话管理接口
 * @author: zzy
 * @createDate: 2025/6/24
 */
public interface Session {
    /**
     * 绑定回话
     * @param channel
     * @param name
     */
    void bind(Channel channel, String name);

    /**
     * 解绑会话
     * @param channel
     */
    String unbind(Channel channel);

    /**
     * 获取属性
     * @param channel
     * @param name
     * @return
     */
    Object getAttribute(Channel channel, String name);

    /**
     * 设置属性
     * @param channel
     * @param name
     * @param value
     */
    void setAttribute(Channel channel, String name, Object value);

    /**
     * 根据用户名获取channel
     * @param name
     * @return
     */
    Channel getChannel(String name);
}
