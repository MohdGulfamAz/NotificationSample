package com.gulfam.notificationsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    Button mNotificationBtn;
    ImageView mImageView;
    public final  String CHANNEL_ID = "001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationBtn = (Button) findViewById(R.id.btnNotification);

        mImageView = (ImageView) findViewById(R.id.image_view);

        String url = "https://i.imgur.com/SDivRM6.jpg";

        Picasso.get()
                .load(url)
                .resize(50, 50)
                .centerCrop()
                .into(mImageView);


        mNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This is a notification";

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"001",NotificationManager.IMPORTANCE_DEFAULT);
                    channel.setDescription("It is a new Notification");
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.createNotificationChannel(channel);

                    Notification.Builder builder = new Notification.Builder(MainActivity.this,CHANNEL_ID);
                    builder.setSmallIcon(R.drawable.ic_message);
                    builder.setAutoCancel(true);
                    builder.setContentTitle("New Notification");
                    builder.setContentText(message);


                    Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("message",message);

                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,001,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                    notificationManager.notify(001, builder.build());


                }
                else {
                    Notification.Builder builder = new Notification.Builder(MainActivity.this);
                    builder.setSmallIcon(R.drawable.ic_message);
                    builder.setAutoCancel(true);
                    builder.setContentTitle("New Notification");
                    builder.setContentText(message);



                    Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("message",message);

                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,001,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);


                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                    notificationManager.notify(001, builder.build());

                }

            }
        });
    }
}