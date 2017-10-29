package com.darkappfactory.hudsoncafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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


public class CartListAdapter extends ArrayAdapter<CartDish> {

    ArrayList<CartDish> dishes;
    Context context;
    SQLiteDatabase db;

    public CartListAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<CartDish> dishes, SQLiteDatabase db) {
        super(context, resource, dishes);
        this.context = context;
        this.dishes = dishes;
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
        view = inflater.inflate(R.layout.cart_row_layout, parent, false);

        final CartDish dish = dishes.get(position);

        Log.d("Hudson", "getView: " + parent.getId());
        final DishViewHolder dishViewHolder = new DishViewHolder();
        dishViewHolder.dishName = (TextView) view.findViewById(R.id.dishNameCart);
        dishViewHolder.dishPrice = (TextView) view.findViewById(R.id.dishPriceCart);
        dishViewHolder.dishQuantity = (TextView) view.findViewById(R.id.dishQtyCart);
        dishViewHolder.dishRating = (TextView) view.findViewById(R.id.dishRatingCart);
        dishViewHolder.dishImage = (ImageView) view.findViewById(R.id.dishImgCart);
        dishViewHolder.removeButt = (Button) view.findViewById(R.id.removeDish);

        dishViewHolder.removeButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dishes.remove(position);
                ContentValues recordValues = new ContentValues();
                recordValues.put("QUANTITY", 0);
                db.update(dish.getTableName(), recordValues, "NAME = ?", new String[]{dish.getName()});
                notifyDataSetChanged();
            }
        });

        dishViewHolder.dishName.setText(dish.getName());
        dishViewHolder.dishQuantity.setText(dish.getQuantity() + "");
        dishViewHolder.dishPrice.setText("₹" + dish.getPrice());
        dishViewHolder.dishImage.setImageResource(dish.getImageResId());
        dishViewHolder.dishRating.setText("3");
        return view;

    }

    private static class DishViewHolder {
        TextView dishName, dishRating, dishPrice, dishQuantity;
        ImageView dishImage;
        Button removeButt;
    }
}
