package com.testandroid.yang.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
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
    @BindView(R.id.notification_chronometer)
    Chronometer notificationChronometer;
    @BindView(R.id.notification_chronometer_content)
    TextView notificationChronometerContent;

    private NotificationManager notificationManager;

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
            , R.id.notification_06})
    public void onViewClicked(View view) {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        switch (view.getId()) {
            case R.id.notification_01:
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
                break;
            case R.id.notification_02: {
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
        }
    }

    private void remoteView() {
        Notification.Builder mBuilder = new Notification.Builder(this).
                setSmallIcon(R.drawable.ic_launcher)
                .setContentText("remoteView")
                .setContentTitle("setContentTitle");

        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.remoteview);
        mBuilder.setContent(remoteViews);
        remoteViews.setChronometer(R.id.remoteview_chronometer, System.currentTimeMillis(),"MM:SS" , true);

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

        mBuilder.setProgress(100, 0 , false);

// Start a lengthy operation in a background thread
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr+=5) {
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
                                Thread.sleep(5*1000);
                            } catch (InterruptedException e) {
                                Log.d(TAG, "sleep failure");
                            }
                        }
                        // When the loop is finished, updates the notification
                        mBuilder.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0,0,false);
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

        Log.d(TAG, "taskStack:  intent.getAction()=" +  intent.getAction());

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
