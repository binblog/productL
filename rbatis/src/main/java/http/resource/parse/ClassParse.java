package http.resource.parse;

import http.resource.Configuration;
import http.resource.HttpResource;
import http.resource.rest.Path;
import http.resource.rest.Post;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class ClassParse {
    private Class<?> clazz;

    public ClassParse(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void parse(Configuration configuration) {
        Annotation[] annotations = clazz.getAnnotations();


        String preUrl = configuration.getPreUrl();
        for(Annotation annotation : annotations) {
            if(annotation instanceof Path) {
                preUrl += ((Path) annotation).value();
            }
        }

        Method[] methods = clazz.getMethods();

        for(Method method : methods) {
            HttpResource httpResource = parseMethod(method, preUrl);

            System.out.println(httpResource);

            configuration.registerResource(httpResource.getId(), httpResource);
        }

    }


    private HttpResource parseMethod(Method method, String preUrl) {

        String id = clazz.getName() + "." + method.getName();

        HttpResource resource = new HttpResource();

        resource.setId(id);


        resource.setUrl(preUrl);


        Annotation[] annotations = method.getAnnotations();

        boolean hasVerb = false;

        for(Annotation annotation : annotations) {
            if(isVerb(annotation)) {    // verbUtils ?
                if(hasVerb) {
                    throw new RuntimeException();
                }

                resource.setVerb(annotation);
                hasVerb = true;
            }
        }

        return resource;
    }

    private boolean isVerb(Annotation annotation) {
        if(annotation instanceof Post) {
            return true;
        }

        return false;
    }

}
