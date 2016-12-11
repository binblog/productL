package http.resource.comment;

import http.resource.http.HttpResponse;

/**
 * Created by bin on 2016/12/4.
 */
public interface HttpComment {

    HttpResponse execute(String url, String contentType, byte[] params);

}
