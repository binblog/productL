package http.resource.rest;

import java.lang.annotation.*;

/**
 * Created by bin.liang on 2016/12/2.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Path {
    String value();
}
