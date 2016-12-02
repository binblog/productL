package echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by bin.liang on 2016/11/10.
 */
public class EchoServer {
    private	final	int	port;
    public	EchoServer(int	port)	{
        this.port	=	port;
    }
    public	static	void	main(String[]	args)	throws	Exception	{

        int	port	=	8989;								//1
        new	EchoServer(port).start();																//2
    }
    public	void	start()	throws	Exception	{
        OioEventLoopGroup group	=	new OioEventLoopGroup();	//3
        try	{
            ServerBootstrap b	=	new	ServerBootstrap();
            b.group(group)																																//4
                    .channel(OioServerSocketChannel.class)								//5
                    .localAddress(new InetSocketAddress(port))				//6
                    .childHandler(new	ChannelInitializer<SocketChannel>()	{	//7
                        @Override
                        public	void	initChannel(SocketChannel	ch)
                                throws	Exception	{
                            ch.pipeline().addLast(
                                    new	EchoServerHandler());
                        }
                    });
            ChannelFuture f	=	b.bind().sync();												//8

            f.channel().closeFuture().sync();												//9
        }	finally	{
            group.shutdownGracefully().sync();												//10
        }
    }
}
