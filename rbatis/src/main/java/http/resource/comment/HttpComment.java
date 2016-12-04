package http.resource.comment;

import java.io.IOException;

/**
 * Created by bin on 2016/12/4.
 */
public interface HttpComment {

    String excute(String url, Object params) throws IOException;
}
