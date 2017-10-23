package com.testandroid.yang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.testandroid.yang.R;

/**
 * FullScreenFragment
 * Created by yangjiajia on 2017/10/23.
 */

public class FullScreenFragment extends ListFragment {

    public static FullScreenFragment newInstance() {

        Bundle args = new Bundle();

        FullScreenFragment fragment = new FullScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.letter_list,
                android.R.layout.simple_list_item_1);
        setListAdapter(arrayAdapter);
    }
}
