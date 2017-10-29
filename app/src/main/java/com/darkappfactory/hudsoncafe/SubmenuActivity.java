package com.darkappfactory.hudsoncafe;

import android.content.Context;
import android.content.Intent;
//import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class SubmenuActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    static ArrayList<Dish> dishesA, dishesM, dishesS;
    ArrayList<String> finalizedDishes;
//    SharedPreferences sharedPreferences;
    HashMap<String, String> nameDishJsonPair;

    DishListAdapter dishListAdapter;
    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu);

        Intent intent = getIntent();
        int imgIndex = intent.getIntExtra("imgIndex", -1);
        Log.d("Hudson", "" + imgIndex);

        toolbar = (Toolbar) findViewById(R.id.toolbar_submenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewPagerSubmenu);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new AppetizerFragment(), "Appetizer");
        adapter.addFragment(new MainCourseFragment(), "Main Course");
        adapter.addFragment(new ShakesFragment(), "Shakes");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(0);

        SQLiteOpenHelper dbHelper = new DBHelper(SubmenuActivity.this);
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("APPETIZERS"
                , new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "RATING", "QUANTITY", "IMG_RES_ID"}
                ,null, null, null, null, null);


        dishesA = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            dishesA.add(new Dish(cursor));
        }
        cursor.close();

        cursor = db.query("SHAKES"
                , new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "RATING", "QUANTITY", "IMG_RES_ID"}
                ,null, null, null, null, null);
        dishesS = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            dishesS.add(new Dish(cursor));
        }
        cursor.close();

        cursor = db.query("MAIN_COURSE"
                , new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "RATING", "QUANTITY", "IMG_RES_ID"}
                ,null, null, null, null, null);
        dishesM = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            dishesM.add(new Dish(cursor));
        }
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        Log.d("Hudson", "Submenu destroyed");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.itemOne:
                Log.d("Hudson", "item 1");
                //TODO
                break;
            case R.id.itemTwo:
                //TODO
                break;
            case R.id.itemThree:
                //TODO
                break;
            case R.id.cart:
                //TODO
                Intent intent = new Intent(SubmenuActivity.this, CartActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void hashmapToSP() {
//        if (sharedPreferences.getString("finalizedDishHashmap", "-1").equals("-1")) {
//            nameDishJsonPair = Functions.threeArrayListToHashmap(dishesM, dishesS, dishesA);
//            String hashMapJson = Functions.hashMapToJson(nameDishJsonPair);
//            sharedPreferences.edit().putString("finalizedDishHashmap", hashMapJson).apply();
//        } else {
//            HashMap<String, String> retrievedHashMap = Functions.jsonToHashMap(sharedPreferences.getString("finalizedDishHashmap", "-1"));
//            retrievedHashMap = Functions.updateHashMapFromArrayLists(retrievedHashMap, dishesM, dishesS, dishesA);
//            String hashMapJson = Functions.hashMapToJson(retrievedHashMap);
//            sharedPreferences.edit().putString("finalizedDishHashmap", hashMapJson).apply();
//        }
//    }

}