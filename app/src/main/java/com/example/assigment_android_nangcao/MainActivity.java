package com.example.assigment_android_nangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnCourse(View view) {

        Intent intent = new Intent(this, CourseActitity.class);
        startActivity(intent);

    }

    public void btnMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void btnNews(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }
}
