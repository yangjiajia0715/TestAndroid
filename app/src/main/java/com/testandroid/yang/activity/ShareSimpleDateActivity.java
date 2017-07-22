package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享数据
 * file:///D:/sdk/docs/training/sharing/index.html
 * Created by yangjiajia on 2017/7/21.
 */

public class ShareSimpleDateActivity extends BaseActivity {

    @BindView(R.id.share_date_0)
    TextView shareDate0;
    @BindView(R.id.share_date_1)
    TextView shareDate1;
    @BindView(R.id.share_date_3)
    TextView shareDate3;
    @BindView(R.id.share_date_4)
    TextView shareDate4;
    @BindView(R.id.share_date_5)
    TextView shareDate5;
    @BindView(R.id.share_date_6)
    TextView shareDate6;
    @BindView(R.id.share_date_7)
    TextView shareDate7;
    @BindView(R.id.share_date_8)
    TextView shareDate8;
    @BindView(R.id.share_date_9)
    TextView shareDate9;
    private ShareActionProvider shareActionProvider;

    public static void start(Context context) {
        Intent starter = new Intent(context, ShareSimpleDateActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_date);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_simple_date, menu);
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.share_date_0, R.id.share_date_1, R.id.share_date_3, R.id.share_date_4,
            R.id.share_date_5, R.id.share_date_6, R.id.share_date_7, R.id.share_date_8,
            R.id.share_date_9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_date_0:
                ActionProvider provider = new ActionProvider(this) {
                    @Override
                    public View onCreateActionView() {
                        return null;
                    }
                };

                Intent sendIntent1 = new Intent();
                sendIntent1.setAction(Intent.ACTION_SEND);
                sendIntent1.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent1.setType("text/plain");
                startActivity(sendIntent1);
                break;
            case R.id.share_date_1:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                break;
            case R.id.share_date_3:
//                MimeTypeMap.getFileExtensionFromUrl("");
//                D:\aaWorking\apks\精准课堂
                String uriToImage = "content://media/external/images/media/17039";

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                //*/*,You can use a MIME type of "*/*",
                // but this will only match activities that are able to handle generic data streams.
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
                break;
            case R.id.share_date_4:
                Uri uri = Uri.parse("content://media/external/images/media/17039");
                ArrayList<Uri> imageUris = new ArrayList<>();
                imageUris.add(uri); // Add your image URIs here
                imageUris.add(uri);

                Intent shareIntent4 = new Intent();
                shareIntent4.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareIntent4.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                shareIntent4.setType("image/*");
                startActivity(Intent.createChooser(shareIntent4, "Share images to.."));
                break;
            case R.id.share_date_5:
                // Multiple attachment data
//                ArrayList<Uri> uris = getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setData(Uri.parse("content://media/external/images/media/17039"));
                intent.putExtra(Intent.EXTRA_STREAM, "content://media/external/images/media/17039");
                intent.setType("image/*");
                shareActionProvider.setShareIntent(intent);
                break;
            case R.id.share_date_6:
                File file = new File("");
                Uri uriForFile = FileProvider.getUriForFile(this, "", file);
                break;
            case R.id.share_date_7:
                break;
            case R.id.share_date_8:
                break;
            case R.id.share_date_9:
                break;
        }
    }
}
