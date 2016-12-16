package http.resource.httpclient;

import http.resource.DefaultResourceContent;
import http.resource.ResourceContent;
import http.resource.http.media.JsonMediaHandler;
import http.resource.http.media.PlainMediaHandler;
import http.resource.http.media.StreamMediaHandler;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;


/**
 * Created by bin on 2016/12/11.
 */
public class HttpClientBuilder {
    
    public static ResourceContent build() {
        ResourceContent content = new DefaultResourceContent();
        content.registerVerbComment(POST.class , new PostComment());
        
        content.registerVerbComment(GET.class, new GetComment());

        content.registerMediaHandler(MediaType.APPLICATION_JSON, new JsonMediaHandler());
        content.registerMediaHandler(MediaType.APPLICATION_OCTET_STREAM, new JsonMediaHandler());
        content.registerMediaHandler(MediaType.TEXT_PLAIN, new PlainMediaHandler());
        content.registerMediaHandler(MediaType.APPLICATION_OCTET_STREAM, new StreamMediaHandler());
        content.registerMediaHandler(MediaType.MULTIPART_FORM_DATA, new FormMediaHandler());
        
        return content;
    }
}
