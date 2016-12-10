package http.resource.http;

/**
 * Created by bin.liang on 2016/12/5.
 */
public class HttpResponse {
    private int code;

    private byte[] content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "code=" + code +
                ", content='" + content + '\'' +
                '}';
    }
}
