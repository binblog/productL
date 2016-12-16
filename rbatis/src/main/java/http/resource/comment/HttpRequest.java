package http.resource.comment;

import http.resource.http.media.BytesWrapper;
import http.resource.http.media.MediaHandler;

/**
 * Created by bin.liang on 2016/12/13.
 */
public class HttpRequest {
    private String url;

    private String[] consumersMediaType;
    private MediaHandler[] producesHandlers;


    private BytesWrapper bytesWrapper;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getConsumersMediaType() {
        return consumersMediaType;
    }

    public void setConsumersMediaType(String[] consumersMediaType) {
        this.consumersMediaType = consumersMediaType;
    }

    public MediaHandler[] getProducesHandlers() {
        return producesHandlers;
    }

    public void setProducesHandlers(MediaHandler[] producesHandlers) {
        this.producesHandlers = producesHandlers;
    }

    public BytesWrapper getBytesWrapper() {
        return bytesWrapper;
    }

    public void setBytesWrapper(BytesWrapper bytesWrapper) {
        this.bytesWrapper = bytesWrapper;
    }
}
