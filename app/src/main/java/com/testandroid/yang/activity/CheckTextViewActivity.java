package com.testandroid.yang.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
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
//        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);//需配合setMultiChoiceModeListener

        adapter = new CheckedTextViewAdapter(this);
        mListView.setAdapter(adapter);

        mListView.setItemChecked(3, true);
        adapter.setSparseBooleanArray(mListView.getCheckedItemPositions());
        adapter.notifyDataSetChanged();

        mListView.setOnItemClickListener(this);
//        mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
//            @Override
//            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
//            }
//
//            @Override
//            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                MenuInflater inflater = getMenuInflater();
//                inflater.inflate(R.menu.list_select_menu, menu);
//                mode.setTitle("Select Items");
//                return true;
//            }
//
//            @Override
//            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                return true;
//            }
//
//            @Override
//            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.drop_down_answer_action_mode:
//                        Toast.makeText(CheckTextViewActivity.this, "drop_down_answer_action_mode", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.drop_down_setting_action_mode:
//                        Toast.makeText(CheckTextViewActivity.this, "drop_dow", Toast.LENGTH_SHORT).show();
//                        return true;
//                }
//                return false;
//            }
//
//            @Override
//            public void onDestroyActionMode(ActionMode mode) {
//
//            }
//        });

        //获取对应的属性值 Android框架自带的属性 attr
        int pressed = android.R.attr.state_pressed;
        int window_focused = android.R.attr.state_window_focused;
        int focused = android.R.attr.state_focused;
        int selected = android.R.attr.state_selected;

        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.arrow_left_dart, getTheme());
        stateListDrawable.addState(new int[]{pressed}, drawable);

//        checkedTectviewGetCheckids.setActivated(true);
//        checkedTectviewGetCheckids.setSelected(true);

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
                Log.d(TAG, "onViewClicked: checkedItemPosition  :: 点击-----");
//                checkedTectviewGetCheckids.setSelected(true);
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
        Log.d(TAG, "onViewClicked: position=" + position + " isItemChecked=" + mListView.isItemChecked(position));
//        adapter.notifyDataSetChanged();
//        mListView.setItemChecked(position, !mListView.isItemChecked(position));

//        SparseBooleanArray checkedItemPositions = mListView.getCheckedItemPositions();
////        Log.d(TAG, "onViewClicked: onItemClick ::" + checkedItemPositions);
////        view.setSelected(true);
////        int checkedItemCount = mListView.getCheckedItemCount();
////        Log.d(TAG, "onViewClicked: checkedItemCount ::" + checkedItemCount);
////        mListView.getCheckedItemPosition();//单选有效
//        adapter.setSparseBooleanArray(checkedItemPositions);
//        adapter.notifyDataSetChanged();
    }
}
