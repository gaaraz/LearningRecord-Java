package com.example.server.handler;


import com.example.message.GroupQuitRequestMessage;
import com.example.message.GroupQuitResponseMessage;
import com.example.server.session.Group;
import com.example.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
@ChannelHandler.Sharable
public class GroupQuitRequestMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupQuitRequestMessage msg) throws Exception {
        Group group = GroupSessionFactory.getGroupSession().removeMember(msg.getGroupName(), msg.getUsername());

        if (group != null) {
            ctx.writeAndFlush(new GroupQuitResponseMessage(true, "退出" + msg.getGroupName() + "成功！"));
        } else {
            ctx.writeAndFlush(new GroupQuitResponseMessage(false, "退出" + msg.getGroupName() + "失败！"));
        }
    }
}
