package com.mydarkappfactory.hudsoncafe;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText emailText, passwordText, confirmPasswordText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);
        confirmPasswordText = (EditText) findViewById(R.id.confirmPassword);

        mAuth = FirebaseAuth.getInstance();

    }

    public void registerToFirebase(View view) {
        String email = emailText.getText().toString(),
                password = passwordText.getText().toString(),
                confirmPassword = confirmPasswordText.getText().toString();

        boolean emailValidity = isEmailValid(email), passwordValidity = isPasswordValid(password);

        if (emailValidity && passwordValidity) {
            createFirebaseUser(email, password);
        } else if (!emailValidity && passwordValidity) {
            Toast.makeText(RegisterActivity.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
        } else if (!passwordValidity && emailValidity) {
            Toast.makeText(RegisterActivity.this, "Password must be atleast 6 characters long", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(RegisterActivity.this, "Enter valid email and password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailValid(String email) {
        return !email.isEmpty() && email.contains("@") && (email.indexOf('@') != 0);
    }

    private boolean isPasswordValid(String password) {
        return !password.isEmpty() && password.length() > 5;
    }

    private void createFirebaseUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Hudson", "Status: " + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.d("Hudson", task.getException().toString());
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration successful, Login to continue", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}
