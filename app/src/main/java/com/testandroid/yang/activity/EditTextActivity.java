package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangjiajia on 2017/6/25 0025.
 */

public class EditTextActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.bottom_btn)
    TextView bottomBtn;
    @BindView(R.id.bottomLayout)
    LinearLayout bottomLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, EditTextActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
//                    sendMessage();
                    handled = true;
                }
                return handled;

            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bottom_btn)
    public void onViewClicked() {
        if (edittext.requestFocus()) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.showSoftInput(edittext, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
