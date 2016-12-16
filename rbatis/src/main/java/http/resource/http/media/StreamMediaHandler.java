package http.resource.http.media;

import org.apache.http.util.ByteArrayBuffer;

import java.io.*;

/**
 * Created by bin.liang on 2016/12/6.
 */
public class StreamMediaHandler implements MediaHandler {

    @Override
    public BytesWrapper produce(Object o) {

        if(o instanceof File) {
            try {
                File file = (File)o;
                InputStream instream = new FileInputStream(file);

                try {

                    int i = (int) file.length();
                    if (i < 0) {
                        i = 4096;
                    }
                    final ByteArrayBuffer buffer = new ByteArrayBuffer(i);
                    final byte[] tmp = new byte[4096];
                    int l;
                    while((l = instream.read(tmp)) != -1) {
                        buffer.append(tmp, 0, l);
                    }
                    return new BytesWrapper(buffer.toByteArray());

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    public <E> E consume(BytesWrapper o, Class<E> clazz) {
        return null;
    }
}
