package http.resource.parse;

import http.resource.Configuration;
import http.resource.comment.HttpComment;
import http.resource.http.HttpParam;

import http.resource.http.HttpResource;
import http.resource.http.HttpTransfer;
import http.resource.http.TransferExecutor;
import http.resource.rest.GET;
import http.resource.rest.Path;
import http.resource.rest.PathParam;
import http.resource.rest.Post;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class ClassParser {
    private Class<?> clazz;
    private Configuration configuration;

    public ClassParser(Configuration configuration, Class<?> clazz) {
        this.clazz = clazz;
        this.configuration = configuration;
    }








    private HttpTransfer parseMethod(Method method, String preUrl) {

        HttpTransfer transfer = new HttpTransfer();

        Annotation[] annotations = method.getAnnotations();

        boolean hasVerb = false;

        Parameter[] parameters = method.getParameters();

        for(Parameter parameter : parameters) {
            PathParam annotation = parameter.getAnnotation(PathParam.class);
            if(annotation != null) {
                transfer.addParam(new HttpParam(annotation.value()));
            } else {
                transfer.addParam(new HttpParam());
            }
        }


        Path pathAn = method.getAnnotation(Path.class);
        if(pathAn != null) {
            System.out.println("//////////// " + pathAn.value());
            transfer.setUrl(preUrl + "/" + pathAn.value());
        }
        

        for(Annotation annotation : annotations) {
//            String verb  = getVerb(annotation);
            HttpComment comment = null;
            try {
                System.out.println(" ann1111 :  " + annotation.annotationType().getName());
                
                Class<? extends  HttpComment> commentClazz = configuration.getVerbComment(annotation);
                if(commentClazz ==  null) {
                    continue;
                }

                System.out.println("找到了");
                comment = commentClazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            } 

            if(comment != null) {    // verbUtils ?
                if(hasVerb) {
                    throw new RuntimeException();
                }


                //transfer.setVerb(verb);
                  transfer.setExecutor(new TransferExecutor(transfer, comment));

                hasVerb = true;
            }

            if(annotation instanceof  Path) {
                preUrl += "/" +  ((Path) annotation).value();
            }
        }

//        transfer.setUrl(preUrl);
        


        return transfer;

    }

    private String getVerb(Annotation annotation) {
        if(annotation instanceof  GET) {
            return "get";
        } else if(annotation instanceof Post) {
            return "post";
        }
        return null;

    }


    public HttpResource parse() {
        Annotation[] annotations = clazz.getAnnotations();

        String id = clazz.getName();

        String preUrl = configuration.getPreUrl();
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



        configuration.registerResource(id, resource);
        return resource;


    }





    private boolean isVerb(Annotation annotation) {
        if(annotation instanceof Post || annotation instanceof GET) {
            return true;
        }

        return false;
    }

}
