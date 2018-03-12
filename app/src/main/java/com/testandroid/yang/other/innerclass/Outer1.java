package com.testandroid.yang.other.innerclass;

/**
 * Created by yangjiajia on 2018/3/12.
 */

public class Outer1 implements IFly{
    public int outaa = 1;

    @Override
    public void fly() {

    }

    /**
     * 成员内部类：
     * 不能声明静态的变量和方法
     *
     */
    class Inner1 implements IFly{
        //        static void getInneraaa();
        void getInnerbb() {
            getaa();
        }

        @Override
        public void fly() {

        }
    }

    void getaa() {
        new Inner1();
    }
}

class O3 {

}
