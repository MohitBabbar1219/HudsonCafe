package com.mydarkappfactory.hudsoncafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class SubmenuActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    static ArrayList<Dish> dishesA, dishesM, dishesS;
    ArrayList<String> finalizedDishes;
    SharedPreferences sharedPreferences;

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

        dishesA = new ArrayList<>();
        dishesM = new ArrayList<>();
        dishesS = new ArrayList<>();

        Dish d1 = new Dish("Veg Bruschetta", "Veg Bruschetta is grilled bread rubbed with garlic and topped with olive oil and salt, tomato, vegetables, beans, and cheese.", 139, R.drawable.appetizers_quesadilla);
        Dish d2 = new Dish("Nachos", "Crispy Nachos topped with tomato salsa, sour cream, jalapenos and kidney beans.", 159, R.drawable.appetizers_nachos);
        Dish d3 = new Dish("Quesadilla Veg", "Hand rolled tortilla filled with melted cheese, beans, jalapenos and peppers.", 179, R.drawable.appetizers_bruschetta);
        Dish d4 = new Dish("Cottage Cheeze Skewers", "Cubes of cattage cheese marinated and grilled on sticks.", 179, R.drawable.appetizers_cottage_cheeze);
        Dish d5 = new Dish("Chilli Chicken Dry", "Boneless chicken cubes are marinated in soya sauce, chili sauce and pepper, deep fried and seasoned again in sauces.", 239, R.drawable.appetizers_chilli_chicken);
        Dish d6 = new Dish("Drums of Heaven", "Chicken lollipop tossed in schezwan sauce.", 239, R.drawable.appetizers_drums_heaven);
        Dish d7 = new Dish("Thai Chilli Basil", "Thai dish made with freshly chopped chicken thighs and fresh basil.", 239, R.drawable.appetizers_thai_chilli);
        Dish d1m = new Dish("Veg BruschettaM", "Veg Bruschetta is grilled bread rubbed with garlic and topped with olive oil and salt, tomato, vegetables, beans, and cheese.", 139, R.drawable.appetizers_quesadilla);
        Dish d2m = new Dish("NachosM", "Crispy Nachos topped with tomato salsa, sour cream, jalapenos and kidney beans.", 159, R.drawable.appetizers_nachos);
        Dish d3m = new Dish("Quesadilla VegM", "Hand rolled tortilla filled with melted cheese, beans, jalapenos and peppers.", 179, R.drawable.appetizers_bruschetta);
        Dish d4m = new Dish("Cottage Cheeze SkewersM", "Cubes of cattage cheese marinated and grilled on sticks.", 179, R.drawable.appetizers_cottage_cheeze);
        Dish d5m = new Dish("Chilli Chicken DryM", "Boneless chicken cubes are marinated in soya sauce, chili sauce and pepper, deep fried and seasoned again in sauces.", 239, R.drawable.appetizers_chilli_chicken);
        Dish d6m = new Dish("Drums of HeavenM", "Chicken lollipop tossed in schezwan sauce.", 239, R.drawable.appetizers_drums_heaven);
        Dish d7m = new Dish("Thai Chilli BasilM", "Thai dish made with freshly chopped chicken thighs and fresh basil.", 239, R.drawable.appetizers_thai_chilli);
        Dish d1s = new Dish("Veg BruschettaS", "Veg Bruschetta is grilled bread rubbed with garlic and topped with olive oil and salt, tomato, vegetables, beans, and cheese.", 139, R.drawable.appetizers_quesadilla);
        Dish d2s = new Dish("NachosS", "Crispy Nachos topped with tomato salsa, sour cream, jalapenos and kidney beans.", 159, R.drawable.appetizers_nachos);
        Dish d3s = new Dish("Quesadilla VegS", "Hand rolled tortilla filled with melted cheese, beans, jalapenos and peppers.", 179, R.drawable.appetizers_bruschetta);
        Dish d4s = new Dish("Cottage Cheeze SkewersS", "Cubes of cattage cheese marinated and grilled on sticks.", 179, R.drawable.appetizers_cottage_cheeze);
        Dish d5s = new Dish("Chilli Chicken DryS", "Boneless chicken cubes are marinated in soya sauce, chili sauce and pepper, deep fried and seasoned again in sauces.", 239, R.drawable.appetizers_chilli_chicken);
        Dish d6s = new Dish("Drums of HeavenS", "Chicken lollipop tossed in schezwan sauce.", 239, R.drawable.appetizers_drums_heaven);
        Dish d7s = new Dish("Thai Chilli BasilS", "Thai dish made with freshly chopped chicken thighs and fresh basil.", 239, R.drawable.appetizers_thai_chilli);

        dishesA.add(d1);
        dishesA.add(d2);
        dishesA.add(d3);
        dishesA.add(d4);
        dishesA.add(d5);
        dishesA.add(d6);
        dishesA.add(d7);

        dishesM.add(d1m);
        dishesM.add(d2m);
        dishesM.add(d3m);
        dishesM.add(d4m);
        dishesM.add(d5m);
        dishesM.add(d6m);
        dishesM.add(d7m);

        dishesS.add(d1s);
        dishesS.add(d2s);
        dishesS.add(d3s);
        dishesS.add(d4s);
        dishesS.add(d5s);
        dishesS.add(d6s);
        dishesS.add(d7s);

        sharedPreferences = this.getSharedPreferences("com.mydarkappfactory.hudsoncafe", Context.MODE_PRIVATE);
        finalizedDishes = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
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
                for(int i = 0; i < dishesM.size(); i++) {
                    if (dishesM.get(i).getQuantity() > 0) {
                        Dish dish = dishesM.get(i);
                        Gson gson = new Gson();
                        String dishJson = gson.toJson(dish);
                        finalizedDishes.add(dishJson);
                    }
                }
                for(int i = 0; i < dishesS.size(); i++) {
                    if (dishesS.get(i).getQuantity() > 0) {
                        Dish dish = dishesS.get(i);
                        Gson gson = new Gson();
                        String dishJson = gson.toJson(dish);
                        finalizedDishes.add(dishJson);
                    }
                }
                for(int i = 0; i < dishesA.size(); i++) {
                    if (dishesA.get(i).getQuantity() > 0) {
                        Dish dish = dishesA.get(i);
                        Gson gson = new Gson();
                        String dishJson = gson.toJson(dish);
                        finalizedDishes.add(dishJson);
                    }
                }
                try {
                    sharedPreferences.edit().putString("finalizedDishes", ObjectSerializer.serialize(finalizedDishes)).apply();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SubmenuActivity.this, CartActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}