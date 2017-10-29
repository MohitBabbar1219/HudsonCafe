package com.darkappfactory.hudsoncafe;

import android.database.Cursor;

/**
 * Created by dragonslayer on 29/10/17.
 */

public class CartDish extends Dish {
    private String tableName;

    public CartDish(String name, String description, int price, int imageResId, String tableName) {
        super(name, description, price, imageResId);
        this.tableName = tableName;
    }

    public CartDish(Dish dish, String tableName) {
        super(dish);
        this.tableName = tableName;
    }

    public CartDish(Cursor cursor, String tableName) {
        super(cursor);
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
