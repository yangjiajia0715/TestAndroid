package com.testandroid.yang.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 联系嵌套fragment 答疑
 * Created by yangjiajia on 2017/8/22.
 */

public class AnswerFragment extends Fragment {
    private static final String TAG = "AnswerFragment";

    @BindView(R.id.listview)
    ListView listview;
    Unbinder unbinder;

    public static AnswerFragment newInstance() {
        Bundle bundle = new Bundle();
        AnswerFragment fragment = new AnswerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_answer, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.letter_list, android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_select_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        android.support.v7.app.ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        switch (item.getItemId()) {
            case R.id.drop_down_answer_action_mode:
                if (supportActionBar != null) {
                    supportActionBar.hide();
                }
                return true;
            case R.id.drop_down_setting_action_mode:
                if (supportActionBar != null) {
                    supportActionBar.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }
}
