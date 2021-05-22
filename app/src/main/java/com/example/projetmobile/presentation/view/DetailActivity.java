package com.example.projetmobile.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmobile.R;
import com.example.projetmobile.Singletons;
import com.example.projetmobile.presentation.model.Pokemon;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;
    TextView tvTitle,tvSource,tvTime;
    ImageView imageView;
    TextView txtContent;
    WebView webView;
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        tvTitle=findViewById(R.id.titleD);
        tvSource=findViewById(R.id.sourcesD);
        // tvDesc=v.findViewById(R.id.tvDesc);
        imageView=findViewById(R.id.imageView);
        txtDetail=findViewById(R.id.desc);
        txtContent=findViewById(R.id.content);
        //Bundle args = getArguments();
        Intent intent =getIntent();
        String title=intent.getStringExtra("title");
        String source=intent.getStringExtra("source");
        String desc=intent.getStringExtra("description");
        String content=intent.getStringExtra("content");
        String time=intent.getStringExtra("time");
        String imageUrl=intent.getStringExtra("imageUrl");
        String url=intent.getStringExtra("url");


        tvTitle.setText(desc);
        txtDetail.setText(title);
        txtContent.setText(content);
        tvSource.setText("Source: "+source);


        Picasso.get().load(imageUrl).into(imageView);

    }

    private void showDetail(Pokemon pokemon) {
        pokemon=new Pokemon();
        txtDetail.setText(pokemon.getName());
    }
}






