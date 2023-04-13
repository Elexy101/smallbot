package com.example.myapplication6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Handler handler = new Handler();
    private ImageView image_view;
    private int progressStatus = 0;
    ProgressBar progressbarDemo;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.image_view = (ImageView) findViewById(R.id.image1);
        this.image_view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.clockwise));
        this.progressbarDemo = (ProgressBar) findViewById(R.id.progress1);
        new Thread(new Runnable() {
            public void run() {
                while (MainActivity.this.progressStatus < 100) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.progressStatus = mainActivity.progressStatus + 10;
                    MainActivity.this.handler.post(new Runnable() {
                        public void run() {
                            MainActivity.this.progressbarDemo.setProgress(MainActivity.this.progressStatus);
                            if (MainActivity.this.progressStatus == 100) {
                                MainActivity.this.progressbarDemo.setProgress(MainActivity.this.progressStatus);
                                MainActivity.this.startActivity(new Intent(MainActivity.this, Submain.class));
                                MainActivity.this.finish();
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void onResume() {
        super.onResume();
        Toast main = Toast.makeText(getApplicationContext(), "Please wait...", Toast.LENGTH_SHORT);
        main.setGravity(Gravity.CENTER, 0, 0);
        main.show();
    }
}