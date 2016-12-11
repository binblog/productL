package http.resource.parse;

import http.resource.ResourceContent;
import http.resource.comment.HttpComment;
import http.resource.exception.ResourceParseException;
import http.resource.http.HttpParameter;
import http.resource.http.HttpTransfer;
import http.resource.http.TransferExecutor;
import http.resource.http.media.MediaHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by bin on 2016/12/11.
 */
public class DefaultTransferParser implements TransferParser {
    private ResourceContent resourceContent;
    
    public DefaultTransferParser(ResourceContent content) {
        this.resourceContent = content;
    }
    
    @Override
    public HttpTransfer parseTransfer(Method method, String preUrl) {

        HttpTransfer transfer = new HttpTransfer();

        transfer.setResultType(method.getReturnType());

        Annotation[] annotations = method.getAnnotations();

        

        Consumes consumes = method.getAnnotation(Consumes.class);
        if(consumes != null) {
            MediaHandler[] consumesHandler = resourceContent.getMediaHandlers(consumes.value());
            transfer.setConsumersHandlers(consumesHandler);
            transfer.setConsumersMediaType(consumes.value());
        } else {
            transfer.setConsumersHandlers(new MediaHandler[] {resourceContent.getMediaHandler(MediaType.TEXT_PLAIN)});
            transfer.setConsumersMediaType(new String[]{MediaType.TEXT_PLAIN});
        }


        Produces produces = method.getAnnotation(Produces.class);
        if(produces != null) {
            MediaHandler[] producesHandler = resourceContent.getMediaHandlers(produces.value());
            transfer.setProducesHandlers(producesHandler);
            transfer.setProducesMediaType(produces.value());
        } else {
            transfer.setProducesHandlers(new MediaHandler[] {resourceContent.getMediaHandler(MediaType.TEXT_PLAIN)});
            transfer.setProducesMediaType(new String[]{MediaType.TEXT_PLAIN});
        }


        transfer.setUrl(preUrl);

        String hasVerb = null;
        for(Annotation annotation : annotations) {
            HttpComment comment = resourceContent.getVerbComment(annotation);
            if(comment != null) {
                if(hasVerb != null) {
                    Class clz = method.getDeclaringClass();
                    throw new ResourceParseException(clz.getName() + "." + method.getName() + " exists veb : " + hasVerb + " , " + annotation);
                }

                transfer.setExecutor(new TransferExecutor(transfer, comment));
                hasVerb = annotation.toString();
            }
        }


        Parameter[] parameters = method.getParameters();
        for(int i = 0; i < parameters.length ; i ++) {
            Parameter parameter = parameters[i];
            PathParam pathParam = parameter.getAnnotation(PathParam.class);
            if(pathParam != null) {
                transfer.addParam(HttpParameter.newPathParameter(i, pathParam.value()));
                continue;
            } 


            FormParam formParam = parameter.getAnnotation(FormParam.class);

            if(formParam != null) {
                transfer.addParam(HttpParameter.newFormParameter(i, formParam.value()));
                continue;
            }


            transfer.addParam(HttpParameter.newBodyParameter(i));

        }

        transfer.setUrl(parseUri(method, preUrl));
        return transfer;

    }
    
    
    protected  String parseUri(Method method, String preUrl) {
        Path path = method.getAnnotation(Path.class);
        if(path != null) {
            preUrl += "/" +  path.value();
        }
        return preUrl;
    }
}
