package com.example.netty.server.session;

import io.netty.channel.Channel;
import java.util.List;
import java.util.Set;

/**
 * @description: 聊天群组会话管理接口
 * @author: zzy
 * @createDate: 2025/6/24
 */
public interface GroupSession {
    /**
     * 创建一个聊天群组,不存在才能创建成功,否则返回null
     * @param name
     * @param members
     * @return
     */
    Group createGroup(String name, Set<String> members);

    /**
     * 加入聊天群组
     * @param name
     * @param member
     * @return
     */
    Group joinMember(String name, String member);

    /**
     * 移除聊天群组成员
     * @param name
     * @param member
     * @return
     */
    Group removeMember(String name, String member);

    /**
     * 移除聊天群组
     * @param name
     * @return
     */
    Group removeGroup(String name);

    /**
     * 获取组成员
     * @param name
     * @return
     */
    Set<String> getMembers(String name);

    /**
     * 获取组成员的channel集合,只有在线的channel才会返回
     * @param name
     * @return
     */
    List<Channel> getMembersChannel(String name);
}
