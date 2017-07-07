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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
//        int style = R.style.MyDialogTheme;
        int style = 0;
        return new DatePickerDialog(getActivity(), this, year, month, day);
//        return new DatePickerDialog(getActivity(), style, this, year, month, day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: year=" + year);
        Log.d(TAG, "onDateSet: month=" + month);
        Log.d(TAG, "onDateSet: dayOfMonth=" + dayOfMonth);
    }
}
