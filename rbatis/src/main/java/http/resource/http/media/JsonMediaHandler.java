package http.resource.http.media;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * Created by bin.liang on 2016/12/5.
 */
public class JsonMediaHandler implements MediaHandler {

    private static  ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

    static {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Override
    public BytesWrapper produce(Object o) {
        try {
            String s1 = mapper.writeValueAsString(o);
            return new BytesWrapper(s1.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <E> E consume(BytesWrapper o, Class<E> clazz) {
        try {

            String s = new String(o.getBytes());
            return mapper.readValue(s, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
