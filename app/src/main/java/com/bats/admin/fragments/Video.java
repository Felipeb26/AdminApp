package com.bats.admin.fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.bats.admin.R;
import com.bats.admin.adapters.VideoAdapter;
import com.bats.admin.model.SongList;
import com.bats.admin.model.VideoList;

import java.util.ArrayList;
import java.util.List;

public class Video extends Fragment {

    private VideoView videoView;
    private MediaController mediaController;
    private RecyclerView recyclerView;
    private SeekBar volume_SeekBar;
    private MediaPlayer mediaPlayer;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        initComponents(view);
        initRecycleVideoList();
        return view;
    }

    public void initComponents(View view) {
        videoView = view.findViewById(R.id.video_view);
        recyclerView = view.findViewById(R.id.recycle_videoList);
    }

    public void initRecycleVideoList(){
        List<VideoList> list = new ArrayList<>();
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        list.add(new VideoList("video","https://docs.google.com/uc?export=dowload&id=1FKi8JQFXZvx-MyAV3mL1q8WbWaXUWgHR"));
        VideoAdapter videoAdapter = new VideoAdapter(list, getContext());
        recyclerView.setAdapter(videoAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        videosPlay(null);
    }

//    public void videosPlay(String url) {
//        videoView.setVideoPath(url);
//        mediaController = new MediaController(getContext());
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);
//        videoView.start();
//    }
}