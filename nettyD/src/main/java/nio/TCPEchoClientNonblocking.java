package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by bin.liang on 2016/11/21.
 */
public class TCPEchoClientNonblocking {
    public static void main(String[] args) throws IOException {


        start();

    }


    private static  void start() throws IOException {
        //��һ��������ΪҪ���ӵķ���˵���������IP
        String server = "127.0.0.1";
        //�ڶ�������ΪҪ���͵�����˵��ַ���
        byte[] argument = "helloWorl".getBytes();
        //����е���������������Ϊ�˿ںţ����û�У���˿ں���Ϊ7
        int servPort = 8111;
        //����һ���ŵ�������Ϊ������ģʽ
        SocketChannel clntChan = SocketChannel.open();
        clntChan.configureBlocking(false);
        //�����˷�������
        if (!clntChan.connect(new InetSocketAddress(server, servPort))){
            //���ϵ���ѯ����״̬��ֱ���������
            while (!clntChan.finishConnect()){
                //�ڵȴ����ӵ�ʱ�������ִ�����������Գ�ַ��ӷ�����IO���첽����
                //����Ϊ����ʾ�÷�����ʹ�ã�ֻ��һֱ��ӡ"."
                System.out.print(".");
            }
        }
        //Ϊ��������ӡ��"."������������������з�
        System.out.print("\n");
        //�ֱ�ʵ����������д�Ļ�����
        ByteBuffer writeBuf = ByteBuffer.wrap(argument);
        ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
        //���յ����ܵ��ֽ���
        int totalBytesRcvd = 0;
        //ÿһ�ε���read�����������յ����ֽ���
        int bytesRcvd;
        //ѭ��ִ�У�ֱ�����յ����ֽ����뷢�͵��ַ������ֽ������
        while (totalBytesRcvd < argument.length){
            //���������ͨ����д���ݵĻ������л���ʣ����ֽڣ������������д���ŵ�
            if (writeBuf.hasRemaining()){
                clntChan.write(writeBuf);
            }
            //���read�������յ�-1����������˹رգ��׳��쳣
            if ((bytesRcvd = clntChan.read(readBuf)) == -1){
                throw new SocketException("Connection closed prematurely");
            }
            //������յ������ֽ���
            totalBytesRcvd += bytesRcvd;
            //�ڵȴ�ͨ����ɵĹ����У��������ִ���������������ַ�����IO���첽����
            //����Ϊ����ʾ�÷�����ʹ�ã�ͬ��ֻ��һֱ��ӡ"."
            System.out.print(".");
        }
        //��ӡ�����յ�������
        System.out.println("Received: " +  new String(readBuf.array(), 0, totalBytesRcvd));
        //�ر��ŵ�
        clntChan.close();
    }
}
