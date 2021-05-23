package com.example.projetmobile.presentation.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.presentation.model.Articles;
import com.example.projetmobile.R;
import com.example.projetmobile.presentation.model.Pokemon;
import com.example.projetmobile.presentation.view.DetailActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsAdapter.ViewHolderNews> {

    private List<Articles> articles;
    private ListAdapter.OnItemClickListener listener;
    Context mContext;
    Activity mActivity;

    public ListNewsAdapter( List<Articles>  myDataset,  ListAdapter.OnItemClickListener listener) {
        this.articles = myDataset;
        this.listener = listener;
    }
    public ListNewsAdapter( List<Articles>  myDataset,Context context/*Activity activity*/) {
        this.articles = myDataset;
        this.listener = listener;
        this.mContext=context;
        //this.mActivity=activity;
    }
    public interface OnItemClickListener {
        void onItemsClick(Pokemon item);
    }

    class ViewHolderNews extends RecyclerView.ViewHolder {

        TextView txtHeader;
        TextView txtFooter;
        ImageView image;
        RelativeLayout relativeLayout;
        View layout;

        ViewHolderNews(View v) {
            super(v);
            layout = v;
            image=(ImageView) v.findViewById(R.id.icon);
            txtHeader = (TextView) v.findViewById(R.id.title);
            txtFooter = (TextView) v.findViewById(R.id.author);
            relativeLayout=(RelativeLayout) v.findViewById(R.id.rc_news);
        }
    }
    @Override
    public ViewHolderNews onCreateViewHolder(
            ViewGroup parent,
            int viewType) {
        View layout
         = LayoutInflater.from(mContext).inflate(R.layout.items_news,parent,false);
        return new ViewHolderNews(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderNews holder, int position) {
        final Articles a=articles.get(position);
        final String url =a.getUrl();
        holder.txtFooter.setText(a.getAuthor());
        holder.txtHeader.setText(a.getTitle());
        String imageUrl=a.getUrlToImage();
        Picasso.get().load(imageUrl).into(holder.image);
       holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);

                intent.putExtra("title",a.getTitle());
                intent.putExtra("source",a.getSource().getName());
                intent.putExtra("time",a.getPublishedAt());
                intent.putExtra("imageUrl",a.getUrlToImage());
                intent.putExtra("url",a.getUrl());
                intent.putExtra("decs",a.getDescription());
                mContext.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }



}
