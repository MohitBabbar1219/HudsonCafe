package com.darkappfactory.hudsoncafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dragonslayer on 29/10/17.
 */

public class DishListAdapter extends ArrayAdapter {
    ArrayList<Dish> dishes;
    Context context;
    String tableName;
    SQLiteDatabase db;

    public DishListAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Dish> dishes, String tableName, SQLiteDatabase db) {
        super(context, resource, dishes);
        this.context = context;
        this.dishes = dishes;
        this.tableName = tableName;
        this.db = db;
    }

    @Override
    public int getCount() {
        return dishes.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.row_layout, parent, false);

        final Dish dish = dishes.get(position);

        Log.d("Hudson", "getView: " + parent.getId());
        final DishViewHolder dishViewHolder = new DishViewHolder();
        dishViewHolder.dishName = (TextView) view.findViewById(R.id.dishName);
        dishViewHolder.dishPrice = (TextView) view.findViewById(R.id.dishPrice);
        dishViewHolder.dishQuantity = (TextView) view.findViewById(R.id.dishQty);
        dishViewHolder.dishRating = (TextView) view.findViewById(R.id.dishRating);
        dishViewHolder.dishImage = (ImageView) view.findViewById(R.id.dishImg);
        dishViewHolder.minusButt = (Button) view.findViewById(R.id.minusButt);
        dishViewHolder.plusButt = (Button) view.findViewById(R.id.plusButt);

        dishViewHolder.plusButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dishes.get(position).incrementQuantity();
                Log.d("Hudson", dishes.get(position).getQuantity() + "");
                quantityUpdater(position, dish.getQuantity());
                dishViewHolder.dishQuantity.setText(dish.getQuantity() + "");
            }
        });

        dishViewHolder.minusButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dishes.get(position).decrementQuantity();
                Log.d("Hudson", dishes.get(position).getQuantity() + "");
                quantityUpdater(position, dish.getQuantity());
                dishViewHolder.dishQuantity.setText(dish.getQuantity() + "");
            }
        });

        dishViewHolder.dishName.setText(dish.getName());
        dishViewHolder.dishQuantity.setText(dish.getQuantity() + "");
        dishViewHolder.dishPrice.setText("₹" + dish.getPrice());
        dishViewHolder.dishImage.setImageResource(dish.getImageResId());
        dishViewHolder.dishRating.setText("3");
        return view;
    }

    private void quantityUpdater(int position, int quantity) {
        ContentValues recordValues = new ContentValues();
        recordValues.put("QUANTITY", quantity);
        db.update(tableName, recordValues, "_id = ?", new String[]{Integer.toString(position + 1)});
    }

    private static class DishViewHolder{
        TextView dishName, dishRating, dishPrice, dishQuantity;
        ImageView dishImage;
        Button minusButt, plusButt;
    }

}
