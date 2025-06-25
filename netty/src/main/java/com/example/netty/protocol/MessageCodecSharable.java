package com.example.netty.protocol;

import com.example.netty.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;

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
        ByteBuf out = ctx.alloc().buffer();
        // 4字节魔数
        out.writeBytes(new byte[]{1,2,3,4});
        // 1字节的版本号
        out.writeByte(1);
        // 1字节的序列化方式 0-jdk; 1-json
        out.writeByte(0);
        // 1字节的指令类型
        out.writeByte(msg.getMessageType());
        // 4字节
        out.writeInt(msg.getSequenceId());
        // 1字节,无意义,填充
        out.writeByte(0xff);
        // 获取内容的字节数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();
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
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();
        log.debug("{},{},{},{},{},{}", magicNum, version, serializerType, messageType, sequenceId, length);
        log.debug("{}", message);
        out.add(message);
    }
}
