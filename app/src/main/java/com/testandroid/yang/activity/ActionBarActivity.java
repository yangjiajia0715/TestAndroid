package com.testandroid.yang.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.testandroid.yang.R;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 根据文档，测试ActionBar 相关
 * Created by yangjiajia on 2017/2/24 0024.
 */

//
public class ActionBarActivity extends BaseActivity {
    private static final String TAG = "ActionBarActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Messenger mMessenger;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActionBarActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getResultData();
            setResultData("");
            Bundle bundle = new Bundle();
        }
    };

    ServiceConnection mCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        ButterKnife.bind(this);
        initView();
        initData();
//        sendOrderedBroadcast();
//        sendOrderedBroadcast();

//        HttpURLConnection connection = Uri.parse("").

        Intent intent = new Intent("");
        bindService(intent, mCon, Context.BIND_AUTO_CREATE);

        HttpURLConnection connection;
        HttpClient httpClient ;
        DefaultHttpClient defaultHttpClient;
        AndroidHttpClient androidHttpClient;

        Glide.with(this);

        Message msgFromClient = Message.obtain(null, 11, 22, 3);
        msgFromClient.replyTo = mMessenger;

        //往服务端发送消息
        try {
            mMessenger.send(msgFromClient);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        HandlerThread handlerThread;
        try {
            //区别
//            Uri.
            URL url = new URL("");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            startForegroundService()
//            Intent.AcConn
//            Intent.AcNew
            Handler handler = new Handler();

//            message1.replyTo.send();
            Messenger messenger;
//            messenger.getBinder();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {
        Log.d(TAG, "initView: toolbar=" + toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        Log.d(TAG, "initView: actionBar=" + actionBar);// android.support.v7.app.WindowDecorActionBar
        if (actionBar != null) {
            actionBar.setTitle("MyActionBar");
//            actionBar.setIcon(R.drawable.ic_mail_box);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_mail_box);
            actionBar.setDisplayHomeAsUpEnabled(true);//貌似不起作用
//            actionBar.setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationContentDescription("导航？");
        //需放到setSupportActionBar 后面 才生效？
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: setNavigationOnClickListener v=" + v);
//                finish();
//            }
//        });

//        actionBar.setElevation(0);
//        actionBar.setLogo(R.drawable.ic_data);

//        actionBar.setIcon(R.drawable.ic_mail_box);

        Log.d(TAG, "initView: actionBar=" + actionBar);

    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        return super.getSupportParentActivityIntent();
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
    }

    @Override
    public void initData() {
        //ActionMenuView

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);

//        MenuItem searchItem = menu.findItem(R.id.action_search);
//
//        //必须用该方法设置，否则不支持的操作异常
//        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                Log.d(TAG, "onMenuItemActionExpand: item=" + item);
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                Log.d(TAG, "onMenuItemActionCollapse: item=" + item);
//                return true;
//            }
//        });
//
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//
//        searchView.setIconifiedByDefault(false);
//
////        720
//        searchView.setMaxWidth(100 * 4);
//
//        searchView.setSubmitButtonEnabled(true);
//
//        searchView.setBackgroundResource(R.drawable.bg_accumulated_income);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d(TAG, "onQueryTextSubmit: query=" + query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Log.d(TAG, "onQueryTextChange: newText=" + newText);
//                return false;
//            }
//        });


//        TextView actionView = (TextView) menu.findItem(R.id.action_text_veiw).getActionView();
//        actionView.setText("健健康康扩扩啦啦啦啦啦绿");

//        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));
//        shareActionProvider.setShareIntent(getDefaultIntent());

        return super.onCreateOptionsMenu(menu);
    }

    /** Defines a default (dummy) share intent to initialize the action provider.
     * However, as soon as the actual content to be used in the intent
     * is known or changes, you must update the share intent by again calling
     * mShareActionProvider.setShareIntent()
     */
    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Drawable icon = item.getIcon();
//        Log.d(TAG, "onOptionsItemSelected: item=" + item.getTitle());

        switch (item.getItemId()) {
            case R.id.menu_bar_setting:
                return true;
            case R.id.menu_bar_more:
                return true;
            default:
                return true;
//            super.onOptionsItemSelected(item)
        }

        //返回true,actionView不会自动展开？，为啥还是展开了
//        return true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mCon);
    }
}
