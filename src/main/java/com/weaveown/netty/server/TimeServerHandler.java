package com.weaveown.netty.server;

import com.weaveown.netty.UnixTime;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author wangwei
 * @date 2022/3/1
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // To test if our time server works as expected,you can use the UNIX rdate command.
        // rdate -o <port> -p <host>
        final ChannelFuture channelFuture = ctx.writeAndFlush(new UnixTime());
        channelFuture.addListener((ChannelFutureListener) future -> {
            assert channelFuture == future;
            ctx.close();
        });

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
