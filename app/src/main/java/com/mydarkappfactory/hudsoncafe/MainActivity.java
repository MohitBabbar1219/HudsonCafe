package com.mydarkappfactory.hudsoncafe;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.numberprogressbar.NumberProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NumberProgressBar progressBar = (NumberProgressBar) findViewById(R.id.number_progress_bar);
        progressBar.setMax(100);
        final int x = 2;
        final TextView text = (TextView) findViewById(R.id.textView);

        new CountDownTimer(1500, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                text.animate().alpha(0).setDuration(600);
            }
        }.start();

        new CountDownTimer(3000, 100) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress((int)(Math.floor((1 - ((float) l / 5000)) * 100)));
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(100);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }.start();

    }
}
