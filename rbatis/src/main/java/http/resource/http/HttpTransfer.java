package http.resource.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bin on 2016/12/3.
 */
public class HttpTransfer {

    private String url;
    private String verb;

    private Object bodyParam;

    private TransferExecutor executor;

    private List<HttpParam> params = new ArrayList<>();


    public TransferExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(TransferExecutor executor) {
        this.executor = executor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public Object getBodyParam() {
        return bodyParam;
    }

    public void setBodyParam(Object bodyParam) {
        this.bodyParam = bodyParam;
    }

    public void addParam(HttpParam param) {
        params.add(param);
    }


    public List<HttpParam> getParams() {
        return params;
    }
}
