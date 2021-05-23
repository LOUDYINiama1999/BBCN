package com.example.projetmobile.presentation.controller;

import android.content.SharedPreferences;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Constants;
import com.example.projetmobile.Singletons;
import com.example.projetmobile.Client;
import com.example.projetmobile.presentation.model.Articles;
import com.example.projetmobile.presentation.model.Headlines;
import com.example.projetmobile.presentation.model.Pokemon;
import com.example.projetmobile.presentation.model.RestPokemonResponse;
import com.example.projetmobile.presentation.view.NewActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private NewActivity view;
    private String API_KEY="10d0da6b81274c03b352f1d420b54783";
    RecyclerView newsRecyclerViews;
    final String country="fr";
    final String  category="business";
    ListNewsAdapter newsAdapter;

    List<Articles> articles = new ArrayList<>();

    public MainController(NewActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        List<Articles> articles = getDataFromCache();

        if(articles != null){
            view.showList(articles);
        } else {
            //makeApiCall();
            CallApi(country,category,API_KEY);
        }

    }


    private void makeApiCall(){
        Call<RestPokemonResponse> call = Singletons.getPokeApi().getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Pokemon> pokemonList = response.body().getResults();
                    /*saveList(pokemonList);
                    view.showList(pokemonList);*/
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
               view.showError();
            }
        });
    }

    private void saveList(List<Articles> articles) {
        String jsonString = gson.toJson(articles);

        sharedPreferences
                .edit()
                .putString(Constants.NEW_LIST, jsonString)
                .apply();
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

                    saveList(articles);
                    view.showList(articles);;
                }else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

                view.showError();
            }
        });
    }

    private List<Articles> getDataFromCache() {
        String jsonNews = sharedPreferences.getString(Constants.NEW_LIST, null);

        if(jsonNews == null){
            return null;
        } else {
            Type listType = new TypeToken<ArrayList<Articles>>() {}.getType();
            return gson.fromJson(jsonNews, listType);
        }
    }

    
}
