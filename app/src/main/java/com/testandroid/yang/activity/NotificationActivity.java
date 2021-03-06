package com.testandroid.yang.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * NotificationActivity
 * Created by yangjiajia on 2017/3/24 0024.
 */

public class NotificationActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "NotificationActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.notification_01)
    TextView notification01;
    @BindView(R.id.notification_02)
    TextView notification02;
    @BindView(R.id.notification_03)
    TextView notification03;
    @BindView(R.id.notification_04)
    TextView notification04;
    @BindView(R.id.notification_05)
    TextView notification05;
    @BindView(R.id.notification_06)
    TextView notification06;
    @BindView(R.id.notification_07)
    TextView notification07;
    @BindView(R.id.notification_chronometer)
    Chronometer notificationChronometer;
    @BindView(R.id.notification_chronometer_content)
    TextView notificationChronometerContent;

    private NotificationManager notificationManager;

    Notification.Builder mBuilder;
    private PendingIntent activity;

    public static void start(Context context) {
        Intent starter = new Intent(context, NotificationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Log.d(TAG, "NotificationActivity--onCreate: getTaskId=" + getTaskId());
        ButterKnife.bind(this);

        mBuilder = new Notification.Builder(this).
                setSmallIcon(R.drawable.ic_launcher)
                .setContentText("setContentText")
                .setContentTitle("NotificationTitle");
        initView();
        initData();
    }

    @Override
    public void initView() {
        notificationChronometer.setOnClickListener(this);
        notificationChronometer.setFormat("yyyy-MM-ss HH:mm:SS");
        notificationChronometerContent.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.notification_01
            , R.id.notification_02
            , R.id.notification_03
            , R.id.notification_04
            , R.id.notification_05
            , R.id.notification_06
            , R.id.notification_07
            , R.id.notification_0700
            , R.id.notification_09
    })
    public void onViewClicked(View view) {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        switch (view.getId()) {
            case R.id.notification_01:
                taskStackBuilder01();
                break;
            case R.id.notification_02:
                taskStackBuilder();
                break;
            case R.id.notification_03:
                notificationLoop();
                break;
            case R.id.notification_04:
                taskStack();
                break;
            case R.id.notification_05:
                remoteView();
                break;
            case R.id.notification_06:
                setProgressBar();
                break;
            case R.id.notification_07:
                notificationAction();
                break;
            case R.id.notification_0700:
                headerUPNotification();
                break;
            case R.id.notification_09:
                others();
                break;
        }
    }

    private void headerUPNotification() {

        Intent intent = new Intent(this, TypeOtherActivity.class);
        PendingIntent activity = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setFullScreenIntent(activity, true);

        notificationManager.notify(200, mBuilder.build());
    }

    private void others() {

        //test 001 inboxstyle
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
        inboxStyle.addLine("Line1Line1Line1Line1Line1Line1Line1Line1Line1Line1Line1Line1Line1Line1");
        inboxStyle.addLine("Line2");
//        inboxStyle.addLine("Line3");
//        inboxStyle.addLine("Line4Line4Line4Line4Line4Line4Line4Line4Line4Line4Line4Line4Line4Line4");
//        inboxStyle.addLine("Line5");
//        inboxStyle.addLine("Line6");
        inboxStyle.setBigContentTitle("BigContentTitle");
        inboxStyle.setSummaryText("SummaryText");
        mBuilder.setStyle(inboxStyle);

//        mBuilder.setVisibility(Notification.VISIBILITY_PRIVATE);//api 21

        mBuilder.setDefaults(Notification.DEFAULT_LIGHTS);//呼吸灯
//        mBuilder.setCategory(Notification.CATEGORY_PROGRESS);
//        Notification notification = mBuilder.build();
//        notification.category = Notification.CATEGORY_PROGRESS;

        //test 002:largeIcon
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_pay_success));

        //test 003: action failed
//        Intent intent = new Intent(this, NotificationActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        mBuilder.addAction(R.drawable.ic_launcher,"查看微课",pendingIntent);//?不起作用

        final Notification notification = mBuilder.build();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            notification.visibility = Notification.VISIBILITY_SECRET;
        }

        notification01.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: postDelayed=" + notification);
                notificationManager.notify(R.id.notification_09, notification);
            }
        },8000);
    }

    private void taskStackBuilder01() {
        Notification.Builder builder = new Notification.Builder(this).
                setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("标题")
                .setAutoCancel(true)
                .setContentText("Content");

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, OverviewScreen01Activity.class);

        // The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(NotificationActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
//                PendingIntent.
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0
                , PendingIntent.FLAG_ONE_SHOT);//FLAG_UPDATE_CURRENT
        builder.setContentIntent(resultPendingIntent);

        Notification notification = builder.build();
//                notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(R.id.notification_01, notification);
    }

    private void taskStackBuilder() {
        Intent intent = new Intent(this, OverviewScreen01Activity.class);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Event tracker")
                .setContentText("Events received");

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        inboxStyle.setBigContentTitle("Event tracker details:");

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(NotificationActivity.class);
        taskStackBuilder.addNextIntent(intent);
//                PendingIntent.
        PendingIntent taskStackBuilderPendingIntent = taskStackBuilder.getPendingIntent(0
                , PendingIntent.FLAG_ONE_SHOT);//FLAG_UPDATE_CURRENT
        mBuilder.setContentIntent(taskStackBuilderPendingIntent);
// Moves events into the expanded layout
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
// Moves the expanded layout object into the notification object.
        mBuilder.setStyle(inboxStyle);

        Notification notification1 = mBuilder.build();
//                notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(R.id.notification_01, notification1);
    }

    private void notificationAction() {
//        new Notification.Actionstion.Builder(null,"Act",null);
//        mBuilder.addAction();

//        mBuilder.setPriority(Notification.PRIORITY_MAX);
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
        inboxStyle.setBigContentTitle("BigContenTitle");
        inboxStyle.addLine("按钮1");
        inboxStyle.addLine("按钮2");
        inboxStyle.addLine("按钮3");
        inboxStyle.addLine("按钮4");
        inboxStyle.addLine("按钮5");

        Log.d(TAG, "notificationAction: inboxStyle=" + inboxStyle);

//        mBuilder.setStyle(inboxStyle);

//        Icon.createWithResource("",R.drawable.ic_launcher);

        Intent intent = new Intent(this, TypeViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        mBuilder.addAction(R.drawable.ic_launcher, "hh", pendingIntent);

        Log.d(TAG, "notificationAction: mBuilder=" + mBuilder);

        mBuilder.setFullScreenIntent(pendingIntent, true);

//        PendingIntent service = PendingIntent.getService(this, 1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        notificationManager.notify(R.id.notification_02, mBuilder.build());
    }

    private void remoteView() {
        Notification.Builder mBuilder = new Notification.Builder(this).
                setSmallIcon(R.drawable.ic_launcher)
                .setContentText("remoteView")
                .setContentTitle("setContentTitle");

        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.remoteview);
        mBuilder.setContent(remoteViews);
        remoteViews.setChronometer(R.id.remoteview_chronometer, System.currentTimeMillis(), "MM:SS", true);

        mBuilder.setOngoing(true);//用户不能取消

        notificationManager.notify(R.id.notification_02, mBuilder.build());
//        mBuilder.setCustomContentView(remoteViews);

    }

    private void setProgressBar() {

        final int ID = R.id.notification_02;
        final Notification.Builder mBuilder = new Notification.Builder(this).
                setSmallIcon(R.drawable.ic_launcher)
                .setContentText("setProgressBar")
                .setContentTitle("setProgressBar");

        mBuilder.setAutoCancel(true);

        mBuilder.setProgress(100, 0, false);

// Start a lengthy operation in a background thread
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr += 5) {
                            // Sets the progress indicator to a max value, the
                            // current completion percentage, and "determinate"
                            // state
                            mBuilder.setProgress(100, incr, false);
                            // Displays the progress bar for the first time.
                            notificationManager.notify(0, mBuilder.build());
                            // Sleeps the thread, simulating an operation
                            // that takes time
                            try {
                                // Sleep for 5 seconds
                                Thread.sleep(5 * 1000);
                            } catch (InterruptedException e) {
                                Log.d(TAG, "sleep failure");
                            }
                        }
                        // When the loop is finished, updates the notification
                        mBuilder.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0, 0, false);
                        notificationManager.notify(ID, mBuilder.build());
                    }
                }
// Starts the thread by calling the run() method in its Runnable
        ).start();
        notificationManager.notify(R.id.notification_01, mBuilder.build());
    }

    private void taskStack() {
        Notification.Builder builder = new Notification.Builder(this).
                setSmallIcon(R.drawable.ic_launcher)
                .setContentText("content")
                .setContentTitle("content");

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
//        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(new Intent(this, TypeOtherActivity.class));
        taskStackBuilder.addNextIntent(new Intent(this, OverviewScreen01Activity.class));

        int intentCount = taskStackBuilder.getIntentCount();

        Log.d(TAG, "taskStack: intentCount=" + intentCount);

        Intent intent = taskStackBuilder.editIntentAt(0);

        Log.d(TAG, "taskStack:  intent.getAction()=" + intent.getAction());

//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

//        builder.setAutoCancel(true);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(100, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(R.id.notification_01, builder.build());
    }

    private void notificationLoop() {
        Notification.Builder builder = new Notification.Builder(this).setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Loop")
                .setContentText("content");
        int numMessage = 0;

        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, null, PendingIntent.FLAG_ONE_SHOT);

//        builder.setContentIntent()
        builder.setAutoCancel(true);
//        builder.setFullScreenIntent()

        for (int i = 0; i < 10; i++) {
            builder.setContentText("ccccccc=" + i);
            notificationManager.notify(R.id.notification_01, builder.build());
//            notificationManager.cancelAll();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification_chronometer:
                notificationChronometer.start();
                break;
            case R.id.notification_chronometer_content:
                notificationChronometer.stop();
                break;
        }
    }
}
