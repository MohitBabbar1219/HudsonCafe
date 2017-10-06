package com.mydarkappfactory.hudsoncafe;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    boolean canLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canLogin = false;

        final NumberProgressBar progressBar = (NumberProgressBar) findViewById(R.id.number_progress_bar);
        progressBar.setMax(100);
        final int x = 2;
        final TextView text = (TextView) findViewById(R.id.textView);

        final SharedPreferences sp = this.getSharedPreferences("com.mydarkappfactory.hudsoncafe", Context.MODE_PRIVATE);

        final String email = sp.getString("email", "-1");
        final String password = sp.getString("password", "-1");


        new CountDownTimer(1500, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                text.animate().alpha(0).setDuration(600);
            }
        }.start();

        new CountDownTimer(4000, 100) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress((int)(Math.floor((1 - ((float) l / 5000)) * 100)));
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(100);
                Intent intent;
                if (canLogin) {
                    intent = new Intent(MainActivity.this, MenuActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                }
                startActivity(intent);
            }
        }.start();

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Hudson", "Sign in status: " + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.d("Hudson", task.getException().toString());
                        } else {
                            Toast.makeText(MainActivity.this, "Welcome " + sp.getString("username", ""), Toast.LENGTH_SHORT).show();
                            canLogin = true;
                        }
                    }
                });

    }
}
