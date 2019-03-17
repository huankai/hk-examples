package com.hk.netty.example.fourth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author huangkai
 * @date 2019-03-17 21:12
 */
public class FourthServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType;
            switch (event.state()) {
                case READER_IDLE: // 读空闲
                    eventType = "读空闲";
                    break;
                case ALL_IDLE: // 读写空闲
                    eventType = "读写空闲";
                    break;
                case WRITER_IDLE: // 写空闲
                    eventType = "写空闲";
                    break;
                default:
                    throw new RuntimeException("Null.");
            }
            System.out.println(ctx.channel().remoteAddress() + "超时事件为:" + eventType);
            ctx.channel().close();
        }
    }
}
