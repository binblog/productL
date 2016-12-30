package str;

import org.junit.Test;

/**
 * Created by bin.liang on 2016/12/26.
 */
public class StringTest {

    @Test
    public void test1() {
        String str1 = "string";
        String str2 = new String("string");
        String str3 = str2.intern();

        System.out.println(str1==str2);//#1 false
        System.out.println(str1==str3);//#2 true
    }


    @Test
    public void test2() {
        String baseStr = "baseStr";
        final String baseFinalStr = "baseStr";

        String str1 = "baseStr01";
        String str2 = "baseStr"+"01";   // 字面量相加，编译时转化为常量baseStr01
        String str3 = baseStr + "01";   // 通过stringBuilder.append()生成的结果
        String str4 = baseFinalStr+"01";    // final字段，编译时转化为常量baseStr01
        String str5 = new String("baseStr01").intern(); //

        System.out.println(str1 == str2);//#3
        System.out.println(str1 == str3);//#4
        System.out.println(str1 == str4);//#5
        System.out.println(str1 == str5);//#6
    }

    @Test
    public void test3() {
        String str1 = "str01";
        String str2 = new String("str")+new String("01");
        String str3 = str2.intern();

        System.out.println(str2==str1);//#7
        System.out.println(str3==str1);
    }
}
