package com.example.book_application;

import static com.example.book_application.App.CHANNEL_ID;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Service extends android.app.Service {
    Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String time = new SimpleDateFormat("hhmm").format(Calendar.getInstance().getTime());
        notification_times times = notification_times.getInstance();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(times.contains_reminder_time(time)){
                    Log.e("service","is running");
                    try{
                        Thread.sleep(2000);
                        if(times.contains_reminder_time(time)){
                            System.out.println("running");
                        }

                    }
                    catch (InterruptedException e ){
                        e.printStackTrace();
                    }
                }

            }
        }).start();
             notification = new NotificationCompat.Builder(Service.this, CHANNEL_ID)
                    .setContentTitle("Example Service")
                    .setContentText("running")
                    .setSmallIcon(R.drawable.ic_android)
                    .build();
                startForeground(1,notification);


        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}