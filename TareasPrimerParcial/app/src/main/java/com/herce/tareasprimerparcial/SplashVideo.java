package com.herce.tareasprimerparcial;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class SplashVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_video);

        String UrlPath="android.resource://"+getPackageName()+"/"+R.raw.ad1;
        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse(UrlPath));
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intentoPrincipal = new Intent().setClass(SplashVideo.this, MainActivity.class);
                startActivity(intentoPrincipal);
            }
        });
    }
}
