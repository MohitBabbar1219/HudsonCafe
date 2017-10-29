package com.darkappfactory.hudsoncafe;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OtpActivity extends AppCompatActivity {

    EditText otpText;
    DatabaseReference dbRef;
//    SharedPreferences sp;
    SQLiteDatabase db;
    static String tableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        SQLiteOpenHelper dbHelper = new DBHelper(OtpActivity.this);
        db = dbHelper.getWritableDatabase();

        otpText = (EditText) findViewById(R.id.otpEdtText);
        dbRef = FirebaseDatabase.getInstance().getReference();
//        sp = OtpActivity.this.getSharedPreferences("com.darkappfactory.hudsoncafe", Context.MODE_PRIVATE);

        Cursor cursor = db.query("TABLE_OTP", new String[]{"TABLE_NUMBER", "OTP"},
                "_id = 1", null, null, null, null);
        cursor.moveToFirst();

        final int tableNum = cursor.getInt(0);
        final int otp = cursor.getInt(1);

        cursor.close();

        if (tableNum != -1) {
            dbRef.child("tables").child("" + tableNum).child("otp").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue(Integer.class) == otp) {
//                    tableNumber = otpText.getText().toString().substring(0, 1);
//                    sp.edit().putString("tableNumber", tableNumber).apply();
//                    Log.d("Hudson", "tableNum:" + sp.getString("tableNumber", "-2"));

                        Intent intent = new Intent(OtpActivity.this, MenuActivity.class);
                        startActivity(intent);
                    } else {
                        RelativeLayout otpWaiting = (RelativeLayout) findViewById(R.id.otpWaitingLayout);
                        otpWaiting.setVisibility(View.INVISIBLE);
                        RelativeLayout otpLayout = (RelativeLayout) findViewById(R.id.otpLayout);
                        otpLayout.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            RelativeLayout otpWaiting = (RelativeLayout) findViewById(R.id.otpWaitingLayout);
            otpWaiting.setVisibility(View.INVISIBLE);
            RelativeLayout otpLayout = (RelativeLayout) findViewById(R.id.otpLayout);
            otpLayout.setVisibility(View.VISIBLE);
        }

    }

    public void submitOtp(View view) {
        final int otp = Integer.parseInt(otpText.getText().toString().substring(1));
        final int tableNum = Integer.parseInt(otpText.getText().toString().substring(0, 1));
        System.out.println(otp);
        System.out.println(tableNum);
        dbRef.child("tables").child("" + tableNum).child("otp").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(Integer.class) == otp) {
//                    sp.edit().putString("tableNumber", tableNumber).apply();
//                    Log.d("Hudson", "tableNum:" + sp.getString("tableNumber", "-2"));
                    ContentValues recordValues = new ContentValues();
                    recordValues.put("TABLE_NUMBER", tableNum);
                    recordValues.put("OTP", otp);

                    db.update("TABLE_OTP", recordValues, "_id = 1", null);
                    db.close();

                    Intent intent = new Intent(OtpActivity.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("Hudson", "Otp: false");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
