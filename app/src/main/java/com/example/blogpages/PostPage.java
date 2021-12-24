package com.example.blogpages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PostPage extends AppCompatActivity {

    TextView title,category,description,postedDate,postby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_page);
        Bundle b = getIntent().getExtras();
        title = findViewById(R.id.title);
        category = findViewById(R.id.category);
        description = findViewById(R.id.desc);
        postedDate = findViewById(R.id.postdate);
        postby = findViewById(R.id.postby);


        title.setText("Title : " +b.getString("title"));
        category.setText("Category : " +b.getString("category"));
        description.setText(b.getString("desc"));
        postedDate.setText("Posted On : " +b.getString("date"));
        postby.setText("Posted by : "+b.getString("postedBy"));

    }
}