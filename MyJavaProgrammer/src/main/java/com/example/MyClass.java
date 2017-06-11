package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class MyClass {
    public static void main(String[] args) throws FileNotFoundException {
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
//        testMapAddDelNull();

        testJdbc();

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");//不加后缀
        String url = bundle.getString("url");
        System.out.print("--------url=" + url);
    }

    //全部是 java.sql 下的
    private static void testJdbc() {
        try {
            //注册驱动（导包）
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/test2", "root", "yangjiajia");
            //编写sql
            String sql = "select * from category";

            //创建语句执行着
            PreparedStatement st = conn.prepareStatement(sql);

            //设置参数

            //执行sql
            ResultSet resultSet = st.executeQuery();

            //处理结果
            while (resultSet.next()) {
                String string = resultSet.getString("");
                System.out.println("------------------------string=" + string);
            }

            //释放资源  先创建的后释放
            resultSet.close();

            st.close();
            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testTest() {
        System.out.print("------------------");
        return;
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
        boolean remove22 = arrayList.remove(new TestUserInfo("dfdsf", 22));
        System.out.println("--------------arrayList---------------2--remove=" + remove);
        System.out.println("--------------arrayList---------------2--remove22=" + remove22);
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
