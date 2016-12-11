package http.resource.parse;

import http.resource.DefaultResourceContent;
import http.resource.http.HttpResource;
import http.resource.http.HttpTransfer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.Path;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class DefaultResourceParser implements ResourceParser {

    private DefaultTransferParser transferParser;
    private DefaultResourceContent resourceContent;

    protected final Log logger = LogFactory.getLog(getClass());

    public DefaultResourceParser(DefaultResourceContent content) {
        this.resourceContent = content;
        transferParser = new DefaultTransferParser(content);
    }


    @Override
    public HttpResource parse(Class<?> clazz) {

        HttpResource resource = new HttpResource();

        Annotation[] annotations = clazz.getAnnotations();
        String resourceUri = resourceContent.getBaseUri();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Path) {
                resourceUri += ((Path) annotation).value();
            }
        }

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            HttpTransfer transfer = transferParser.parseTransfer(method, resourceUri);

            logger.debug("parse transfer : " +  transfer);
            
            resource.addTransfer(method.getName(), transfer);
        }

        return resource;
    }


    public DefaultTransferParser getTransferParser() {
        return transferParser;
    }

    public void setTransferParser(DefaultTransferParser transferParser) {
        this.transferParser = transferParser;
    }
}
