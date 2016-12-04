package http.resource.rest;

import java.lang.annotation.*;

/**
 * Created by bin on 2016/12/3.
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GET {
}
