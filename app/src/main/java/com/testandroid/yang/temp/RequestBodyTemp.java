package com.testandroid.yang.temp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * RequestBodyTemp
 * Created by yangjiajia on 2017/4/27 0027.
 */

public class RequestBodyTemp extends RequestBody {

    @Override
    public MediaType contentType() {
        return MultipartBody.FORM;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        String data = "呵呵哈哈哈";
        byte[] bytes = data.getBytes();
        sink.write(bytes, 0, bytes.length);
    }
}
