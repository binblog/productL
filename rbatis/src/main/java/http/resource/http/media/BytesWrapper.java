package http.resource.http.media;

/**
 * Created by bin.liang on 2016/12/13.
 *
 * 可以继承该类，以携带更多的属性
 */
public class BytesWrapper {
    private byte[] bytes;

    public BytesWrapper(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
