package echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by bin.liang on 2016/11/10.
 */
public class EchoClient {
    private	final	String	host;
    private	final	int	port;
    public	EchoClient(String	host,	int	port)	{
        this.host	=	host;
        this.port	=	port;
    }
    public	void	start()	throws	Exception	{
        EventLoopGroup group	=	new OioEventLoopGroup();
        try	{
            Bootstrap b	=	new	Bootstrap();																//1
            b.group(group)																																//2
                    .channel(OioSocketChannel.class)												//3
                    .remoteAddress(new InetSocketAddress(host,	port))				//4
                    .handler(new	ChannelInitializer<SocketChannel>()	{				//5
                        @Override
                        public	void	initChannel(SocketChannel	ch)
                                throws	Exception	{
                            ch.pipeline().addLast(
                                    new	EchoClientHandler());
                        }
                    });
            ChannelFuture f	=	b.connect().sync();								//6
            f.channel().closeFuture().sync();												//7
        }	finally	{
            group.shutdownGracefully().sync();												//8
        }
    }
    public	static	void	main(String[]	args)	throws	Exception	{
        /*if	(args.length	!=	2)	{
            System.err.println(
                    "Usage:	"	+	EchoClient.class.getSimpleName()	+
                            "	<host>	<port>");
            return;
        }*/
        final	String	host	=	"127.0.0.1";
        final	int	port	=	8989;
        new	EchoClient(host,	port).start();
    }
}
