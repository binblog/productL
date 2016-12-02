package echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by bin.liang on 2016/11/10.
 */
public class EchoServerHandler 	extends
        ChannelInboundHandlerAdapter {
    @Override
    public	void	channelRead(ChannelHandlerContext ctx,
                                  Object	msg)	{
        ByteBuf in	=	(ByteBuf)	msg;
        System.out.println("Server	received:	"	+	in.toString(CharsetUtil.UTF_8));								//2
        ctx.write(in);																												//3
    }

    @Override
    public	void	channelReadComplete(ChannelHandlerContext	ctx)	throws	Exception	{
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                .addListener(ChannelFutureListener.CLOSE);
//        ctx.close();

    }

    @Override
    public	void	exceptionCaught(ChannelHandlerContext	ctx,
                                      Throwable	cause)	{
        cause.printStackTrace();																//5
        ctx.close();																												//6
    }
}