package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.House;
import com.testandroid.yang.common.IServer;
import com.testandroid.yang.extenddd.Apple;
import com.testandroid.yang.extenddd.Fruit;
import com.testandroid.yang.extenddd.Plate;
import com.testandroid.yang.extenddd.RedApple;
import com.testandroid.yang.extenddd.TestType;
import com.testandroid.yang.retrofit.ApiServer;
import com.testandroid.yang.util.UtilsCopySource;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.GET;

/**
 * 反射
 * Created by yangjiajia on 2017/4/28 0028.
 */

public class ReflectActivity extends BaseActivity {
    private static final String TAG = "ReflectActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.reflect_01)
    TextView reflect01;
    @BindView(R.id.reflect_02)
    TextView reflect02;
    @BindView(R.id.reflect_03)
    TextView reflect03;
    @BindView(R.id.reflect_04)
    TextView reflect04;
    @BindView(R.id.reflect_05)
    TextView reflect05;

    public static void start(Context context) {
        Intent starter = new Intent(context, ReflectActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        setTitle("Reflect");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.reflect_01, R.id.reflect_02, R.id.reflect_03, R.id.reflect_04,
            R.id.reflect_05, R.id.reflect_06, R.id.reflect_07, R.id.reflect_08})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reflect_01:
                test01();
                break;
            case R.id.reflect_02:
                testProxy();
                break;
            case R.id.reflect_03:
                basicReflect();
                break;
            case R.id.reflect_04:
                utils();
                break;
            case R.id.reflect_05:
                typeAll();
                break;
            case R.id.reflect_06:
                break;
            case R.id.reflect_07:
                break;
            case R.id.reflect_08:
                break;
        }
    }

    // 泛型介绍：http://loveshisong.cn/%E7%BC%96%E7%A8%8B%E6%8A%80%E6%9C%AF/2016-02-16-Type%E8%AF%A6%E8%A7%A3.html
    private void typeAll() {
        //TYPE1: ParameterizedType
        try {
            Field f = TestType.class.getDeclaredField("userMap");
            System.out.println(f.getGenericType());                               // java.util.Map<java.lang.String, com.testandroid.yang.common.User>
            System.out.println(f.getGenericType() instanceof ParameterizedType);  // true
            ParameterizedType pType = (ParameterizedType) f.getGenericType();
            System.out.println(pType.getRawType());                               // interface java.util.Map
            for (Type type : pType.getActualTypeArguments()) {
                System.out.println(type);                                         // class java.lang.String class com.testandroid.yang.common.User
            }
//            返回是谁的member.
            System.out.println(pType.getOwnerType());                             // null
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "typeAll: ----------------TypeVariable-------------------");

        // 获取字段的类型
        try {
            Field fk = TestType.class.getDeclaredField("key");
            Field fv = TestType.class.getDeclaredField("value");

            System.out.println(fk.getGenericType() instanceof TypeVariable);//true
            System.out.println(fv.getGenericType() instanceof TypeVariable);//true

            TypeVariable keyType = (TypeVariable) fk.getGenericType();
            TypeVariable valueType = (TypeVariable) fv.getGenericType();
            // getName 方法
            System.out.println("getName 方法:");
            System.out.println(keyType.getName());                 // K
            System.out.println(valueType.getName());               // V
            // getGenericDeclaration 方法
            System.out.println(keyType.getGenericDeclaration());   // class com.test.TestType
            System.out.println(valueType.getGenericDeclaration()); // class com.test.TestType
            // getBounds 方法
            System.out.println("K 的上界:");                        // 有两个
            for (Type type : keyType.getBounds()) {                // interface java.lang.Comparable
                System.out.println(type);                          // interface java.io.Serializable
            }
            System.out.println("V 的上界:");                        // 没明确声明上界的, 默认上界是 Object
            for (Type type : valueType.getBounds()) {              // class java.lang.Object
                System.out.println(type);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "typeAll: ----------------GenericArrayType-------------------");
        Method[] declaredMethods = TestType.class.getDeclaredMethods();
        Method method = declaredMethods[0];
        Type[] genericParameterTypes = method.getGenericParameterTypes();

//        List<String>[] lists = new List<String>[4];
        for (Type type : genericParameterTypes) {
            boolean isGenericArrayType = type instanceof GenericArrayType;

            Log.d(TAG,"---------GenericArrayType " + isGenericArrayType);
            if (isGenericArrayType) {
                GenericArrayType arrayType = (GenericArrayType) type;
                Type componentType = arrayType.getGenericComponentType();
                if (componentType instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) componentType;
                    Log.d(TAG,"typeVariable " + typeVariable.getName());
                }

                if (componentType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) componentType;
                    Log.d(TAG,"parameterizedType " + parameterizedType.getRawType());
                }
            }

        }
    }

    private void utils() {
        boolean isR = true;
//        List<String>[]  lists = new ArrayList<String>[3];

        String[] ss = new String[3];

//        Plate<Fruit> plate = new Plate<Apple>(new Apple());

        //pecs
        Plate<? extends Fruit> plate1 = new Plate<>(new Apple());
        Fruit fruit = plate1.get();

        //========================================================

        Plate<? super Apple> plateaa = new Plate<>(new Apple());
        plateaa.set(new RedApple());
        plateaa.set(new Apple());
        Object object1 = plateaa.get();

//        plateaa.set(new Object());//error
        //pecs
        Plate<? super Apple> plate = new Plate<>(new Object());
        plate.set(new Apple());
        plate.set(new RedApple());
        Object object = plate.get();

//        Log.d(TAG, "utils: fruit=" + fruit);

        if (isR)
            return;
        //extends T 表示类型的上届 需要是它的子类
        //super T 表示类型的下界 Object

        //PECS原则
//        最后看一下什么是PECS（Producer Extends Consumer Super）原则，已经很好理解了：
//
//        频繁往外读取内容的，适合用上界Extends。
//        经常往里插入的，适合用下界Super。
        Map<String, ? super Runnable> map = new HashMap<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        map.put("kkkk", runnable);

        Class<? extends Map> aClass = map.getClass();

        TypeVariable<? extends Class<? extends Map>>[] parameters = aClass.getTypeParameters();

        for (TypeVariable<? extends Class<? extends Map>> parameter : parameters) {
            String name = parameter.getName();
            Log.d(TAG, "utils: name=" + name);
            Type[] bounds = parameter.getBounds();
            for (Type bound : bounds) {
                Log.d(TAG, "utils: bound=" + bound);
            }
        }

        Log.d(TAG, "utils: -------------------------------------");

        Class<House> houseClass = House.class;
        try {
            Method method = houseClass.getDeclaredMethod("mappp");
            Class<?> returnType = method.getReturnType();
            Type genericReturnType = method.getGenericReturnType();

            Log.d(TAG, "utils:  returnType=" + returnType);//interface java.util.Map

            Log.d(TAG, "utils: return genericReturnType=" + genericReturnType);//java.util.Map<java.lang.String, ? extends java.lang.Runnable>
            boolean isParameterized = genericReturnType instanceof ParameterizedType;

            Log.d(TAG, "utils:  isParameterized=" + isParameterized);

            if (isParameterized) {
                ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type type : actualTypeArguments) {
                    Log.d(TAG, "utils: type=" + type + "   instanceof=" + (type instanceof WildcardType));
                    if (type instanceof WildcardType) {

                        Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                        for (Type upperBound : upperBounds) {
//                            Log.d(TAG, "utils: upperBound=" + upperBound.getClass().getName());//java.lang.Class
                            Log.d(TAG, "utils: upperBound 11=" + upperBound);
                        }

//                        ? super Runnable
                        Type[] lowerBounds = ((WildcardType) type).getLowerBounds();
                        for (Type lower : lowerBounds) {
                            Log.d(TAG, "utils: lower=" + lower);
                        }
                    }
                }
            }

//            Log.d(TAG, "utils: return instanceof=" + (genericReturnType instanceof Class<?>));//false
//            Log.d(TAG, "utils: return ==" + (returnType == Map.class)); // true

            Log.d(TAG, "utils: ---------------------------");

            Log.d(TAG, "utils: return returnType=" + UtilsCopySource.getRawType(returnType));
            Log.d(TAG, "utils: return genericReturnType=" + UtilsCopySource.getRawType(genericReturnType));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void basicReflect() {
        Class<?> aClass = ApiServer.class;
        try {
            Method method = aClass.getDeclaredMethod("add", int.class, int.class);
            Log.d(TAG, "basicReflect: getName=" + method.getName());

            Type[] genericParameterTypes = method.getGenericParameterTypes();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();

            Log.d(TAG, "basicReflect: genericParameterTypes=" + genericParameterTypes.length);
            Log.d(TAG, "basicReflect: parameterAnnotations=" + parameterAnnotations.length);
//            Log.d(TAG, "basicReflect: parameterAnnotations=" + parameterAnnotations);

            Type returnType = method.getGenericReturnType();
            Log.d(TAG, "basicReflect: returnType=" + returnType);
            Annotation[] annotations = method.getAnnotations();

            for (Annotation annotation : annotations) {
//                Class<? extends Annotation> type = annotation.annotationType();
                Log.d(TAG, "basicReflect: annotation=" + annotation);
                if (annotation instanceof GET) {
                    String value = ((GET) annotation).value();
                    Log.d(TAG, "basicReflect: value=" + value);
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private void testProxy() {
        Class aClass = IServer.class;
        Log.d(TAG, "invoke: aClass=" + aClass);
        Log.d(TAG, "invoke: getClassLoader=" + aClass.getClassLoader());

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Integer a = (Integer) args[0];
                Integer b = (Integer) args[1];
                Log.d(TAG,"方法名：" + method.getName());
                Log.d(TAG,"参数：" + a + " , " + b);

//                    GET get = method.getAnnotation(GET.class);
//                    Log.d(TAG,"注解：" + get.value());

                try {
//                    ApiServer apiServer = (ApiServer) proxy;
                    return method.invoke(proxy, args);//stackoverflow
                } catch (InvocationTargetException e) {
                    e.getCause().printStackTrace();
                    Log.d(TAG, "testProxy: e=" + e);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "testProxy: age=================");
//                    return  method.invoke(proxy, args);
                return 33333333;
            }
        };

        ApiServer instance = (ApiServer) Proxy.newProxyInstance(ApiServer.class.getClassLoader(), new Class<?>[]{ApiServer.class}, handler);

        int add = instance.add(3, 5);
        Log.d(TAG, "testProxy: add=" + add);

    }

    private void test01() {
        //获取 反射类
        try {
            //houseClass=class com.testandroid.yang.common.House
//            Class<?> aClass = Class.forName("com.testandroid.yang.common.House");
//            House house = new House();
//            Class<? extends House> aClass1 = house.getClass();
            Class houseClass = House.class;

            Log.d(TAG, "test01: houseClass=" + houseClass);

            String name = houseClass.getName();
            String simpleName = houseClass.getSimpleName();
            Log.d(TAG, "test01: name=" + name);
            Log.d(TAG, "test01: simpleName=" + simpleName);

            Method[] methods = houseClass.getMethods();

            for (Method method : methods) {
                String methodName = method.getName();
                Log.d(TAG, "test01: methodName=" + methodName);
            }

            Log.d(TAG, "test01: ========================");

            Method[] declaredMethods = houseClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                String methodName = declaredMethod.getName();
                Log.d(TAG, "test01: declaredMethod=" + methodName);
            }

            Constructor[] constructors = houseClass.getConstructors();

            for (Constructor constructor : constructors) {
                Class[] parameterTypes = constructor.getParameterTypes();
                Log.d(TAG, "test01: parameterTypes=" + parameterTypes.length);
                if (parameterTypes.length == 2) {
                    House instance = (House) constructor.newInstance("错题会", "全都会");
                    String string = instance.toString();
                    Log.d(TAG, "test01: string=" + string);

                    Method method = houseClass.getDeclaredMethod("aa", String.class);
                    Log.d(TAG, "test01: getName=" + method.getName());
                    Log.d(TAG, "test01: isAccessible=" + method.isAccessible());
                    method.setAccessible(true);
                    Log.d(TAG, "test01: isAccessible=" + method.isAccessible());

                    method.invoke(instance, "呵呵哈哈哈");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "test01: e=" + e);
        }
    }
}
