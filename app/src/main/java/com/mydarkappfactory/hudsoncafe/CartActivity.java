package com.mydarkappfactory.hudsoncafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ArrayList<String> dishListJson = new ArrayList<>();
        sharedPreferences = this.getSharedPreferences("com.mydarkappfactory.hudsoncafe", Context.MODE_PRIVATE);
        try {
            dishListJson = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("finalizedDishes", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Dish> finalizedDishes = new ArrayList<>();

        for (int i = 0; i < dishListJson.size(); i++) {
            Gson gson = new Gson();
            Dish dish = gson.fromJson(dishListJson.get(i), Dish.class);
            finalizedDishes.add(dish);
        }

        ListView listView = (ListView) findViewById(R.id.cartListView);
        ListAdapter adapter = new ListAdapter(this, R.layout.row_layout, finalizedDishes);
        listView.setAdapter(adapter);
    }
}
