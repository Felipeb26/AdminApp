package com.bats.admin.adapters;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bats.admin.R;
import com.bats.admin.interfaces.RecycleListItem;
import com.bats.admin.model.SongList;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {
    private final List<SongList> list;
    private final Context context;
    private final RecycleListItem listItem;

    public SongAdapter(List<SongList> listS, Context cont, RecycleListItem listItem) {
        this.list = listS;
        this.context = cont;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.songText.setText(list.get(position).getSongName());
        holder.urlText.setText(list.get(position).getSongUrl());
        holder.cardView.setOnClickListener((view) -> {
            listItem.onItemClick(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView songText, urlText;
        public CardView cardView;

        public MyViewHolder(@NonNull View view) {
            super(view);
            songText = view.findViewById(R.id.item_nome);
            urlText = view.findViewById(R.id.item_url);
            cardView = view.findViewById(R.id.item_container);

        }
    }
}