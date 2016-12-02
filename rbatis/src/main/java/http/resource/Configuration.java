package http.resource;

import http.resource.parse.ClassParse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class Configuration  {

    private static Map<String, HttpResource> registeredResource = new HashMap<String, HttpResource>();

    private String preUrl;

    public void addClass(Class<?> clazz) {
        ClassParse classParse = new ClassParse(clazz);
        classParse.parse(this);
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    public String getPreUrl() {
        return preUrl;
    }

    public void registerResource(String id, HttpResource resource) {
        registeredResource.put(id, resource);
    }
}
