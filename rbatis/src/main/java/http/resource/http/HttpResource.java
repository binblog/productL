package http.resource.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class HttpResource {

    private String id;

    Map<String, HttpTransfer>  methodMap = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void addTransfer(String name, HttpTransfer transfer) {
        methodMap.put(name, transfer);
    }

    public  HttpTransfer getTransfer(String name) {
        return methodMap.get(name);
    }
}
