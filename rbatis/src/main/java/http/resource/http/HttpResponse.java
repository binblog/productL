package http.resource.http;

import http.resource.http.media.BytesWrapper;

/**
 * Created by bin.liang on 2016/12/5.
 */
public class HttpResponse {
    private int code;

    private BytesWrapper content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public byte[] getContent() {
        return content.getBytes();
    }

    public void setContent(byte[] content) {
        this.content = new BytesWrapper(content);
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "code=" + code +
                ", content='" + content + '\'' +
                '}';
    }
}
