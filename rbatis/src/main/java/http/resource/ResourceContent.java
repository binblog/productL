package http.resource;

import http.resource.comment.HttpComment;
import http.resource.http.media.MediaHandler;

import java.lang.annotation.Annotation;

/**
 * Created by bin on 2016/12/11.
 */
public interface ResourceContent {
    HttpComment getVerbComment(Annotation annotation);

    void registerResource(Class<?> clazz);

    void registerBaseUri(String preUrl);

    void registerVerbComment(Class<? extends Annotation> annotation, HttpComment comment);

    void registerMediaHandler(String type, MediaHandler mediaHandler);

    String getBaseUri();

    <E> E getResource(Class<E> clazz);

    MediaHandler getMediaHandler(String name);

    MediaHandler[] getMediaHandlers(String[] name);
}
