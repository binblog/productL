package spi;

import java.util.Comparator;

/**
 * Created by bin.liang on 2016/12/19.
 */
public class ComparatorProvider  implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getAge() - o2.getAge();
    }
}
