package com.darkappfactory.hudsoncafe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ConfirmedOrdersActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_orders);

        SQLiteOpenHelper dbHelper = new DBHelper(ConfirmedOrdersActivity.this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("ALL_ORDERS", new String[]{"NAME", "QUANTITY", "PRICE"},
                null, null, null, null, null);
        cursor.moveToFirst();

        ArrayList<FirebaseDish> dishesToBeSent = new ArrayList<>();
        HashMap<String, FirebaseDish> finalOrders = new HashMap<>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
//            dishesToBeSent.add(new FirebaseDish(cursor.getString(0), cursor.getInt(1), cursor.getInt(2)));
            if (finalOrders.containsKey(cursor.getString(0))) {
                finalOrders.put(cursor.getString(0), new FirebaseDish(cursor.getString(0), cursor.getInt(1) + finalOrders.get(cursor.getString(0)).getQuantity(), cursor.getInt(2)));
            } else {
                finalOrders.put(cursor.getString(0), new FirebaseDish(cursor.getString(0), cursor.getInt(1), cursor.getInt(2)));
            }
        }

        StringBuilder dishString = new StringBuilder();
        StringBuilder priceString = new StringBuilder();
        int totalPrice = 0;

        Set<String> finalDishSet = finalOrders.keySet();

        for (String x: finalDishSet) {

            FirebaseDish dish = finalOrders.get(x);

            if (x.length() > 23) {
                dishString.append(x.substring(0, 22) + "... x" + dish.getQuantity() + "\n");
            } else {
                dishString.append(x + " x" + dish.getQuantity() + "\n");
            }

            priceString.append(dish.getPrice() + "\n");

            totalPrice += dish.getQuantity() * dish.getPrice();
        }

        double sgst = 0.09 * totalPrice;
        double cgst = 0.09 * totalPrice;

        dishString.append("\nSubtotal");

        priceString.append("\n" + totalPrice);

        dishString.append("\n\nSGST: 9%\nCGST: 9%\n\n~~~~~Total~~~~~");

        priceString.append("\n\n" + (int)cgst + "\n" + (int)sgst + "\n\n" + (int)(totalPrice + cgst + sgst) + ".00");

        cursor.close();

        TextView dishNames = findViewById(R.id.dishList);
        TextView priceList = findViewById(R.id.priceList);

        dishNames.setText(dishString);
        priceList.setText(priceString);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
