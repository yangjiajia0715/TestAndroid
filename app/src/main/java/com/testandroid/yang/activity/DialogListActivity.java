package com.testandroid.yang.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.fragment.ClearCacheFragment;
import com.testandroid.yang.fragment.DatePickerFragment;
import com.testandroid.yang.fragment.EmbeddableFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 各种 对话框
 * Created by yangjiajia on 2017/6/25 0025.
 */

public class DialogListActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "DialogListActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dialog0)
    TextView dialog0;
    @BindView(R.id.dialog1)
    TextView dialog1;
    @BindView(R.id.dialog2)
    TextView dialog2;
    @BindView(R.id.dialog3)
    TextView dialog3;
    @BindView(R.id.dialog4)
    TextView dialog4;
    @BindView(R.id.dialog5)
    TextView dialog5;
    @BindView(R.id.dialog6)
    TextView dialog6;
    @BindView(R.id.dialog7)
    TextView dialog7;
    @BindView(R.id.dialog8)
    TextView dialog8;
    @BindView(R.id.dialog9)
    TextView dialog9;
    @BindView(R.id.bottom_btn)
    TextView bottomBtn;
    @BindView(R.id.bottomLayout)
    LinearLayout bottomLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, DialogListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_list);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        toolbar.setTitle("对话框相关");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.dialog0, R.id.dialog1, R.id.dialog2, R.id.dialog3, R.id.dialog4, R.id.dialog5, R.id.dialog6, R.id.dialog7, R.id.dialog8, R.id.dialog9, R.id.bottom_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog0:
                Log.d(TAG, "onViewClicked: digitsOnly=" + TextUtils.isDigitsOnly("12345") + " Only=" + TextUtils.isDigitsOnly("1@2345"));
                Log.d(TAG, "onViewClicked: digitsOnly=" + TextUtils.concat("111", "-", "222"));

                URLUtil.isValidUrl("");
//                URLUtil.isDataUrl()
                URLUtil.isHttpsUrl("");
                URLUtil.isNetworkUrl("");

                DatePickerFragment.newInstance().show(getFragmentManager(), "DatePicker");
                break;
            case R.id.dialog1:
                ClearCacheFragment cacheFragment = ClearCacheFragment.newInstance();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//没有卵用
                cacheFragment.show(transaction, "clearCache");
//                transaction.commit();
//                cacheFragment.show(getFragmentManager(), "clearCache");
                break;
            case R.id.dialog2:
                new AlertDialog.Builder(this)
                        .setTitle("性别")
                        .setSingleChoiceItems(R.array.sex, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogListActivity.this, "which " + which, Toast.LENGTH_SHORT).show();
                            }
                        })
//                        .setItems(R.array.sex, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(DialogListActivity.this, "which " + which, Toast.LENGTH_SHORT).show();
//                            }
//                        })
                        .create()
                        .show();
                break;
            case R.id.dialog3:
                EmbeddableFragment embeddableFragment = EmbeddableFragment.newInstance();
                // The device is smaller, so show the fragment fullscreen
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                // For a little polish, specify a transition animation
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                // To make it fullscreen, use the 'content' root view as the container
                // for the fragment, which is always the root view for the activity
                fragmentTransaction.add(android.R.id.content, embeddableFragment)
                        .addToBackStack(null).commit();

//                embeddableFragment.show(getFragmentManager(), "embeddableFrg");
                break;
            case R.id.dialog4:
                String[] sex = getResources().getStringArray(R.array.sex);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sex);
                new AlertDialog.Builder(this)
                        .setTitle("性别")
                        .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogListActivity.this, "which " + which, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.dialog5:

                android.support.v7.app.AlertDialog.Builder builder1 = new android.support.v7.app.AlertDialog.Builder(this);
                builder1.setView(R.layout.dialog_custom_01);
                builder1.create().show();

            case R.id.dialog6:
                break;
            case R.id.dialog7:
                break;
            case R.id.dialog8:
                break;
            case R.id.dialog9:
                break;
            case R.id.bottom_btn:
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: year=" + year);
        Log.d(TAG, "onDateSet: month=" + month);
        Log.d(TAG, "onDateSet: dayOfMonth=" + dayOfMonth);
    }
}
