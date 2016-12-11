package http.resource;

import http.resource.comment.HttpComment;
import http.resource.http.HttpResource;
import http.resource.http.media.MediaHandler;
import http.resource.parse.DefaultResourceParser;
import http.resource.proxy.ResourceProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class DefaultResourceContent implements ResourceContent {

    private static Map<String, HttpResource> registeredResource = new HashMap<String, HttpResource>();

    private static Map<String, ResourceProxy> registeredProxy = new HashMap<String, ResourceProxy>();

    private static Map<String, HttpComment> registeredVerbComment = new HashMap<String, HttpComment>();

    private static Map<String, MediaHandler> registeredMediaHandler = new HashMap<String, MediaHandler>();


    private String baseUri;

    public DefaultResourceContent() {

    }


    @Override
    public HttpComment getVerbComment(Annotation annotation) {
        return registeredVerbComment.get(annotation.annotationType().getName());
    }

    @Override
    public void registerResource(Class<?> clazz) {
        DefaultResourceParser classParse = new DefaultResourceParser(this);
        HttpResource resource = classParse.parse(clazz);
        registerResource(clazz.getName(), resource);
    }

    @Override
    public void registerBaseUri(String preUrl) {
        this.baseUri = preUrl;
    }

    @Override
    public void registerVerbComment(Class<? extends Annotation> annotation, HttpComment comment) {
        registeredVerbComment.put(annotation.getName(), comment);
    }

    @Override
    public void registerMediaHandler(String type, MediaHandler mediaHandler) {
        registeredMediaHandler.put(type, mediaHandler);
    }

    public String getBaseUri() {
        return baseUri;
    }

    private void registerResource(String id, HttpResource resource) {
        registeredResource.put(id, resource);
        registeredProxy.put(id, new ResourceProxy(resource));
    }


    @Override
    public <E> E getResource(Class<E> clazz) {

        ResourceProxy proxy = registeredProxy.get(clazz.getName());
        HttpResource resource = registeredResource.get(clazz.getName());
        proxy.setResource(resource);

        return (E) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, proxy);
    }

    @Override
    public MediaHandler getMediaHandler(String name) {
        return registeredMediaHandler.get(name);
    }

    @Override
    public MediaHandler[] getMediaHandlers(String[] names) {
        MediaHandler[] handlers = new MediaHandler[names.length];
        
        for(int i = 0; i < names.length; i++) {
            
            handlers[i] = registeredMediaHandler.get(names[i]);
        }
        return handlers;
    }


}
