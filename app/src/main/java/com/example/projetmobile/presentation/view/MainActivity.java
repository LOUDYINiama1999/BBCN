package com.example.projetmobile.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projetmobile.R;
import com.example.projetmobile.Singletons;
import com.example.projetmobile.presentation.controller.ListAdapter;
import com.example.projetmobile.presentation.model.Pokemon;
import com.example.projetmobile.presentation.controller.MainController;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager ;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferencesInstances(getApplicationContext())
        );
        controller.onStart();*/
        Intent intent = new Intent(this,NewActivity.class);
        this.startActivity(intent);

    }

    public void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(pokemonList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemsClick(Pokemon item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigaTeDetails(Pokemon pokemon) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("pokemonKeyName", Singletons.getGson().toJson(pokemon));
        MainActivity.this.startActivity(myIntent);
    }
}






