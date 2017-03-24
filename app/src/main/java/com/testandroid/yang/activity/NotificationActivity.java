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
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * NotificationActivity
 * Created by yangjiajia on 2017/3/24 0024.
 */

public class NotificationActivity extends BaseActivity {
    private static final String TAG = "NotificationActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.notification_01)
    TextView notification01;
    @BindView(R.id.notification_02)
    TextView notification02;
    @BindView(R.id.notification_03)
    TextView notification03;
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
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.notification_01, R.id.notification_02, R.id.notification_03})
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
        }
    }

    private void notificationLoop() {
        Notification.Builder builder = new Notification.Builder(this).setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Loop")
                .setContentText("content");
        int numMessage = 0;

        for (int i = 0; i < 10; i++) {
            builder.setContentText("ccccccc=" + i);
            notificationManager.notify(R.id.notification_01, builder.build());
            notificationManager.cancelAll();
        }
    }
}
