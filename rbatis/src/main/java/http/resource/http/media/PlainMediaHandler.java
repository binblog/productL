package http.resource.http.media;

/**
 * Created by bin.liang on 2016/12/6.
 */
public class PlainMediaHandler implements  MediaHandler {
    @Override
    public byte[] produce(Object e) {
        if(e == null || e.toString() == null) {
            return new byte[0];
        }
        return e.toString().getBytes();
    }

    @Override
    public <E> E consume(byte[] o, Class<E> clazz) {
        return  (E)new String(o);
    }
}
