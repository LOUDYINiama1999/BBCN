package com.example.projetmobile.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.projetmobile.Singletons;
import com.example.projetmobile.presentation.controller.MainController;
import com.example.projetmobile.presentation.model.Articles;
import com.example.projetmobile.presentation.model.Headlines;
import com.example.projetmobile.R;
import com.example.projetmobile.Client;
import com.example.projetmobile.presentation.controller.ListNewsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewActivity extends AppCompatActivity {

    private String API_KEY="10d0da6b81274c03b352f1d420b54783";
    RecyclerView newsRecyclerViews;
    final String country="fr";
    final String  category="business";
    ListNewsAdapter newsAdapter;
    private MainController controller;

    List<Articles> articles = new ArrayList<>();
    private String cat="news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportActionBar().hide();
        newsRecyclerViews = findViewById(R.id.rcView_news);
        newsRecyclerViews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferencesInstances(getApplicationContext())
        );
        controller.onStart();
//        CallApi(country,category,API_KEY);

    }
    public  void CallApi(String country,String categori,String apiKey)
    {
        Call<Headlines> call= Client.getInstance().getApi().getHeadlines(country,categori,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles()!=null){
                    articles.clear();
                    articles=response.body().getArticles();

                     newsAdapter = new ListNewsAdapter(articles,NewActivity.this);
                    newsRecyclerViews.setAdapter(newsAdapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"There is An Error",Toast.LENGTH_SHORT).show();
            }
        });
    }





    public void showList(List<Articles> articlesList) {

        newsAdapter = new ListNewsAdapter(articlesList,NewActivity.this);
        newsRecyclerViews.setAdapter(newsAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }


}