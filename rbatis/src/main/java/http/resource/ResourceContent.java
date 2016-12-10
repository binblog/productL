package http.resource;

import http.resource.comment.httpclient.GetComment;
import http.resource.comment.HttpComment;
import http.resource.comment.httpclient.PostComment;
import http.resource.http.HttpResource;
import http.resource.http.media.*;
import http.resource.parse.ResourceParser;
import http.resource.proxy.ResourceProxy;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class ResourceContent {

    private static Map<String, HttpResource> registeredResource = new HashMap<String, HttpResource>();

    private static Map<String, ResourceProxy> registeredProxy = new HashMap<String, ResourceProxy>();

    private static Map<String,  HttpComment> verbComment = new HashMap<String,  HttpComment>();

//    private static Map<String, AsyncHttpComment> asyncVerbComment = new HashMap<String, AsyncHttpComment>();

    private static Map<String, MediaHandler> registeredMediaHandler = new HashMap<String, MediaHandler>();


    private String preUrl;

    public ResourceContent() {
        verbComment.put(GET.class.getName(), new GetComment());
        verbComment.put(POST.class.getName(), new PostComment());

        registeredMediaHandler.put(MediaType.APPLICATION_JSON, new JsonMediaHandler());
        registeredMediaHandler.put(MediaType.APPLICATION_OCTET_STREAM, new JsonMediaHandler());
        registeredMediaHandler.put(MediaType.TEXT_PLAIN, new PlainMediaHandler());
        registeredMediaHandler.put(MediaType.APPLICATION_OCTET_STREAM, new StreamMediaHandler());
        registeredMediaHandler.put(MediaType.MULTIPART_FORM_DATA, new FormMediaHandler());
    }

    public HttpComment getVerbComment(Annotation annotation) {
        return   verbComment.get(annotation.annotationType().getName());
    }

    public void addClass(Class<?> clazz) {
        ResourceParser classParse = new ResourceParser(this, clazz);
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
        registeredProxy.put(id, new ResourceProxy(resource));
    }


    public <E> E getResource(Class<E> clazz) {

        ResourceProxy proxy = registeredProxy.get(clazz.getName());
        HttpResource resource = registeredResource.get(clazz.getName());
        proxy.setResource(resource);

        return (E) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, proxy);
    }

    public MediaHandler getMediaHandler(String name) {
        return registeredMediaHandler.get(name);
    }
}
