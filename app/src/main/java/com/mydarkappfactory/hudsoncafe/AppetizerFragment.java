package com.mydarkappfactory.hudsoncafe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AppetizerFragment extends Fragment {

    View view;
    ListView listView;

    public AppetizerFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.appetizers, container, false);

        ArrayList<Dish> dishes = new ArrayList<>();

        listView = (ListView) view.findViewById(R.id.listView);


        Dish d1 = new Dish("Veg Bruschetta", "Veg Bruschetta is grilled bread rubbed with garlic and topped with olive oil and salt, tomato, vegetables, beans, and cheese.", 139, R.drawable.appetizers_quesadilla);
        Dish d2 = new Dish("Nachos", "Crispy Nachos topped with tomato salsa, sour cream, jalapenos and kidney beans.", 159, R.drawable.appetizers_nachos);
        Dish d3 = new Dish("Quesadilla Veg", "Hand rolled tortilla filled with melted cheese, beans, jalapenos and peppers.", 179, R.drawable.appetizers_bruschetta);
        Dish d4 = new Dish("Cottage Cheeze Skewers", "Cubes of cattage cheese marinated and grilled on sticks.", 179, R.drawable.appetizers_cottage_cheeze);
        Dish d5 = new Dish("Chilli Chicken Dry", "Boneless chicken cubes are marinated in soya sauce, chili sauce and pepper, deep fried and seasoned again in sauces.", 239, R.drawable.appetizers_chilli_chicken);
        Dish d6 = new Dish("Drums of Heaven", "Chicken lollipop tossed in schezwan sauce.", 239, R.drawable.appetizers_drums_heaven);
        Dish d7 = new Dish("Thai Chilli Basil", "Thai dish made with freshly chopped chicken thighs and fresh basil.", 239, R.drawable.appetizers_thai_chilli);

        dishes.add(d1);
        dishes.add(d2);
        dishes.add(d3);
        dishes.add(d4);
        dishes.add(d5);
        dishes.add(d6);
        dishes.add(d7);

        ListAdapter customAdapter = new ListAdapter(getContext(), R.layout.row_layout, dishes);

        listView.setAdapter(customAdapter);

        return view;



    }
}
