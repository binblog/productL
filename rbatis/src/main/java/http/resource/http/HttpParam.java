package http.resource.http;

/**
 * Created by bin on 2016/12/3.
 */
public class HttpParam {
    private final  static int BODY_TYPE = 2;
    private final  static  int PATH_TYPE = 1;
    private String name;
    private int type;


    public HttpParam(String name) {
        this.name = name;
        type = 1;
    }

    public HttpParam() {

        type = 2;
    }

    public static int getBodyType() {
        return BODY_TYPE;
    }

    public static int getPathType() {
        return PATH_TYPE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
