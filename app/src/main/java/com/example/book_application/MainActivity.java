package com.example.book_application;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.book_application.bookApiHandler.APIHelper;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    RecyclerView recyclerView;
    EditText searchbox;
    Button search;
    String result;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchbox = findViewById(R.id.searchBox);
        search = findViewById(R.id.searchButton);
//        getting all the books

        try
        {
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    URL url = APIHelper.buildUrl(searchbox.getText().toString());
                    new QueryBooks().execute(url);
                }
            });


        }
        catch (Exception e)
        {
            Log.d("Error",e.getMessage());
        }


        relativeLayout = findViewById(R.id.main);
        recyclerView = findViewById(R.id.recyclerview);

    }

//get the books in the background and update the ui
    public class QueryBooks extends AsyncTask<URL,Void,String> {

        @Override
        protected String doInBackground(URL... urls) {

            URL searchUrl = urls[0];

            try{

                result = APIHelper.getBooks(searchUrl);

            }

            catch(Exception e){

                Log.d("Error", e.getMessage());

            }

            return result;

        }

    @Override

    protected void onPostExecute(String result) {

        super.onPostExecute(result);
//        do something after data is fetched in result
        Log.e("message", result);
        adapter adapter = new adapter(MainActivity.this, "main");
//        here we give all the book object array to the adapter to view items
        ArrayList<book_info> books = com.example.book_application.utils.getInstance().initData(result);
        adapter.setBooks(books);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        Intent intent = getIntent();
    }

    }
}
