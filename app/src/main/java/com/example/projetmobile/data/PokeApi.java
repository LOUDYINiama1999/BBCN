package com.example.projetmobile.data;

import com.example.projetmobile.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {
    @GET("/api/v2/pokemon")
    Call<RestPokemonResponse> getPokemonResponse();
}

// cette classe sert à rien dans le code //