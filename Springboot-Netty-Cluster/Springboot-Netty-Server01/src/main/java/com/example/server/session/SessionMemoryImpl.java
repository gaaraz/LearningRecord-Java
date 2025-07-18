package com.example.server.session;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
public class SessionMemoryImpl implements Session{
    private final Map<String, Channel> usernameChannelMap = new ConcurrentHashMap<>();
    private final Map<Channel, String> channelUsernameMap = new ConcurrentHashMap<>();
    private final Map<Channel, Map<String, Object>> channelAttributeMap = new ConcurrentHashMap<>();

    @Override
    public void bind(Channel channel, String name) {
        usernameChannelMap.put(name, channel);
        channelUsernameMap.put(channel, name);
        channelAttributeMap.put(channel, new ConcurrentHashMap<>());
    }

    @Override
    public void unbind(Channel channel) {
        String name = channelUsernameMap.remove(channel);
        usernameChannelMap.remove(name);
        channelAttributeMap.remove(channel);
    }

    @Override
    public Object getAttribute(Channel channel, String name) {
        return channelAttributeMap.get(channel).get(name);
    }

    @Override
    public void setAttribute(Channel channel, String name, Object value) {
        channelAttributeMap.get(channel).put(name, value);
    }

    @Override
    public Channel getChannel(String name) {
        return usernameChannelMap.get(name);
    }

    @Override
    public String toString() {
        return usernameChannelMap.toString();
    }
}
