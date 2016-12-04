package http.resource.proxy;

import http.resource.comment.HttpComment;
import http.resource.http.HttpResource;
import http.resource.http.HttpTransfer;
import http.resource.http.TransferExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by bin on 2016/12/3.
 */
public class ResourceProxy implements InvocationHandler {
    private HttpResource resource;

    public ResourceProxy(HttpResource resource) {
        this.resource = resource;
    }

    public void setResource(HttpResource resource) {
        this.resource = resource;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用到了 + " + method.getName());
        HttpTransfer transfer = resource.getTransfer(method.getName());


        TransferExecutor executor = transfer.getExecutor();
        executor.execute(args);
        

//        TransferExecutor executer = resource.getExecute();

//        String result = executer.execute(args);
//
//        result hanler = executer.getresultHanlder(method);
//
//
//
//        if(result.isSuccess) {
//            parsresult();
//        } else {
//            parseerro();
//        }

        return null;
    }
}

