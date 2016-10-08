package com.example.testandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testandroid.R;
import com.example.testandroid.adapter.CheckedTextViewAdapter;

/**
 * ViewAnimator
 * author: yangjiajia
 * create time: 2016-9-21
 * desc:
 */
public class CheckTextViewActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "CheckTextViewActivity";

    private ListView mListView;

    private boolean flag;

    private TextView tvShow;

    private CheckedTextViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_textview);
        initView();

        //origin branch
        //tempBranch
//        tttt
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.checked_tectview_listview);
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        adapter = new CheckedTextViewAdapter(this, mListView);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(this);

        findViewById(R.id.checked_tectview_toggle).setOnClickListener(this);
        findViewById(R.id.checked_tectview_get_checkids).setOnClickListener(this);

        tvShow = (TextView) findViewById(R.id.checked_tectview_checkids_show);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checked_tectview_toggle:
                flag = !flag;

                if (flag) {
                    mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                } else {
                    mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                }

                adapter.notifyDataSetChanged();

                Log.d(TAG, "onClick: flag=" + flag + ",getChoiceMode=" + mListView.getChoiceMode());
                break;
            case R.id.checked_tectview_get_checkids:
                int checkedItemCount = mListView.getCheckedItemCount();
                SparseBooleanArray checkedItemPositions = mListView.getCheckedItemPositions();
                long[] checkedItemIds = mListView.getCheckedItemIds();

//                mListView.clearChoices();

                String ids = "";
                if (checkedItemIds != null) {
                    for (long id : checkedItemIds) {
                        ids += ",id=" + id;
                    }
                }

                String content = "checkedItemCount=" + checkedItemCount + ",checkedItemPositions=" + checkedItemPositions + ",ids=" + ids;

                tvShow.setText(content);

                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick: position=" + position);
    }
}
