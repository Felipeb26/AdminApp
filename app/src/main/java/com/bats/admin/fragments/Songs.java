package com.bats.admin.fragments;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bats.admin.R;
import com.bats.admin.adapters.SongAdapter;
import com.bats.admin.interfaces.RecycleListItem;
import com.bats.admin.model.SongList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Songs extends Fragment implements RecycleListItem {

    private RecyclerView recyclerView;
    private ImageView music_icon;
    private ImageButton btnPlay, btnBack, btnGo, btnNextSong, btnPreviusSong;
    private SeekBar seekBar;
    private TextView songName, currentTimeSong, finalTime;
    private MediaPlayer media;
    private ProgressBar progressBar;
    Handler handler = new Handler();
    Runnable runnable;

    List<SongList> songs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_songs, viewGroup, false);
        initComponents(view);
        recycleList();
        return view;
    }

    public void initComponents(View view) {
        recyclerView = view.findViewById(R.id.listSong);
        seekBar = view.findViewById(R.id.seekbar);
        songName = view.findViewById(R.id.songName);
        btnPlay = view.findViewById(R.id.btn_playSong);
        btnGo = view.findViewById(R.id.btn_jumpSong);
        btnBack = view.findViewById(R.id.btn_repeatSong);
        btnPreviusSong = view.findViewById(R.id.btn_backSong);
        btnNextSong = view.findViewById(R.id.btn_nextSong);
        currentTimeSong = view.findViewById(R.id.currentSongTime);
        finalTime = view.findViewById(R.id.totalSongTime);
        music_icon = view.findViewById(R.id.music_icon);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    public void recycleList() {
        List<SongList> songLists = new ArrayList<>();
        songLists.add(new SongList("item1", "https://doc.google.com/uc?export=dowload&id=1OK4QcnsFMOSkHmUitL4SzLlpwUJ8JdK1"));
        songLists.add(new SongList("item2", "https://doc.google.com/uc?export=dowload&id=1OK4QcnsFMOSkHmUitL4SzLlpwUJ8JdK1"));
        songLists.add(new SongList("item3", "https://doc.google.com/uc?export=dowload&id=1OK4QcnsFMOSkHmUitL4SzLlpwUJ8JdK1"));
        songLists.add(new SongList("shallow", "https://docs.google.com/uc?export=dowload&id=1VzVCWhz_Pyzor9NJn798nZsCiao712j2"));
        songLists.add(new SongList("shallow", "https://docs.google.com/uc?export=dowload&id=1VzVCWhz_Pyzor9NJn798nZsCiao712j2"));
        songLists.add(new SongList("shallow", "https://docs.google.com/uc?export=dowload&id=1VzVCWhz_Pyzor9NJn798nZsCiao712j2"));
        SongAdapter adapter = new SongAdapter(songLists, getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initAudioManager(null, null);
    }

    private void initAudioManager(String songN, Uri filePath) {
        media = new MediaPlayer();
        try {
            media.setAudioAttributes(new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build());
            media.setDataSource(getContext(), filePath);
            media.prepareAsync();
            songName.setText(songN);
            runnable = new Runnable() {
                @Override
                public void run() {
                    seekBar.setProgress(media.getCurrentPosition());
                    handler.postDelayed(this, 200);
                }
            };
            media.setOnPreparedListener((MediaPlayer) -> {
                progressBar.setVisibility(View.GONE);
                int duration = media.getDuration();
                seekBar.setMax(media.getDuration());
                finalTime.setText(convertTimeFormat(duration));
            });
            btnPlay.setOnClickListener((View) -> {
                if (media.isPlaying()) {
                    media.pause();
                    handler.removeCallbacks(runnable);
                    btnPlay.setImageResource(R.drawable.ic_play);
                } else {
                    media.start();
                    seekBar.setMax(media.getDuration());
                    int duration = media.getDuration();
                    finalTime.setText(convertTimeFormat(duration));
                    handler.postDelayed(runnable, 0);
                    btnPlay.setImageResource(R.drawable.ic_pause);
                }
            });
            btnGo.setOnClickListener((View) -> {
                int currentTime = media.getCurrentPosition();
                int dura = media.getDuration();
                if (media.isPlaying() && dura != currentTime) {
                    currentTime = currentTime + 100 * 100;
                    media.seekTo(currentTime);
                    currentTimeSong.setText(convertTimeFormat(currentTime));
                }
            });
            btnBack.setOnClickListener((View) -> {
                int currentTime = media.getCurrentPosition();
                if (media.isPlaying() && currentTime > 5000) {
                    currentTime = currentTime - 100 * 100;
                    media.seekTo(currentTime);
                    currentTimeSong.setText(convertTimeFormat(currentTime));
                }
            });
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if (b) {
                        media.seekTo(i);
                    }
                    currentTimeSong.setText(convertTimeFormat(media.getCurrentPosition()));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.seekTo(0);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String convertTimeFormat(int duration) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    @Override
    public void onItemClick(SongList list) {
        startAnimation();
        if (media.isPlaying()) {
            media.stop();
            btnPlay.setImageResource(R.drawable.ic_play);
        }
        progressBar.setVisibility(View.VISIBLE);
        initAudioManager(list.getSongName(), Uri.parse(list.getSongUrl()));
    }

    public void startAnimation() {
        RotateAnimation animation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, .5f,
                RotateAnimation.RELATIVE_TO_SELF, .5f);
        animation.setDuration(1500);
        music_icon.setAnimation(animation);
    }
}