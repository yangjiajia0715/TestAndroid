package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyClass {
    public static void main(String []args){
        System.out.print("yangjiajia");
//        System.out.println();
        System.out.printf("");
        //快捷键 sout souf
        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(2);
        integers.add(5);
        integers.add(4);
        integers.add(1);
        Collections.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        System.out.printf("toArray toArray=" + integers.toArray());
    }


}
