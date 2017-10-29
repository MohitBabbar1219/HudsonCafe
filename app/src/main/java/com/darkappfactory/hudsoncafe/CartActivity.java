package com.darkappfactory.hudsoncafe;

import android.content.ContentValues;
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

    ArrayList<CartDish> finalizedDishes;
    String tableNumber;
    CartListAdapter adapter;
    SQLiteDatabase db;
    DatabaseReference firebaseDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SQLiteOpenHelper dbHelper = new DBHelper(CartActivity.this);
        db = dbHelper.getWritableDatabase();

        finalizedDishes = new ArrayList<>();

        Cursor cursor = db.query("TABLE_OTP", new String[]{"TABLE_NUMBER"}, "_id = 1", null, null, null, null);
        cursor.moveToFirst();

        tableNumber = Integer.toString(cursor.getInt(0));

        cursor.close();

        firebaseDb = FirebaseDatabase.getInstance().getReference();

        cursor = db.query("APPETIZERS"
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

    }


    public void confirmOrder(View view) {
        for (CartDish dish: finalizedDishes) {
            insertDishInAllOrders(db, new FirebaseDish(dish));
        }

        finalizedDishes = new ArrayList<>();
        ContentValues recordValues = new ContentValues();
        recordValues.put("QUANTITY", 0);
        db.update("APPETIZERS", recordValues, "QUANTITY > 0", null);
        db.update("MAIN_COURSE", recordValues, "QUANTITY > 0", null);
        db.update("SHAKES", recordValues, "QUANTITY > 0", null);
        ListView listView = (ListView) findViewById(R.id.cartListView);
        adapter = new CartListAdapter(this, R.layout.cart_row_layout, finalizedDishes, db);
        listView.setAdapter(adapter);

        Cursor cursor = db.query("ALL_ORDERS", new String[]{"NAME", "QUANTITY", "PRICE"},
                null, null, null, null, null);
        cursor.moveToFirst();

        ArrayList<FirebaseDish> dishesToBeSent = new ArrayList<>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            dishesToBeSent.add(new FirebaseDish(cursor.getString(0), cursor.getInt(1), cursor.getInt(2)));
            Log.d("Hudson", "Dish\t" + cursor.getString(0) + "\t" + cursor.getInt(1) + "\t" + cursor.getInt(2));
        }

        cursor.close();

        firebaseDb.child("tables").child(tableNumber).child("Dishes").setValue(dishesToBeSent);

    }

    private static void insertDishInAllOrders(SQLiteDatabase db, FirebaseDish firebaseDish) {
        ContentValues recordValues = new ContentValues();
        recordValues.put("NAME", firebaseDish.getName());
        recordValues.put("PRICE", firebaseDish.getPrice());
        recordValues.put("QUANTITY", firebaseDish.getQuantity());
        db.insert("ALL_ORDERS", null, recordValues);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
