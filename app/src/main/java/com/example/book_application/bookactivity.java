package com.example.book_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pspdfkit.configuration.activity.PdfActivityConfiguration;
import com.pspdfkit.ui.PdfActivity;

import java.util.Arrays;
import java.util.List;

public class bookactivity extends AppCompatActivity {
    String book_id="book_id",url;
    Context context;
    book_info  clicked_book;
    ImageView book_img;
    Button showallbooks,addtofavorite, showfavoritebooks,showfinishedbooks , addtofinished , read, setTimer;
    TextView author,discription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookactivity);
//        bookactivity.this.registerReceiver(new notification_receiver(),new IntentFilter(Intent.ACTION_TIME_TICK));

        book_img = findViewById(R.id.book_img);
        showallbooks = (Button) findViewById(R.id.showallbooks);
        addtofavorite =(Button) findViewById(R.id.addtofavorite);
        showfavoritebooks = (Button) findViewById(R.id.showfavoritebooks);
        showfinishedbooks = (Button) findViewById(R.id.showfinishedbooks);
        addtofinished = findViewById(R.id.addtofinished);
        read = findViewById(R.id.read);
        setTimer = findViewById(R.id.settimer);
        author = findViewById(R.id.author);
        discription = findViewById(R.id.discription);
// creating the notification channel for notification receiver
//        create_notification_channel();
//        settting the timer when set timer is clicked
        setTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_time();
            }
        });

        Intent intent = getIntent();
        if(intent!=null){
            String book_Id = intent.getStringExtra("book_id");

            book_info clicked_book = utils.getInstance().getbookbyid(book_Id);
            //            book url
            url = clicked_book.URL;
//            setting text in the author
            String authorTxt = clicked_book.AUTHOR.replace("]","");
            authorTxt = authorTxt.replace("[","");
            List<String> author1 = Arrays.asList(authorTxt.split(","));
            author.setText(author1.get(0));
//            getting discript of book
            String discriptionTxt = clicked_book.DESCRIPTION;
            discription.setText(discriptionTxt);
//            getting image link
            String imgLink = clicked_book.IMG;
            Glide.with(bookactivity.this).asBitmap().load(imgLink).into(book_img);


//            handling the clicking
            showallbooks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(bookactivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });
//            reading the book on addtofinished button click
            addtofinished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(utils.getInstance().addtofinished(clicked_book)){

                        Toast.makeText(bookactivity.this, "book added", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(bookactivity.this, "try again ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            addtofavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(utils.getInstance().addtofavorite(clicked_book)){

                        Toast.makeText(bookactivity.this, "book added", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(bookactivity.this, "try again ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            showfavoritebooks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(bookactivity.this, "lauch activity", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(bookactivity.this,favorite_books.class);
                    startActivity(intent);
                }
            });
//            show all finished books
            showfinishedbooks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(bookactivity.this, "lauch activity", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(bookactivity.this,finished_books.class);
                    startActivity(intent);
                }
            });
//            handling read book click
            read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tutorialspoint.com/android/android_tutorial.pdf"));
//                    startActivity(browserIntent);
                    final Uri uri = Uri.parse("file:///android_asset/2130.pdf");
                    final Uri uri2 = Uri.parse("http:///books.google.co.in/books/download/Android_For_Dummies-sample-pdf.acsm?id=JGH0DwAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api");
                    final PdfActivityConfiguration config = new PdfActivityConfiguration
                            .Builder(bookactivity.this).build();
                    PdfActivity.showDocument(bookactivity.this, uri, config);
                }
            });
//            handling set timer button click


        }

//        handling the functionalities of all the buttons in bookactivity





    }
//    notification properties that will be linked to the reciever notification
    private void create_notification_channel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = "somename";
            String description = "channel for the notification";
            int important = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("channel",name,important);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
//    setting a reminder
    public void set_time(){

                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, "time");
//                    intent.putExtra(CalendarContract.Events.ORIGINAL_INSTANCE_TIME, );
                    if (intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else
                        Toast.makeText(bookactivity.this,"There is no Calendar app.",Toast.LENGTH_SHORT).show();
            }

    public void onDestroy () {

        super.onDestroy();
//        bookactivity.this.registerReceiver(new notification_receiver(),new IntentFilter(Intent.ACTION_TIME_TICK));
    }
}