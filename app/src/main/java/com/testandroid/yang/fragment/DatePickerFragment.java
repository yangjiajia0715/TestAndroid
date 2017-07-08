package com.testandroid.yang.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * 日期选择
 * Created by yangjiajia on 2017/7/4.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "DatePickerFragment";

    /**
     * 实际应用中，用该方法使Activity必须实现DatePickerDialog.OnDateSetListener
     */
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (!(context instanceof DatePickerDialog.OnDateSetListener)) {
//            throw new ClassCastException(context.getClass().getSimpleName() + "木有实现接口：DatePickerDialog.OnDateSetListener");
//        }
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);

        //实际项目中使用
        //windownotitle 和setMinDate 冲突？？？？？
        //设置最小时间 标题不能隐藏！
//        DatePicker datePicker = datePickerDialog.getDatePicker();
//        datePicker.setMinDate(System.currentTimeMillis());
//        return new DatePickerDialog(getActivity(), R.style.DatePickerDialog, (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: year=" + year);
        Log.d(TAG, "onDateSet: month=" + month);
        Log.d(TAG, "onDateSet: dayOfMonth=" + dayOfMonth);
    }
}
