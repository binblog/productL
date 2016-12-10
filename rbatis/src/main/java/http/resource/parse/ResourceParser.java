package http.resource.parse;

import http.resource.ResourceContent;
import http.resource.comment.HttpComment;
import http.resource.http.*;
import http.resource.http.media.MediaHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class ResourceParser {
    private Class<?> clazz;
    private ResourceContent resourceContent;

    public ResourceParser(ResourceContent configuration, Class<?> clazz) {
        this.clazz = clazz;
        this.resourceContent = configuration;
    }


    private HttpTransfer parseMethod(Method method, String preUrl) {

        HttpTransfer transfer = new HttpTransfer();

        transfer.setResultType(method.getReturnType());

        Annotation[] annotations = method.getAnnotations();

        boolean hasVerb = false;

        Consumes consumes = method.getAnnotation(Consumes.class);
        if(consumes != null) {
            MediaHandler consumesHandler = resourceContent.getMediaHandler(consumes.value()[0]);
            transfer.setConsumersHandler(consumesHandler);
            transfer.setConsumersMediaType(consumes.value()[0]);
        } else {
            transfer.setConsumersHandler(resourceContent.getMediaHandler(MediaType.TEXT_PLAIN));
            transfer.setConsumersMediaType(MediaType.TEXT_PLAIN);
        }


        Produces produces = method.getAnnotation(Produces.class);
        if(produces != null) {
            MediaHandler producesHandler = resourceContent.getMediaHandler(produces.value()[0]);
            transfer.setProducesHandler(producesHandler);
            transfer.setProducesMediaType(produces.value()[0]);
        } else {
            transfer.setProducesHandler(resourceContent.getMediaHandler(MediaType.TEXT_PLAIN));
            transfer.setProducesMediaType(MediaType.TEXT_PLAIN);
        }

        Path path = method.getAnnotation(Path.class);
        if(path != null) {
            preUrl += "/" +  path.value();
        }
        transfer.setUrl(preUrl);

        for(Annotation annotation : annotations) {
            HttpComment comment = resourceContent.getVerbComment(annotation);
            if(comment != null) {
                if(hasVerb) {
                    throw new RuntimeException();
                }

                transfer.setExecutor(new TransferExecutor(transfer, comment));

                hasVerb = true;
            }

        }



        Parameter[] parameters = method.getParameters();
        for(int i = 0; i < parameters.length ; i ++) {
            Parameter parameter = parameters[i];
            PathParam pathParam = parameter.getAnnotation(PathParam.class);
            if(pathParam != null) {
                transfer.addParam(HttpParameter.newPathParameter(i, pathParam.value()));
                continue;
            } else {
//                transfer.addParam(HttpParameter.newBodyParameter());
            }


            FormParam formParam = parameter.getAnnotation(FormParam.class);

            if(formParam != null) {
                transfer.addParam(HttpParameter.newFormParameter(i, formParam.value()));
                continue;
            }


            transfer.addParam(HttpParameter.newBodyParameter(i));



        }

        return transfer;

    }



    public HttpResource parse() {
        Annotation[] annotations = clazz.getAnnotations();

        String id = clazz.getName();

        String preUrl = resourceContent.getPreUrl();
        for(Annotation annotation : annotations) {
            if(annotation instanceof Path) {
                preUrl += ((Path) annotation).value();
            }
        }

        Method[] methods = clazz.getMethods();

        HttpResource resource = new HttpResource();

        for(Method method : methods) {
            HttpTransfer transfer = parseMethod(method, preUrl);
             resource.addTransfer(method.getName(), transfer);
        }



        resourceContent.registerResource(id, resource);
        return resource;


    }



}
