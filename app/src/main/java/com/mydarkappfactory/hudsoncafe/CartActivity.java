package com.mydarkappfactory.hudsoncafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CartActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ArrayList<Dish> finalizedDishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        sharedPreferences = this.getSharedPreferences("com.mydarkappfactory.hudsoncafe", Context.MODE_PRIVATE);

        finalizedDishes = new ArrayList<>();
        String hashMapJson = sharedPreferences.getString("finalizedDishHashmap", "-1");
        if (!hashMapJson.equals("-1")) {
            Gson gson = new Gson();
            HashMap<String, String> dishesHashMap = gson.fromJson(hashMapJson, new TypeToken<HashMap<String, String>>() {}.getType());
            Set<String> dishNames = dishesHashMap.keySet();
            for (String dishName: dishNames) {
                finalizedDishes.add(gson.fromJson(dishesHashMap.get(dishName), Dish.class));
            }
        }

        ListView listView = (ListView) findViewById(R.id.cartListView);
        CartListAdapter adapter = new CartListAdapter(this, R.layout.cart_row_layout, finalizedDishes);
        listView.setAdapter(adapter);
        int x = finalizedDishes.size();
        if (finalizedDishes.size() != x) {
            Log.d("Hudson", "removed");
        }

    }
}
