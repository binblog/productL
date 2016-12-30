package echo;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


/**
 * Created by bin.liang on 2016/11/14.
 */
public class EchoClient {
    private final String host;
    private final int port;
    private final int firstMessageSize;

    public EchoClient(String host, int port, int firstMessageSize) {
        this.host = host;
        this.port = port;
        this.firstMessageSize = firstMessageSize;
    }

    public void run() {
        // Configure the client.
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Set up the pipeline factory.
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(
                        new EchoClientHandler(firstMessageSize));
            }
        });

        // Start the connection attempt.
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));

        // Wait until the connection is closed or the connection attempt fails.
        future.getChannel().getCloseFuture().awaitUninterruptibly();

        // Shut down thread pools to exit.
        bootstrap.releaseExternalResources();
    }

    public static void main(String[] args) throws Exception {
        // Print usage if no argument is specified.
//        if (args.length < 2 || args.length > 3) {
//            System.err.println(
//                    "Usage: " + EchoClient.class.getSimpleName() +
//                            " <host> <port> [<first message size>]");
//            return;
//        }
//
//        // Parse options.
//        final String host = args[0];
//        final int port = Integer.parseInt(args[1]);
//        final int firstMessageSize;
//        if (args.length == 3) {
//            firstMessageSize = Integer.parseInt(args[2]);
//        } else {
//            firstMessageSize = 256;
//        }

        String host = "127.0.0.1";
        int port = 8080;
        int firstMessageSize = 256;

        new EchoClient(host, port, firstMessageSize).run();
    }
}
