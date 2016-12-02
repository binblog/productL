package http.resource;

import java.lang.annotation.Annotation;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class HttpResource {

    private String id;
    private String url;
    private Annotation verb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }





    public Annotation getVerb() {
        return verb;
    }

    public void setVerb(Annotation verb) {
        this.verb = verb;
    }

    @Override
    public String toString() {
        return "HttpResource{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", verb=" + verb +
                '}';
    }
}
