package http.resource.parse;

import http.resource.http.HttpTransfer;

import java.lang.reflect.Method;

/**
 * Created by bin on 2016/12/11.
 */
public interface TransferParser {
    HttpTransfer parseTransfer(Method method, String preUrl);
}
