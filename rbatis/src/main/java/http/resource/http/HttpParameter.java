package http.resource.http;

/**
 * Created by bin on 2016/12/3.
 */
public class HttpParameter {

    private String name;
    private int index;

    private HttpParameterType type;

//    private Class<?> classType;


    private HttpParameter() {

    }

    public static HttpParameter newPathParameter(int index, String name) {
        HttpParameter parameter = new HttpParameter();
        parameter.setName(name);
        parameter.setIndex(index);
        parameter.setType(HttpParameterType.PATH_PARAMETER);
        return parameter;
    }

    public static HttpParameter newBodyParameter(int index) {
        HttpParameter parameter = new HttpParameter();
        parameter.setType(HttpParameterType.BODY_PARAMETER);
        parameter.setIndex(index);
        return parameter;
    }

    public static HttpParameter newFormParameter(int index, String name) {
        HttpParameter parameter = new HttpParameter();
        parameter.setName(name);
        parameter.setType(HttpParameterType.FORM_PARAMETER);
        parameter.setIndex(index);
        return parameter;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }


    public HttpParameterType getType() {
        return type;
    }

    public void setType(HttpParameterType type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "HttpParameter{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
