package com.example.book_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class favorite_books extends AppCompatActivity {
    RecyclerView recyclerview;
    ArrayList<book_info> favorite_books;
    ArrayList<book_info> added_tofavorite_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_books);
        recyclerview = findViewById(R.id.recyclerviewfavorite);
        adapter adapter = new adapter(this,"favorite_books");
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter.addBook(utils.getFavorite_books());
        Intent intent = getIntent();

    }
}