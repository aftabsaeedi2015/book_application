package com.example.book_application;

import java.util.ArrayList;

public class notification_times {
    public static notification_times notification_times;
    ArrayList<String> reminder_times;
    private notification_times(){
        if(reminder_times==null){
            reminder_times = new ArrayList<>();
        }
    }
    public static notification_times getInstance() {
        if(notification_times==null){
            return notification_times = new notification_times();
        }
        else
        {
            return notification_times;
        }
    }
    public boolean add_reminder_time(String time){
        if(reminder_times.add(time)) return true;
        else return false;
    }
    public boolean contains_reminder_time(String time){
        if(reminder_times.contains(time)) return true;
        else return false;
    }
}