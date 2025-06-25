package com.example.netty.server.handler;

import com.example.netty.message.GroupChatResponseMessage;
import com.example.netty.message.GroupJoinRequestMessage;
import com.example.netty.server.session.Group;
import com.example.netty.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
        Group group = GroupSessionFactory.getGroupSession().joinMember(msg.getGroupName(), msg.getUsername());
        if (group != null){
            ctx.writeAndFlush(new GroupChatResponseMessage(true, msg.getUsername() + "加入群聊成功"));
        } else {
            ctx.writeAndFlush(new GroupChatResponseMessage(false, msg.getUsername() + "加入群聊失败"));
        }
    }
}
