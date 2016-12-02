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
    //�������ĳ���
    private static final int BUFSIZE = 256;
    //select�����ȴ��ŵ�׼���õ��ʱ��
    private static final int TIMEOUT = 3000;
    public static void main(String[] args) throws IOException {

        //����һ��ѡ����
        Selector selector = Selector.open();
        Selector selector2 = Selector.open();


        //ʵ����һ���ŵ�
        ServerSocketChannel listnChannel = ServerSocketChannel.open();
        //�����ŵ��󶨵�ָ���˿�
        listnChannel.socket().bind(new InetSocketAddress(8111));
        //�����ŵ�Ϊ������ģʽ
        listnChannel.configureBlocking(false);
        //��ѡ����ע�ᵽ�����ŵ�
        listnChannel.register(selector, SelectionKey.OP_ACCEPT);
//        listnChannel.register(selector2, SelectionKey.OP_ACCEPT);

        //����һ��ʵ����Э��ӿڵĶ���
        TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE);
        //������ѯselect��������ȡ׼���õ��ŵ���������Key��
        while (true){
            //һֱ�ȴ�,ֱ�����ŵ�׼������I/O����
            if (selector.select(TIMEOUT) == 0){
                //�ڵȴ��ŵ�׼����ͬʱ��Ҳ�����첽��ִ����������
                //����ֻ�Ǽ򵥵ش�ӡ"."
                System.out.print(".");
                continue;
            }




            //��ȡ׼���õ��ŵ���������Key���ϵ�iteratorʵ��
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            //ѭ��ȡ�ü����е�ÿ����ֵ
            while (keyIter.hasNext()){
                SelectionKey key = keyIter.next();
                //���������ŵ�����Ȥ��I/O����Ϊaccept
                if (key.isAcceptable()){
                    protocol.handleAccept(key);
                }
                //����ͻ����ŵ�����Ȥ��I/O����Ϊread
                if (key.isReadable()){
                    protocol.handleRead(key);
                }
                //����ü�ֵ��Ч���������Ӧ�Ŀͻ����ŵ�����Ȥ��I/O����Ϊwrite
                if (key.isValid() && key.isWritable()) {
                    protocol.handleWrite(key);
                }
                //������Ҫ�ֶ��Ӽ������Ƴ���ǰ��key
                keyIter.remove();
            }
        }
    }
}
