package http.resource.http.media;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by bin.liang on 2016/12/10.
 */
public class FormMediaHandler implements  MediaHandler {
    @Override
    public byte[] produce(Object e) {


        if(e instanceof Map) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            Map<String, Object> map = (Map<String, Object>)e;

            for(Map.Entry<String, Object> entry : map.entrySet()) {
                if(entry.getValue() instanceof File) {
                    File file = (File) entry.getValue();
                    builder.addBinaryBody(entry.getKey(), file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
                } else {
                    builder.addTextBody(entry.getKey(), entry.getValue().toString(), ContentType.TEXT_PLAIN);
                }
            }

            try {
                return EntityUtils.toByteArray(builder.build());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }



        return  null;
    }


    @Override
    public <E> E consume(byte[] o, Class<E> clazz) {
        return null;
    }
}
