package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.testandroid.yang.R;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 常见的排序的算法：
 * 快速排序：
 * 堆排：
 * 冒泡排序：在要排序的一组数中，对当前还没排序的范围内的全部数，自上而下相邻的两个数进行比较和调整，
 * // 让最大的数下沉，让最小的数上移。
 * Created by yangjiajia on 2018/3/8.
 * <p>
 * 排序的种类：
 * 选择排序：简单选择排序，堆排序 nlog2n
 * 交换排序：冒泡排序，快速排序 nlog2n
 * 插入排序：直接插入排序，希尔排序
 * 归并排序 nlog2n
 * 基数排序
 */

public class SortActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_1)
    Button mBtn1;
    @BindView(R.id.btn_2)
    Button mBtn2;
    @BindView(R.id.btn_3)
    Button mBtn3;
    @BindView(R.id.btn_4)
    Button mBtn4;
    @BindView(R.id.btn_5)
    Button mBtn5;

    public static void start(Context context) {
        Intent starter = new Intent(context, SortActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mToolbar.setTitle("排序");

    }

    @Override
    public void initData() {


    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                int[] datas = {11, 55, 22, 44, 100, 6};
//                sortByMaoPao(datas);
                bubbleSortYang(datas);
                mBtn5.setText(Arrays.toString(datas));
                break;
            case R.id.btn_2:
                int[] datas2 = {11, 525, 22, 434, 100, 6};
                quickSort(datas2, 0, datas2.length - 1);
                mBtn5.setText(Arrays.toString(datas2));
                break;
            case R.id.btn_3:
                int[] datas3 = {11, 525, 22, 434, 100, 6666};
                selectSort(datas3);
                mBtn5.setText(Arrays.toString(datas3));
                break;
            case R.id.btn_4:

                break;
            case R.id.btn_5:

                break;
        }
    }

    /**
     * 简单选择排序
     */
    void selectSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[minIndex] > a[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = a[minIndex];
                a[minIndex] = a[i];
                a[i] = temp;
            }
//            int temp =
        }
    }

    /**
     * 冒泡排序
     */
    private void sortByMaoPao(int[] arr) {
//        int max;
//        int temp;
////        for (int i = datas.length - 1; i > 0; i--) {
//        for (int i = 0; i < datas.length; i++) {
//            max = datas[0];
//            for (int j = i; j < datas.length; j++) {
//                if (max > datas[j]) {
//                    temp = datas[i];
//                    datas[i] = datas[j];
//                    datas[j] = temp;
//                }
//            }
//        }

        //减少排序趟数
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            for (int i = 0; i < high; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            high--;
        }
    }

    void Bubble_1(int r[], int n) {
        int i = n - 1;  //初始时,最后位置保持不变
        while (i > 0) {
            int pos = 0; //每趟开始时,无记录交换
            for (int j = 0; j < i; j++)
                if (r[j] > r[j + 1]) {
                    pos = j; //记录交换的位置
                    int tmp = r[j];
                    r[j] = r[j + 1];
                    r[j + 1] = tmp;
                }
            i = pos; //为下一趟排序作准备
        }

        int a[] = {};


    }

    void bubbleSort(int a[], int n) {
        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }

    void bubbleSortYang(int a[]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 1,选一个基准元素，
     * 2，通过一趟排序讲待排记录分成两部分，一部分比基准元素小，另一部分比基准元素大
     * 3，两部分数据进行同样的操作
     */
    void quickSort(int a[], int low, int high) {
        if (low < high) {//递归会导致栈溢出异常
            int middle = getMiddle(a, low, high);
            quickSort(a, 0, middle - 1);
            quickSort(a, middle + 1, high);
        }
    }

    private int getMiddle(int[] a, int low, int high) {
        int key = a[low];
        while (low < high) {
            while (low < high && a[high] >= key) {
                high--;
            }
            a[low] = a[high];

            while (low < high && a[low] <= key) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = key;
        return low;
    }


}
