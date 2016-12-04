package http.resource;

import http.resource.comment.GetComment;
import http.resource.comment.HttpComment;
import http.resource.http.HttpResource;
import http.resource.parse.ClassParser;
import http.resource.proxy.ResourceProxy;
import http.resource.rest.GET;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class Configuration  {

    private static Map<String, HttpResource> registeredResource = new HashMap<String, HttpResource>();

    private static Map<String, ResourceProxy> registerProxy = new HashMap<>();

    private static Map<String, Class<? extends  HttpComment>> verbComment = new HashMap<>();

    private String preUrl;
    
    
    public Configuration() {
        verbComment.put(GET.class.getName(), GetComment.class);
    }

    public Class<? extends HttpComment> getVerbComment(Annotation annotation) {
        System.out.println(verbComment);
        return        verbComment.get(annotation.annotationType().getName());
    }

    public void addClass(Class<?> clazz) {
        ClassParser classParse = new ClassParser(this, clazz);
        HttpResource resource  = classParse.parse();
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    public String getPreUrl() {
        return preUrl;
    }

    public void registerResource(String id, HttpResource resource) {
        registeredResource.put(id, resource);
        registerProxy.put(id, new ResourceProxy(resource));
    }


    public <E> E getResource(Class<E> clazz) {
        System.out.println(clazz.getName());

        ResourceProxy proxy = registerProxy.get(clazz.getName());
        HttpResource resource = registeredResource.get(clazz.getName());
        proxy.setResource(resource);


        return (E) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, proxy);
    }
}
