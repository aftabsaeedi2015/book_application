package com.example.book_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class finished_books extends AppCompatActivity {
    RecyclerView recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_books);
        recyclerview = findViewById(R.id.finished_books_recycler_view);
        adapter adapter = new adapter(this,"finshed_books");
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(utils.getFinished_books());
        Intent intent = getIntent();
    }
}