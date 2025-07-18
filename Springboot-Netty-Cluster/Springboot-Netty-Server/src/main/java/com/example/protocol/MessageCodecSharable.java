package com.example.protocol;


import com.example.config.SocketProperties;
import com.example.message.Message;
import com.example.util.SpringContextUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/21
 */
@Slf4j
@ChannelHandler.Sharable
/**
 * 当handler不保存状态时,就可以安全的在多线程下被共享
 * 但要注意对于编解码器类,不能继承ByteToMessageCodec或CombinedChannelDuplexHandler父类,它们的构造方法对@Sharable有限制
 * 如果能确保编解码器不会保存状态,可以继承MessageToMessageCodec父类
 */
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> list) throws Exception {
        SocketProperties socketProperties = SpringContextUtil.getBean(SocketProperties.class);
        ByteBuf out = ctx.alloc().buffer();
        // 4字节魔数
        out.writeBytes(new byte[]{1,2,3,4});
        // 1字节的版本号
        out.writeByte(1);
        // 1字节的序列化方式 0-jdk; 1-json
        out.writeByte(socketProperties.getSerializerAlgorithm().ordinal());
        // 1字节的指令类型
        out.writeByte(msg.getMessageType());
        // 4字节
        out.writeInt(msg.getSequenceId());
        // 1字节,无意义,填充
        out.writeByte(0xff);
        // 获取内容的字节数组
        byte[] bytes = socketProperties.getSerializerAlgorithm().serialize(msg);
        // 长度
        out.writeInt(bytes.length);
        // 写入内容
        out.writeBytes(bytes);
        list.add(out);
    }


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicNum = in.readInt();
        byte version = in.readByte();
        byte serializerType = in.readByte();
        byte messageType = in.readByte();
        int sequenceId = in.readInt();
        in.readByte();      // 无意义字节
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);

        // 找到反序列化方法
        Serializer.Algorithm algorithm = Serializer.Algorithm.values()[serializerType];
        // 确定具体消息类型
        Class<?> messageClass = Message.getMessageClass(messageType);
        Object message = algorithm.deserialize(messageClass, bytes);

        log.debug("{},{},{},{},{},{}", magicNum, version, serializerType, messageType, sequenceId, length);
        log.debug("{}", message);
        out.add(message);
    }
}
