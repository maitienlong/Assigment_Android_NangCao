package com.example.assigment_android_nangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsDetailActivity extends AppCompatActivity {

    private WebView wvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        wvNews = findViewById(R.id.wvNews);

        wvNews.getSettings().setJavaScriptEnabled(true);

        Intent intent = this.getIntent();

        String link = intent.getStringExtra("link");

        wvNews.loadUrl(link);
    }
}
