package http.resource.http;

import http.resource.http.media.MediaHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bin on 2016/12/3.
 */
public class HttpTransfer {

    private String url;

    private TransferExecutor executor;

    private List<HttpParameter> params = new ArrayList<HttpParameter>();

    private Class<?> resultType;
    private MediaHandler[] consumersHandlers;
    private String[] consumersMediaType;
    private MediaHandler[] producesHandlers;
    private String[] producesMediaType;

//    private InvocationCallback callback;


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

    public MediaHandler[] getConsumersHandlers() {
        return consumersHandlers;
    }

    public void setConsumersHandlers(MediaHandler[] consumersHandlers) {
        this.consumersHandlers = consumersHandlers;
    }

    public MediaHandler[] getProducesHandlers() {
        return producesHandlers;
    }

    public void setProducesHandlers(MediaHandler[] producesHandlers) {
        this.producesHandlers = producesHandlers;
    }

    public String[] getConsumersMediaType() {
        return consumersMediaType;
    }

    public void setConsumersMediaType(String[] consumersMediaType) {
        this.consumersMediaType = consumersMediaType;
    }

    public String[] getProducesMediaType() {
        return producesMediaType;
    }

    public void setProducesMediaType(String[] producesMediaType) {
        this.producesMediaType = producesMediaType;
    }

    @Override
    public String toString() {
        return "HttpTransfer{" +
                "url='" + url + '\'' +
                ", executor=" + executor +
                ", params=" + params +
                ", resultType=" + resultType +
                ", consumersHandlers=" + Arrays.toString(consumersHandlers) +
                ", consumersMediaType=" + Arrays.toString(consumersMediaType) +
                ", producesHandlers=" + Arrays.toString(producesHandlers) +
                ", producesMediaType=" + Arrays.toString(producesMediaType) +
                '}';
    }
}
