package com.testandroid.yang.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.listener.OnStateChangeListener;
import com.testandroid.yang.R;
import com.testandroid.yang.common.Paths;

/**
 * @author yangjiajia
 * @date 2018/7/19
 */
public class AnswerProgressDialog extends Dialog {

    private final FillableLoader mFillableLoader;

    public AnswerProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_answer_progress);
        Window window = getWindow();
        if (window != null) {
//            window.addFlags(WindowManager.LayoutParams.Flag);
        }
        mFillableLoader = findViewById(R.id.fillableLoader);
        mFillableLoader.setSvgPath(Paths.GITHUB);

//        setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                mFillableLoader.reset();
//            }
//        });
    }

    public void reset() {
        mFillableLoader.reset();
    }

    /**
     * @param second 秒值
     */
    public void start(int second) {
        if (isShowing()) {
            return;
        }
        show();
        mFillableLoader.reset();
        mFillableLoader.setFillDuration(Math.max(1, Math.min(second, 600)) * 1000);
        //We wait a little bit to start the animation, to not contaminate the drawing effect
        //by the activity creation animation.
        mFillableLoader.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFillableLoader.start();
            }
        }, 250);

        mFillableLoader.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                switch (state) {
                    case State.FILL_STARTED:
                        break;
                    case State.FINISHED:
                        dismiss();
                        mFillableLoader.reset();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
