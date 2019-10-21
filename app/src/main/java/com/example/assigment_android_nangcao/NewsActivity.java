package com.example.assigment_android_nangcao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.assigment_android_nangcao.adapter.AdapterNewRV;
import com.example.assigment_android_nangcao.model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView rvNews;
    private String url = "https://tuoitre.vn/rss/tin-moi-nhat.rss";
    LinearLayoutManager linearLayoutManager;
    AdapterNewRV adapterNewRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        rvNews = findViewById(R.id.rvNews);
        GetDataTask getDataTask = new GetDataTask();
        getDataTask.execute(url);
    }

    class GetDataTask extends AsyncTask<String, Long, List<News>> {

        @Override
        protected List<News> doInBackground(String... strings) {
            // boc tach xml tai day

            // khai bao 1 list rong~ de chua du lieu
            List<News> newsList = new ArrayList<>();

            // khai bao try catch de bat loi~
            try {

                URL url = new URL(strings[0]);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();


                // khoi tao doi tuong xmlpullparser
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(false);

                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();

                // truyen du lieu vao xmlpullparser tien hanh boc tach xml
                xmlPullParser.setInput(inputStream, "utf-8");

                int eventType = xmlPullParser.getEventType();
                News news = null;
                String text = "";
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String name = xmlPullParser.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (name.equals("item")) {
                                news = new News();
                            }
                            break;

                        case XmlPullParser.TEXT:
                            text = xmlPullParser.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            if (news != null && name.equalsIgnoreCase("title")) {
                                news.title = text;
                            } else if (news != null && name.equalsIgnoreCase("description")) {
                                news.description = text;
                            } else if (news != null && name.equalsIgnoreCase("pubdate")) {
                                news.pubDate = text;
                            } else if (news != null && name.equalsIgnoreCase("link")) {
                                news.link = text;
                            } else if (news != null && name.equalsIgnoreCase("guiid")) {
                                news.guiid = text;
                            } else if (name.equalsIgnoreCase("item")) {
                                newsList.add(news);
                            }
                            break;

                    }
                    // di chuyen toi tag ke tiep
                    eventType = xmlPullParser.next(); //move to next element
                }
            } catch (Exception e) {
                Log.e("Exception", e.getMessage());
            }

            return newsList;
        }


        @Override
        protected void onPostExecute(final List<News> news) {
            super.onPostExecute(news);


            linearLayoutManager = new LinearLayoutManager(NewsActivity.this);

            adapterNewRV = new AdapterNewRV(NewsActivity.this, news);

            rvNews.setAdapter(adapterNewRV);

            rvNews.setLayoutManager(linearLayoutManager);

            AdapterNewRV.ItemClickSupport.addTo(rvNews).setOnItemClickListener(new AdapterNewRV.ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                    String link = news.get(position).link;
                    Intent intent = new Intent(NewsActivity.this , NewsDetailActivity.class);
                    intent.putExtra("link",link);

                    startActivity(intent);
                }
            });

        }
    }
}
