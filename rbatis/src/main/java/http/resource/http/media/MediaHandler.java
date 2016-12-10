package http.resource.http.media;

/**
 * Created by bin.liang on 2016/12/5.
 */
public interface  MediaHandler {
    byte[] produce(Object e);    // 生产参数

    <E> E consume(byte[] o, Class<E> clazz);       // 消费结果
}
