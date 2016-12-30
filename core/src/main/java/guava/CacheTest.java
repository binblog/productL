package guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by bin.liang on 2016/12/29.
 */
public class CacheTest {
    public static void main(String[] args) throws ExecutionException {
        LoadingCache<Integer, String> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(MY_LISTENER)
                .build(
                        new CacheLoader<Integer, String>() {
                            public String load(Integer key) throws Exception {
                                return "str : " + key;
                            }
                        });


        System.out.println(graphs.get(3));
    }
}
