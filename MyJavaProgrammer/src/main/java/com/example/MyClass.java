package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyClass {
    public static void main(String []args){
//        System.out.print("yangjiajia");
////        System.out.println();
//        System.out.printf("");
//        //快捷键 sout souf
//        List<Integer> integers = new ArrayList<>();
//        integers.add(0);
//        integers.add(2);
//        integers.add(5);
//        integers.add(4);
//        integers.add(1);
//        Collections.sort(integers, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//
//        System.out.printf("toArray toArray=" + integers.toArray());

//        String videoId = "F0043FFB21FB1ED59C33DC5901307461";
//
////        [A-Za-z0-9_-]*
//        System.out.printf("toArray toArray=" + videoId.matches("[A-Za-z0-9]*"));
//        testArrayAddDelNull();
        testMapAddDelNull();
    }

    /**
     * add null
     * del null
     */
    private static void testArrayAddDelNull() {
        List<TestUserInfo> arrayList = new ArrayList<>();
        arrayList.add(new TestUserInfo("张三1", 11));
        arrayList.add(new TestUserInfo("张三2", 13));
        arrayList.add(new TestUserInfo("张三3", 15));
        arrayList.add(null);
        arrayList.add(null);

        System.out.println("arrayList arrayList size=" + arrayList.size() + "" + arrayList.toString());
        arrayList.remove(null);
        arrayList.remove(null);
        boolean remove = arrayList.remove(null);
        boolean remove22 =  arrayList.remove(new TestUserInfo("dfdsf",22));
        System.out.println("--------------arrayList---------------2--remove=" + remove );
        System.out.println("--------------arrayList---------------2--remove22=" + remove22 );
        System.out.println("arrayList arrayList size=" + arrayList.size() + "" + arrayList.toString());
    }

    /**
     * add null
     * del null
     */
    private static void testMapAddDelNull() {
        HashMap<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
//        map.put(null, null);
//        map.put(null, null);
        map.remove(null);

        System.out.println("--------testMapAddDelNull--------size=" + map.size() + ",map=" + map.toString());
    }


}
