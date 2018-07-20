package com.testandroid.yang.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.dialog.AnswerProgressDialog;
import com.testandroid.yang.fragment.ClearCacheFragment;
import com.testandroid.yang.fragment.DatePickerFragment;
import com.testandroid.yang.fragment.EmbeddableFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 各种 对话框
 * Created by yangjiajia on 2017/6/25 0025.
 */

public class DialogListActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "DialogListActivity";

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
    @BindView(R.id.planets_spinner)
    Spinner planetsSpinner;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private PopupWindow popupWindow2;
    private ListView listView;

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
        mToolbar.setTitle("对话框相关");
    }

    @Override
    public void initData() {
        spinner();
    }

    private void spinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, R.layout.simple_spinner_item_new);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
//        dropDownViewTheme.
        planetsSpinner.setAdapter(adapter);
        planetsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object itemAtPosition = parent.getItemAtPosition(position);
                Log.d(TAG, "onItemSelected: " + itemAtPosition);

                String[] stringArray = getResources().getStringArray(R.array.planets_array);
                Toast.makeText(DialogListActivity.this, "选择 " + stringArray[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(DialogListActivity.this, "未选择", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.dialog0, R.id.dialog1, R.id.dialog2, R.id.dialog3, R.id.dialog4,
            R.id.dialog5, R.id.dialog6, R.id.dialog7, R.id.dialog8, R.id.dialog9,
            R.id.bottom_btn})
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

                Calendar calendar = Calendar.getInstance();

            case R.id.dialog6:
                Calendar calendar1 = Calendar.getInstance();
                com.fourmob.datetimepicker.date.DatePickerDialog datePickerDialog = com.fourmob.datetimepicker.date.DatePickerDialog.newInstance(new com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(com.fourmob.datetimepicker.date.DatePickerDialog datePickerDialog, int year, int month, int day) {

                    }
                }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show(getSupportFragmentManager(), "");
//                android.R.layout.date_picker_dialog;
                break;
            case R.id.dialog7:
                List<String> dates = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    dates.add("数据" + i);
                }
//                MenuDialog menuDialog = new MenuDialog(this, dates, new MenuDialog.MenuDialogOnButtonClickListener() {
//                    @Override
//                    public void onButtonClick(String name) {
//                        Log.d(TAG, "onButtonClick: name " + name);
//                    }
//                });
//                menuDialog.show();
                break;
            case R.id.dialog8:
//                showPopupWindow();
                showClassListPopupwindow2();
                break;
            case R.id.dialog9:
                showGithubProgressDialog();
                break;
            case R.id.bottom_btn:

                break;
            default:
                break;
        }
    }

    private void showGithubProgressDialog() {
        AnswerProgressDialog answerProgressDialog = new AnswerProgressDialog(this, R.style.AnswerDialog);
        answerProgressDialog.start(10);
    }

    private void showPopupWindow() {

    }

    private void showClassListPopupwindow2() {
        if (listView == null) {
            listView = (ListView) LayoutInflater.from(this).inflate(R.layout.item_homework_popup_filter, null, false);
            String[] mClassNames = {"选项1", "选项2", "选项4", "选项4"};
            listView.setAdapter(new ArrayAdapter<>(this, R.layout.item_homwork_filter, mClassNames));

            popupWindow2 = new PopupWindow(listView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popupWindow2.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.half_gray)));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    listView.setSelection(position);
//                    view.setSelected(true);
                }
            });
        }

//        listView.post(new Runnable() {
//            @Override
//            public void run() {
//                listView.setItemChecked(1, true);
//            }
//        });

        popupWindow2.showAsDropDown(mToolbar);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: year=" + year);
        Log.d(TAG, "onDateSet: month=" + month);
        Log.d(TAG, "onDateSet: dayOfMonth=" + dayOfMonth);
    }

}
