package com.testandroid.yang.view;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;

import com.testandroid.yang.R;

/**
 * MyActionProvide
 * Created by yangjiajia on 2017/2/26 0026.
 */

public class MyActionProvide extends ActionProvider {
    private static final String TAG = "MyActionProvide";
    private Context mContext;
    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public MyActionProvide(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View onCreateActionView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.action_view_test, null);

        return view;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        Log.d(TAG, "onPrepareSubMenu: ");
        super.onPrepareSubMenu(subMenu);
    }

    @Override
    public boolean onPerformDefaultAction() {
        Log.d(TAG, "onPerformDefaultAction: ");
        return super.onPerformDefaultAction();
    }
}
