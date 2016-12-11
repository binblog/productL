package http.resource.parse;

import http.resource.http.HttpResource;

/**
 * Created by bin on 2016/12/11.
 */
public interface ResourceParser {
    HttpResource parse(Class<?> clazz);
}
