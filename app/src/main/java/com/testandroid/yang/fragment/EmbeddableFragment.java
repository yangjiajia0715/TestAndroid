package com.testandroid.yang.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.testandroid.yang.R;

/**
 * 嵌入式fragment
 * Created by yangjiajia on 2017/7/7.
 */

public class EmbeddableFragment extends DialogFragment {

    public static EmbeddableFragment newInstance() {
        Bundle bundle = new Bundle();
        EmbeddableFragment fragment = new EmbeddableFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_embeddable, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;

//        return super.onCreateDialog(savedInstanceState);
    }
}
