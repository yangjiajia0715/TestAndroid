package com.testandroid.yang.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testandroid.yang.R;

/**
 * Created by yangjiajia on 2017/7/30.
 */

public class CardBackFragment extends Fragment {
    private static final String TAG = "CardBackFragment";
    public static CardBackFragment newInstance() {

        Bundle args = new Bundle();

        CardBackFragment fragment = new CardBackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_back, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "CardBackFragment--onDestroy: ");
    }
}
