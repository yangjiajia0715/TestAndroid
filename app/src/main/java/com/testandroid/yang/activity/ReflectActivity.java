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
import com.testandroid.yang.retrofit.ApiServer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

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

    @OnClick({R.id.reflect_01, R.id.reflect_02, R.id.reflect_03, R.id.reflect_04, R.id.reflect_05})
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

                break;
            case R.id.reflect_05:

                break;
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
                System.out.println("方法名：" + method.getName());
                System.out.println("参数：" + a + " , " + b);

//                    GET get = method.getAnnotation(GET.class);
//                    System.out.println("注解：" + get.value());

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
