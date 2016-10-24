package com.example;

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

        String videoId = "F0043FFB21FB1ED59C33DC5901307461";

//        [A-Za-z0-9_-]*
        System.out.printf("toArray toArray=" + videoId.matches("[A-Za-z0-9]*"));
    }


}
