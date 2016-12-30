package spi;

import org.junit.Test;

import java.util.*;

/**
 * Created by bin.liang on 2016/12/19.
 */
public class ComparatorTest {

    private Comparator<User> getCompartor() {
        ServiceLoader<Comparator> serviceLoader
                = ServiceLoader.load(Comparator.class);
        for(Comparator service : serviceLoader)
        {
            return (Comparator<User>)service;
        }

        return null;
    }


    @Test
    public void test() {

        List<User> myList = new ArrayList<User>();
        myList.add(new User(2,"c"));
        myList.add(new User(7,"k"));
        myList.add(new User(6,"d"));
        myList.add(new User(5, "b"));



        Collections.sort(myList, getCompartor());

        for(User user : myList) {
            System.out.println(user);
        }

    }
}
