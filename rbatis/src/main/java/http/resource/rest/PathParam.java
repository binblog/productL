package http.resource.rest;

import java.lang.annotation.*;

/**
 * Created by bin on 2016/12/3.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathParam {
    String value();
}
