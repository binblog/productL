package http.resource.http;


import http.resource.comment.HttpComment;
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
        byte[] bytes = null;
        if(bodyParam != null ) {
            MediaHandler producesHandler = transfer.getProducesHandler();
            bytes = producesHandler.produce(args[bodyParam.getIndex()]);
        }

        List<HttpParameter> formParams = transfer.getFormParmas();
        Map<String, Object> map = new HashMap<String, Object>();
        for(HttpParameter parameter : formParams) {
            map.put(parameter.getName(), args[parameter.getIndex()]);
        }

        System.out.println(map);
        if(!map.isEmpty()) {
            MediaHandler producesHandler = transfer.getProducesHandler();
            bytes = producesHandler.produce(map);
        }



        HttpResponse response = comment.execute(url, transfer.getProducesMediaType(),  bytes);

        Class<?> resultType = transfer.getResultType();

        MediaHandler consumesHandler = transfer.getConsumersHandler();

        Object resultBean = consumesHandler.consume(response.getContent(), resultType);

        return resultBean;
    }


}
