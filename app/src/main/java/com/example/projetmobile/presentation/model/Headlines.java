package com.example.projetmobile.presentation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Headlines {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("articles")
    @Expose
    private List<Articles> articles;


    public List<Articles> getArticles() {
        return articles;
    }



}
