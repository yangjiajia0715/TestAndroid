package com.testandroid.yang.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.testandroid.yang.R;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Loader
 * Created by yangjiajia on 2016/12/2 0002.
 */

public class CursorLoadActivity extends Activity implements LoaderManager.LoaderCallbacks<Object> {
    private static final String TAG = "CursorLoadActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_load);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);

        temp();
    }

    private void temp() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
//        StringBuffer stringBuffer = new StringBuffer();
//        FieldPosition position = new FieldPosition(DateFormat.HOUR_OF_DAY0_FIELD);
//        simpleDateFormat.format(new Date(), stringBuffer, position);
//        Log.d(TAG, "temp: stringBuffer=" + stringBuffer.toString() + ",position=" + position);
//        String substring = stringBuffer.toString().substring(position.getBeginIndex(), position.getEndIndex());
//        Log.d(TAG, "temp: substring=" + substring);

        NumberFormat numForm = NumberFormat.getInstance();
        StringBuffer dest1 = new StringBuffer();
        FieldPosition pos = new FieldPosition(NumberFormat.INTEGER_FIELD);
        BigDecimal bd1 = new BigDecimal(2.342323232323D);
        dest1 = numForm.format(bd1, dest1, pos);
        System.out.println("dest1 = " + dest1);
        System.out.println("INTEGER portion is at: " + pos.getBeginIndex() + ", " + pos.getEndIndex());
        pos = new FieldPosition(NumberFormat.FRACTION_FIELD);
        StringBuffer dest2 = new StringBuffer();
        dest2 = numForm.format(bd1, dest2, pos);
        System.out.println("dest2 = " + dest2);
        System.out.println("FRACTION portion is at: " + pos.getBeginIndex() + ", " + pos.getEndIndex());

        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        StringBuffer dest3 = new StringBuffer();
        //关注的是几号
        pos = new FieldPosition(DateFormat.DATE_FIELD);
        dest3 = df.format(new Date(), dest3, pos);
        System.out.println("dest3 = " + dest3);
        //结果当前时间为2012年6月27日 下午04时06分56秒  则beginIndex为7 endIndex为9
        System.out.println("FRACTION portion is at: " + pos.getBeginIndex() + ", " + pos.getEndIndex());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String input[] = { "abc 2013-10-01 Vancouver, B.C.", "1248-03-01 Ottawa, ON", "1323-06-06 Toronto, ON" };
        for (int i = 0; i < input.length; i++) {
            ParsePosition pp = new ParsePosition(4);//从第四位开始处理
            Date d = formatter.parse(input[i], pp);
            if (d == null) {
                //结果只处理了"abc 2013-10-01 Vancouver, B.C."
                //"1248-03-01 Ottawa, ON"从第四位开始是"8-03-01 Ottawa, ON"无法转换
                System.err.println("Invalid date in " + input[i]);
                continue;
            }
            //成功转换后ParsePosition.getIndex()就是匹配的字符串结尾的索引
            String location = input[i].substring(pp.getIndex());
            System.out.println(" on " + d + " in " + location);
        }
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle bundle) {

        Loader<Object> loader = new Loader<Object>(this) {

        };


        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
    }
}
