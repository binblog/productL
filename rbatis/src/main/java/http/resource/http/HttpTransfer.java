package http.resource.http;

import http.resource.http.media.MediaHandler;

import javax.ws.rs.client.InvocationCallback;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 2016/12/3.
 */
public class HttpTransfer {

    private String url;

    private TransferExecutor executor;

    private List<HttpParameter> params = new ArrayList<HttpParameter>();

    private Class<?> resultType;
    private MediaHandler consumersHandler;
    private String consumersMediaType;
    private MediaHandler producesHandler;
    private String producesMediaType;

    private InvocationCallback callback;


    public TransferExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(TransferExecutor executor) {
        this.executor = executor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addParam(HttpParameter param) {
        params.add(param);
    }

    public HttpParameter getBodyParam() {
        for(HttpParameter parameter : params) {
            if(parameter.getType() == HttpParameterType.BODY_PARAMETER) {
                return parameter;
            }
        }
        return null;
    }

    public List<HttpParameter> getPathParmas() {
        List<HttpParameter> pathParams = new ArrayList<HttpParameter>();
        for(HttpParameter parameter : params) {
            if(parameter.getType() == HttpParameterType.PATH_PARAMETER) {
                pathParams.add(parameter);
            }
        }
        return pathParams;
    }

    public List<HttpParameter> getFormParmas() {
        List<HttpParameter> formParams = new ArrayList<HttpParameter>();
        for(HttpParameter parameter : params) {
            if(parameter.getType() == HttpParameterType.FORM_PARAMETER) {
                formParams.add(parameter);
            }
        }
        return formParams;
    }

    public List<HttpParameter> getParams() {
        return params;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public MediaHandler getConsumersHandler() {
        return consumersHandler;
    }

    public void setConsumersHandler(MediaHandler consumersHandler) {
        this.consumersHandler = consumersHandler;
    }

    public MediaHandler getProducesHandler() {
        return producesHandler;
    }

    public void setProducesHandler(MediaHandler producesHandler) {
        this.producesHandler = producesHandler;
    }

    public String getConsumersMediaType() {
        return consumersMediaType;
    }

    public void setConsumersMediaType(String consumersMediaType) {
        this.consumersMediaType = consumersMediaType;
    }

    public String getProducesMediaType() {
        return producesMediaType;
    }

    public void setProducesMediaType(String producesMediaType) {
        this.producesMediaType = producesMediaType;
    }
}
