package nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * Created by bin.liang on 2016/11/21.
 */
public interface TCPProtocol {
    //accept I/O��ʽ
    void handleAccept(SelectionKey key) throws IOException;
    //read I/O��ʽ
    void handleRead(SelectionKey key) throws IOException;
    //write I/O��ʽ
    void handleWrite(SelectionKey key) throws IOException;
}
