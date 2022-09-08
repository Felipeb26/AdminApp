package com.bats.admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bats.admin.R;
import com.bats.admin.model.VideoList;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    private final Context context;
    private final List<VideoList> videos;

    public VideoAdapter(List<VideoList> list, Context context) {
        this.videos = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_list, parent, false);
        return new VideoAdapter.VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoHolder holder, int position) {
        holder.videoName.setText(videos.get(position).getVideoName());
        holder.videoUrl.setText(videos.get(position).getVideoUrl());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    public static class VideoHolder extends RecyclerView.ViewHolder {
        private TextView videoName, videoUrl;
        private CardView cardView;

        public VideoHolder(@NonNull View view) {
            super(view);
            videoName = view.findViewById(R.id.video_name);
            videoUrl = view.findViewById(R.id.video_url);
            cardView = view.findViewById(R.id.video_container);

        }
    }


}
