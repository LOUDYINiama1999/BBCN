package com.example.projetmobile.presentation.controller;

import com.example.projetmobile.data.News;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static  final String BASE_URL="https://newsapi.org/v2/";
    private static Client apiClient;
    private    static Retrofit retrofit;
    private  Client()
    {
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized Client getInstance()
    {
        if (apiClient==null)
        {
            apiClient=new Client();
        }
        return apiClient;
    }
    public News getApi()
    {
        return retrofit.create(News.class);
    }
}
