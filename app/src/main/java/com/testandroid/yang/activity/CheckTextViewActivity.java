package com.testandroid.yang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.CheckedTextViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ViewAnimator
 * author: yangjiajia
 * create time: 2016-9-21
 * desc:
 */
public class CheckTextViewActivity extends Activity implements AdapterView.OnItemClickListener {
    private static final String TAG = "CheckTextViewActivity";
    @BindView(R.id.checkedtextview_0)
    CheckedTextView checkedtextview0;
    @BindView(R.id.checkedtextview_1)
    CheckedTextView checkedtextview1;
    @BindView(R.id.checkedtextview_2)
    CheckedTextView checkedtextview2;
    @BindView(R.id.checked_tectview_get_checkids)
    TextView checkedTectviewGetCheckids;
    @BindView(R.id.listview)
    ListView mListView;
    private CheckedTextViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_textview);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        adapter = new CheckedTextViewAdapter(this, mListView);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(this);
    }

    @OnClick({R.id.checkedtextview_0, R.id.checkedtextview_1, R.id.checkedtextview_2, R.id.checked_tectview_get_checkids})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkedtextview_0:
                checkedtextview0.toggle();
                if (checkedtextview0.isChecked()) {
                    checkedtextview0.setCheckMarkDrawable(R.drawable.nim_contact_checkbox_checked_grey);
                } else {
                    checkedtextview0.setCheckMarkDrawable(R.drawable.nim_contact_checkbox_unchecked);
                }
                Log.d(TAG, "onViewClicked: 0 ::" + checkedtextview0.isChecked());
                break;
            case R.id.checkedtextview_1:
                checkedtextview1.setCheckMarkDrawable(R.drawable.selector_nim);
                checkedtextview1.toggle();
                Log.d(TAG, "onViewClicked: 1 ::" + checkedtextview1.isChecked());
                break;
            case R.id.checkedtextview_2:
//                checkedtextview2.setCheckMarkDrawable(R.drawable.selector_nim);
                checkedtextview2.toggle();
                Log.d(TAG, "onViewClicked: 2 ::" + checkedtextview2.isChecked());
                break;
            case R.id.checked_tectview_get_checkids:
                int selectedItemPosition = mListView.getSelectedItemPosition();
                int checkedItemPosition = mListView.getCheckedItemPosition();
                SparseBooleanArray checkedItemPositions = mListView.getCheckedItemPositions();
                Log.d(TAG, "onViewClicked: selectedItemPosition ::" + selectedItemPosition);
                Log.d(TAG, "onViewClicked: checkedItemPosition  ::" + checkedItemPosition);
                Log.d(TAG, "onViewClicked: checkedItemPositions        ::" + checkedItemPositions);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SparseBooleanArray checkedItemPositions = mListView.getCheckedItemPositions();
        Log.d(TAG, "onViewClicked: onItemClick ::" + checkedItemPositions);
        adapter.setSparseBooleanArray(checkedItemPositions);
        adapter.notifyDataSetChanged();
    }
}
