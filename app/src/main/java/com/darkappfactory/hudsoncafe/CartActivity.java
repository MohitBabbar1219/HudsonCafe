package com.darkappfactory.hudsoncafe;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CartActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ArrayList<CartDish> finalizedDishes;
    String tableNumber;
    CartListAdapter adapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SQLiteOpenHelper dbHelper = new DBHelper(CartActivity.this);
        db = dbHelper.getWritableDatabase();

        finalizedDishes = new ArrayList<>();

        Cursor cursor = db.query("APPETIZERS"
                , new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "RATING", "QUANTITY", "IMG_RES_ID"}
                ,"QUANTITY > 0", null, null, null, null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            finalizedDishes.add(new CartDish(new Dish(cursor), "APPETIZERS"));
        }
        cursor.close();

        cursor = db.query("SHAKES"
                , new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "RATING", "QUANTITY", "IMG_RES_ID"}
                ,"QUANTITY > 0", null, null, null, null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            finalizedDishes.add(new CartDish(new Dish(cursor), "SHAKES"));
        }
        cursor.close();

        cursor = db.query("MAIN_COURSE"
                , new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "RATING", "QUANTITY", "IMG_RES_ID"}
                ,"QUANTITY > 0", null, null, null, null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            finalizedDishes.add(new CartDish(new Dish(cursor), "MAIN_COURSE"));
        }
        cursor.close();


        ListView listView = (ListView) findViewById(R.id.cartListView);
        adapter = new CartListAdapter(this, R.layout.cart_row_layout, finalizedDishes, db);
        listView.setAdapter(adapter);
        int x = finalizedDishes.size();
        if (finalizedDishes.size() != x) {
            Log.d("Hudson", "removed");
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
