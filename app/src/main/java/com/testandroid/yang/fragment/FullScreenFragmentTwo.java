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

public class FullScreenFragmentTwo extends ListFragment {

    public static FullScreenFragmentTwo newInstance() {

        Bundle args = new Bundle();

        FullScreenFragmentTwo fragment = new FullScreenFragmentTwo();
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
                R.array.error_numbers,
                R.layout.simple_list_item_color_red_color);
        setListAdapter(arrayAdapter);
    }
}
