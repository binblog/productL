package http.resource.http.media;

/**
 * Created by bin.liang on 2016/12/6.
 */
public class PlainMediaHandler implements  MediaHandler {
    @Override
    public BytesWrapper produce(Object e) {
        if(e == null || e.toString() == null) {
            return null;
        }
        return new BytesWrapper(e.toString().getBytes());
    }

    @Override
    public <E> E consume(BytesWrapper o, Class<E> clazz) {
        return  (E)new String(o.getBytes());
    }
}
