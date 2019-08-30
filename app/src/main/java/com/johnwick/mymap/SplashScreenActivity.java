package com.johnwick.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ProgressBar;


public class SplashScreenActivity extends AppCompatActivity {
    private int progressStatus;
    private ProgressBar progressBar;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressStatus = 0;
        progressBar = findViewById(R.id.splashProgressBar);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                splashProgress();
                moveToMainActivity();
            }
        });
        thread.start();
    }

    private void moveToMainActivity() {
        thread.interrupt();
        Intent splashIntent = new Intent(SplashScreenActivity.this, MapsActivity.class);
        startActivity(splashIntent);
    }

    private void splashProgress() {
        try {
            for (int i = 0; i < 12; i++){
                Thread.sleep(200);
                progressBar.setProgress(progressStatus);
                progressStatus += 10;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}