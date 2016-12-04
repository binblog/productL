package http.resource.http;


import http.resource.comment.HttpComment;
import http.resource.http.HttpTransfer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by bin on 2016/12/3.
 */
public class TransferExecutor {

    private  HttpComment comment ;
    private HttpTransfer transfer;
    


    public TransferExecutor(HttpTransfer transfer ,HttpComment comment) {
        this.comment = comment;
        this.transfer = transfer;
    }

    public String execute(Object[] args) {
        System.out.println("--- " + comment);


        String url = transfer.getUrl();
        List<HttpParam> params = transfer.getParams();
        
        
        
        Object bodyParam = null;
        for(int i = 0; i < params.size(); i++) {
            HttpParam param = params.get(i);
            if(param.getType() == 1 && args[i] != null) {
                url = url.replace("{"  +  param.getName()+ "}", args[i].toString());
            } else {
                bodyParam = args[i];
            }
        }

        System.out.println("++++++ url : " + url);

        try {
            return comment.excute(url, bodyParam);
        } catch (IOException e) {
            e.printStackTrace();
        }

//         String url = transfer.getUrl();

         /*Map<String, Object> pathParams = transfer.getPathParams();

         for(Map.Entry<String, Object> entry : pathParams.entrySet()) {
             url = url.replace("{" + entry.getKey() + "}", entry.getValue().toString());
         }*/


        System.out.println(comment);


        return null;
    }


    private void doExcute(String url, Object bodyParams, String verb) {
        switch (verb) {
            case "get" :

            case "post" :

            default:
                throw new IllegalArgumentException("un support verb : " + verb);
        }
    }
}
