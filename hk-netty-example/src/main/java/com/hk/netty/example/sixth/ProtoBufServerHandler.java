package com.hk.netty.example.sixth;

import com.hk.protobuf.DataInfo;
import com.hk.protobuf.PersonInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 泛型类型为 proto 生成的类 {@link DataInfo.Student}
 *
 * @author huangkai
 * @date 2019-03-18 21:52
 */
public class ProtoBufServerHandler extends SimpleChannelInboundHandler<PersonInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonInfo.Person msg) {
        switch (msg.getPersonType()) {
            case BOY:
                PersonInfo.Boy boy = msg.getBoy();
                System.out.println(boy.getAddress());
                System.out.println(boy.getAge());
                System.out.println(boy.getName());
                break;
            case GIRL:
                PersonInfo.Girl girl = msg.getGirl();
                System.out.println(girl.getAge());
                System.out.println(girl.getName());
                break;
            case YOUTH:
                PersonInfo.Youth youth = msg.getYouth();
                System.out.println(youth.getWorkYear());
                System.out.println(youth.getName());
        }
//        System.out.println(msg.getAddress());
//        System.out.println(msg.getName());
//        System.out.println(msg.getAge());
    }
}
