package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by bin.liang on 2016/11/21.
 */
public class TCPServerSelector {
    //???????????
    private static final int BUFSIZE = 256;
    //select?????????????????????
    private static final int TIMEOUT = 3000;
    public static void main(String[] args) throws IOException {

        //????????????
        Selector selector = Selector.open();
        Selector selector2 = Selector.open();


        //???????????
        ServerSocketChannel listnChannel = ServerSocketChannel.open();
        //????????????????
        listnChannel.socket().bind(new InetSocketAddress(8111));
        //????????????????
        listnChannel.configureBlocking(false);
        //?????????????????
        listnChannel.register(selector, SelectionKey.OP_ACCEPT);
//        listnChannel.register(selector2, SelectionKey.OP_ACCEPT);

        //?????????????????????
        TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE);
        //???????select??????????????????????????Key??
        while (true){
            //?????,???????????????I/O????
            if (selector.select(TIMEOUT) == 0){
                //????????????????????????????????????
                //???????????"."
                System.out.print(".");
                continue;
            }




            //????????????????????Key?????iterator???
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            //?????ü????????????
            while (keyIter.hasNext()){
                SelectionKey key = keyIter.next();
                //??????????????????I/O?????accept
                if (key.isAcceptable()){
                    protocol.handleAccept(key);
                }
                //??????????????????I/O?????read
                if (key.isReadable()){
                    protocol.handleRead(key);
                }
                //????ü???????????????????????????????I/O?????write
                if (key.isValid() && key.isWritable()) {
                    protocol.handleWrite(key);
                }
                //?????????????????????????key
                keyIter.remove();
            }
        }
    }
}
