package com.testandroid.yang;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * 异步任务
 * Created by yangjiajia on 2017/6/1 0001.
 */

public class MyAsynTask extends AsyncTask<String,Integer,Bitmap> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //唯一抽象方法
    @Override
    protected Bitmap doInBackground(String... params) {
        publishProgress(1);
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
