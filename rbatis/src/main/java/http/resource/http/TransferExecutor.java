package http.resource.http;


import http.resource.comment.HttpComment;
import http.resource.comment.HttpRequest;
import http.resource.http.media.BytesWrapper;
import http.resource.http.media.MediaHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bin on 2016/12/3.
 */
public class TransferExecutor {

    private HttpComment comment ;
//    private String url;
//    private List<HttpParameter> params;
    private HttpTransfer transfer;

    


    public TransferExecutor(HttpTransfer transfer , HttpComment comment ) {
        this.comment = comment;
        this.transfer = transfer;
    }

    public Object execute(Object[] args) {

        String url = transfer.getUrl();
//        List<HttpParameter> params = transfer.getParams();
        

//        Object bodyParam = null;
        List<HttpParameter> pathParams = transfer.getPathParmas();
        for(int i = 0; i < pathParams.size(); i++) {
            HttpParameter param = pathParams.get(i);

            System.out.println("param : " + param);

            if(args[i] != null) {

                url = url.replace("{"  +  param.getName()+ "}", args[param.getIndex()].toString());

            }
        }

        HttpParameter bodyParam = transfer.getBodyParam();
        BytesWrapper bytes = null;
        if(bodyParam != null ) {
            MediaHandler[] producesHandlers = transfer.getProducesHandlers();
            
            for(MediaHandler handler : producesHandlers) {
                if(handler != null) {
                    bytes = handler.produce(args[bodyParam.getIndex()]);
                    break;
                }
            }
            
        }

        List<HttpParameter> formParams = transfer.getFormParmas();
        Map<String, Object> map = new HashMap<String, Object>();
        for(HttpParameter parameter : formParams) {
            map.put(parameter.getName(), args[parameter.getIndex()]);
        }

        String producesMediaType = null;
        if(!map.isEmpty()) {
            MediaHandler[] producesHandlers = transfer.getProducesHandlers();
            
            for(int i = 0; i < producesHandlers.length; i++) {
                MediaHandler handler = producesHandlers[i];
                if(handler != null) {
                    bytes = handler.produce(map);
                    producesMediaType = transfer.getProducesMediaType()[i];
                    break;
                }
            }
        }


        HttpRequest request = new HttpRequest();
        request.setBytesWrapper(bytes);
        request.setBytesWrapper(bytes);
        request.setConsumersMediaType(transfer.getConsumersMediaType());
        request.setProducesHandlers(transfer.getProducesHandlers());

        HttpResponse response = comment.execute(request);

        Class<?> resultType = transfer.getResultType();

        MediaHandler[] consumesHandler = transfer.getConsumersHandlers();



        Object resultBean = null;
        for(MediaHandler handler : consumesHandler) {   //!!! 需要返回content-type处理返回的内容
            if(handler != null) {
                resultBean = handler.consume(new BytesWrapper(response.getContent()), resultType);
                break;
            }
            
        }
        

        return resultBean;
    }


}
