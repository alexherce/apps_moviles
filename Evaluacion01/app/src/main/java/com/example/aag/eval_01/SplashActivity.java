package com.example.aag.eval_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

  private long tiempoEspera = 5000;

  private NetworkImageView imageView;
  private ImageLoader imageLoader;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    imageView = (NetworkImageView) findViewById(R.id.photo);

    Random generator = new Random();
    int i = generator.nextInt(3) + 1;

    String url = "https://demo.adhw.com.mx/Content/img/bg/" + i + ".jpg".trim();
    imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext()).getImageLoader();
    imageLoader.get(url, ImageLoader.getImageListener(imageView, R.drawable.splash,
        android.R.drawable.ic_dialog_alert));
    imageView.setImageUrl(url, imageLoader);

    TimerTask tarea = new TimerTask() {
      public void run() {
        finish();
        Intent intentoPrincipal = new Intent().setClass(SplashActivity.this, MainActivity.class);
        startActivity(intentoPrincipal);
      }
    };

    Timer timer = new Timer();
    timer.schedule(tarea, tiempoEspera);
  }
}
