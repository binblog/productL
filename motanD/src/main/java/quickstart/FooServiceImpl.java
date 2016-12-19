package quickstart;

/**
 * Created by bin on 2016/12/17.
 */
public class FooServiceImpl implements  FooService{
    public String hello(String name) {
        System.out.println(name + " invoked rpc service");
        return "hello " + name;
    }
}
