package com.darkappfactory.hudsoncafe;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailText, passwordText;
    FirebaseAuth mAuth;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        SQLiteOpenHelper dbHelper = new DBHelper(LoginActivity.this);
        db = dbHelper.getWritableDatabase();

    }

    public void attemptLogin(View view) {
        final String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();
//        final SharedPreferences sp = this.getSharedPreferences("com.mydarkappfactory.hudsoncafe", Context.MODE_PRIVATE);

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "Login in progress...", Toast.LENGTH_SHORT).show();
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Hudson", "Sign in status: " + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.d("Hudson", task.getException().toString());
                        } else {
//                            Log.d("Hudson", sp.getString("username", sp.getString("username", "")));

//                            sp.edit().putString("username", email.substring(0, email.indexOf('@'))).apply();
//                            sp.edit().putString("email", email).apply();
//                            sp.edit().putString("password", password).apply();

                            ContentValues recordValues = new ContentValues();
                            recordValues.put("EMAIL", email);
                            recordValues.put("PASSWORD", password);
                            db.update("EMAIL_PASSWORD", recordValues, "_id = ?", new String[]{"1"});

                            Toast.makeText(LoginActivity.this, "Welcome " + email.substring(0, email.indexOf('@')), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                            db.close();
                            startActivity(intent);
                        }
                    }
                });

    }

    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        db.close();
        startActivity(intent);
    }


}
