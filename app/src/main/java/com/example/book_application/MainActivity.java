package com.example.book_application;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.main);
        recyclerView = findViewById(R.id.recyclerview);
        utils.getInstance();
        adapter adapter = new adapter(this, "main");
//        here we give all the book object array to the adapter to view items
        adapter.addBook(utils.getInstance().getAll_books());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        if(!forground_service_running()){
            startService();
        }

//        stopService();


    }
        public void stopService() {
            Intent serviceIntent = new Intent(this, Service.class);
            stopService(serviceIntent);
        }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startService() {
        Intent serviceIntent = new Intent(this, Service.class);
        startForegroundService(serviceIntent);
    };
    public boolean forground_service_running(){
        ActivityManager manager = (ActivityManager) getSystemService(MainActivity.this.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if(Service.class.getName().equals(service.service.getClassName())){
                return true;
            }

        }
        return false;
    }
    public static void showtext(){
        System.out.println("hello");

    }
}