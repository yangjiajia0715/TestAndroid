package com.testandroid.yang.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * 清除缓存 仿网易新闻
 * Created by yangjiajia on 2017/7/7.
 */

public class ClearCacheFragment extends DialogFragment {
    private static final String TAG = "ClearCacheFragment";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: content " + context.toString());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach: activity " + activity.toString());
    }

    public static ClearCacheFragment newInstance() {
        Bundle bundle = new Bundle();
        ClearCacheFragment fragment = new ClearCacheFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Log.d(TAG, "onCreateDialog: savedInstanceState " + savedInstanceState);
        return super.onCreateDialog(savedInstanceState);
    }
}
