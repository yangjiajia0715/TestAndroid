package com.testandroid.yang.other.innerclass;

/**
 * Created by yangjiajia on 2018/3/12.
 */

public class Test {
//    private static final String TAG = "Test";

    @org.junit.Test
    public void main(){
        System.out.print("mmmmmmmmmmm");
//        Log.d("yang", "main: ");
    }

    public void test1(){
//        new Outer1.Inner1();
        Outer1 outer1 = new Outer1();
        Outer1.Inner1 inner1 = outer1.new Inner1();
    }
}
