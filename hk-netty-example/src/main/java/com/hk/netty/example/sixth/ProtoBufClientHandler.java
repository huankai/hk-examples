package com.hk.netty.example.sixth;

import com.hk.protobuf.DataInfo;
import com.hk.protobuf.PersonInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * 泛型类型为 {@link DataInfo.Student}
 *
 * @author huangkai
 * @date 2019-03-18 21:52
 */
public class ProtoBufClientHandler extends SimpleChannelInboundHandler<PersonInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonInfo.Person msg) {

    }

    /**
     * 连接成功时向服务端发送消息
     *
     * @param ctx ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        int random = new Random().nextInt(3);
        PersonInfo.Person person;
        if (random == 0) {
            person = PersonInfo.Person.newBuilder()
                    .setPersonType(PersonInfo.Person.PersonType.BOY)
                    .setBoy(PersonInfo.Boy.newBuilder()
                            .setName("小线")
                            .setAge(10)
                            .setAddress("广州")
                            .build())
                    .build();
        } else if (random == 2) {
            person = PersonInfo.Person.newBuilder()
                    .setPersonType(PersonInfo.Person.PersonType.GIRL)
                    .setGirl(PersonInfo.Girl.newBuilder()
                            .setName("张三")
                            .setAge(10)
                            .build())
                    .build();

        } else {
            person = PersonInfo.Person.newBuilder()
                    .setPersonType(PersonInfo.Person.PersonType.YOUTH)
                    .setYouth(PersonInfo.Youth.newBuilder()
                            .setName("李四")
                            .setWorkYear(4)
                            .build())
                    .build();

        }
//        DataInfo.Student student = DataInfo.Student.newBuilder()
//                .setAddress("广州")
//                .setAge(19)
//                .setName("张三")
//                .build();
//        ctx.channel().writeAndFlush(student);
        ctx.writeAndFlush(person);
    }
}
