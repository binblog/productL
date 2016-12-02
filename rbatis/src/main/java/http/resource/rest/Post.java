package http.resource.rest;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.*;

/**
 * Created by bin.liang on 2016/12/2.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod(HttpMethod.POST)
@Documented
public @interface Post {
}
