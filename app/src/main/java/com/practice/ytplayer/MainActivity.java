package com.practice.ytplayer;

import static com.google.android.youtube.player.YouTubePlayer.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends YouTubeBaseActivity {

    Button btn_play, btn_shuffle;
    YouTubePlayerView youTubePlayerView;
    OnInitializedListener onInitializedListener;

    YouTubePlayer player;
    List<String> videos;

    String api_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = findViewById(R.id.btn_play);
        btn_shuffle = findViewById(R.id.btn_shuffle);
        youTubePlayerView = findViewById(R.id.yt_playerView);

        videos = Arrays.asList("IEF6mw7eK4s", "8CEJoCr_9UI", "344u6uK9qeQ", "3-nM3yNi3wg", "nB7nGcW9TyE");
        // "RlcY37n5j9M" this video id is not working

        onInitializedListener = new OnInitializedListener() {
            @Override
            public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;
                player.loadVideo(videos.get(0));
                player.play();

                player.setPlaybackEventListener(new PlaybackEventListener() {
                    @Override
                    public void onPlaying() {
                        btn_play.setText("Pause");
                    }

                    @Override
                    public void onPaused() {
                        btn_play.setText("Play");
                    }

                    @Override
                    public void onStopped() {
                        btn_play.setText("Play");
                    }

                    @Override
                    public void onBuffering(boolean b) {

                    }

                    @Override
                    public void onSeekTo(int i) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.i("Initialization", "onInitializationFailure: ");
            }
        };

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player != null){
                    if(player.isPlaying()){
                        player.pause();
                        btn_play.setText("Play");
                    }
                    else{
                        player.play();
                        btn_play.setText("Pause");
                    }
                }else{
                    youTubePlayerView.initialize(api_key,onInitializedListener);
                    btn_play.setText("Pause");
                }
            }
        });

        btn_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rn = (int) (Math.random() * (videos.size()));
                player.loadVideo(videos.get(rn));
            }
        });

    }
}