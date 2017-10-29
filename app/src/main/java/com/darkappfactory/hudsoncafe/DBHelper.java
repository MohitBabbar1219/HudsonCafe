package com.darkappfactory.hudsoncafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "DISHES";
    private static final int DB_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertRecord(SQLiteDatabase db,String tableName, String name, String description,int price, int resourceId, int rating, int quantity) {
        ContentValues recordValues = new ContentValues();
        recordValues.put("NAME", name);
        recordValues.put("DESCRIPTION", description);
        recordValues.put("PRICE", price);
        recordValues.put("RATING", rating);
        recordValues.put("QUANTITY", quantity);
        recordValues.put("IMG_RES_ID", resourceId);
        db.insert(tableName, null, recordValues);
    }

    private static void insertEmailPassword(SQLiteDatabase db, String email, String password) {
        ContentValues recordValues = new ContentValues();
        recordValues.put("EMAIL", email);
        recordValues.put("PASSWORD", password);
        db.insert("EMAIL_PASSWORD", null, recordValues);
    }

    private static void insertTableNumberOtp(SQLiteDatabase db, int tableNum, int otp) {
        ContentValues recordValues = new ContentValues();
        recordValues.put("TABLE_NUMBER", tableNum);
        recordValues.put("OTP", otp);
        db.insert("TABLE_OTP", null, recordValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVer, int newVer) {
        if (oldVer < 1) {
            db.execSQL("CREATE TABLE APPETIZERS(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "PRICE INTEGER, "
                    + "RATING INTEGER, "
                    + "QUANTITY INTEGER, "
                    + "IMG_RES_ID INTEGER);");

            db.execSQL("CREATE TABLE SHAKES(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "PRICE INTEGER, "
                    + "RATING INTEGER, "
                    + "QUANTITY INTEGER, "
                    + "IMG_RES_ID INTEGER);");

            db.execSQL("CREATE TABLE MAIN_COURSE(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "PRICE INTEGER, "
                    + "RATING INTEGER, "
                    + "QUANTITY INTEGER, "
                    + "IMG_RES_ID INTEGER);");

            db.execSQL("CREATE TABLE TABLE_OTP(_id INTEGER PRIMARY KEY AUTOINCREMENT, TABLE_NUMBER INTEGER, OTP INTEGER);");

            db.execSQL("CREATE TABLE EMAIL_PASSWORD(_id INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, PASSWORD TEXT);");

            insertEmailPassword(db, "null", "null");

            insertTableNumberOtp(db, -1, -1);

            insertRecord(db, "APPETIZERS", "Veg Bruschetta", "Veg Bruschetta is grilled bread rubbed with garlic and topped with olive oil and salt, tomato, vegetables, beans, and cheese.", 139, R.drawable.appetizers_quesadilla, 4, 0);
            insertRecord(db, "APPETIZERS","Nachos", "Crispy Nachos topped with tomato salsa, sour cream, jalapenos and kidney beans.", 159, R.drawable.appetizers_nachos, 4, 0);
            insertRecord(db, "APPETIZERS","Quesadilla Veg", "Hand rolled tortilla filled with melted cheese, beans, jalapenos and peppers.", 179, R.drawable.appetizers_bruschetta, 4, 0);
            insertRecord(db, "APPETIZERS","Cottage Cheeze Skewers", "Cubes of cattage cheese marinated and grilled on sticks.", 179, R.drawable.appetizers_cottage_cheeze, 4, 0);
            insertRecord(db, "APPETIZERS","Chilli Chicken Dry", "Boneless chicken cubes are marinated in soya sauce, chili sauce and pepper, deep fried and seasoned again in sauces.", 239, R.drawable.appetizers_chilli_chicken, 4, 0);
            insertRecord(db, "APPETIZERS","Drums of Heaven", "Chicken lollipop tossed in schezwan sauce.", 239, R.drawable.appetizers_drums_heaven, 4, 0);
            insertRecord(db, "APPETIZERS","Thai Chilli Basil", "Thai dish made with freshly chopped chicken thighs and fresh basil.", 239, R.drawable.appetizers_thai_chilli, 4, 0);

            insertRecord(db, "SHAKES", "Veg BruschettaS", "Veg Bruschetta is grilled bread rubbed with garlic and topped with olive oil and salt, tomato, vegetables, beans, and cheese.", 139, R.drawable.appetizers_quesadilla, 4, 0);
            insertRecord(db, "SHAKES","NachosS", "Crispy Nachos topped with tomato salsa, sour cream, jalapenos and kidney beans.", 159, R.drawable.appetizers_nachos, 4, 0);
            insertRecord(db, "SHAKES","Quesadilla VegS", "Hand rolled tortilla filled with melted cheese, beans, jalapenos and peppers.", 179, R.drawable.appetizers_bruschetta, 4, 0);
            insertRecord(db, "SHAKES","Cottage Cheeze SkewersS", "Cubes of cattage cheese marinated and grilled on sticks.", 179, R.drawable.appetizers_cottage_cheeze, 4, 0);
            insertRecord(db, "SHAKES","Chilli Chicken DryS", "Boneless chicken cubes are marinated in soya sauce, chili sauce and pepper, deep fried and seasoned again in sauces.", 239, R.drawable.appetizers_chilli_chicken, 4, 0);
            insertRecord(db, "SHAKES","Drums of HeavenS", "Chicken lollipop tossed in schezwan sauce.", 239, R.drawable.appetizers_drums_heaven, 4, 0);
            insertRecord(db, "SHAKES","Thai Chilli BasilS", "Thai dish made with freshly chopped chicken thighs and fresh basil.", 239, R.drawable.appetizers_thai_chilli, 4, 0);

            insertRecord(db, "MAIN_COURSE", "Veg BruschettaM", "Veg Bruschetta is grilled bread rubbed with garlic and topped with olive oil and salt, tomato, vegetables, beans, and cheese.", 139, R.drawable.appetizers_quesadilla, 4, 0);
            insertRecord(db, "MAIN_COURSE","NachosM", "Crispy Nachos topped with tomato salsa, sour cream, jalapenos and kidney beans.", 159, R.drawable.appetizers_nachos, 4, 0);
            insertRecord(db, "MAIN_COURSE","Quesadilla VegM", "Hand rolled tortilla filled with melted cheese, beans, jalapenos and peppers.", 179, R.drawable.appetizers_bruschetta, 4, 0);
            insertRecord(db, "MAIN_COURSE","Cottage Cheeze SkewersM", "Cubes of cattage cheese marinated and grilled on sticks.", 179, R.drawable.appetizers_cottage_cheeze, 4, 0);
            insertRecord(db, "MAIN_COURSE","Chilli Chicken DryM", "Boneless chicken cubes are marinated in soya sauce, chili sauce and pepper, deep fried and seasoned again in sauces.", 239, R.drawable.appetizers_chilli_chicken, 4, 0);
            insertRecord(db, "MAIN_COURSE","Drums of HeavenM", "Chicken lollipop tossed in schezwan sauce.", 239, R.drawable.appetizers_drums_heaven, 4, 0);
            insertRecord(db, "MAIN_COURSE","Thai Chilli BasilM", "Thai dish made with freshly chopped chicken thighs and fresh basil.", 239, R.drawable.appetizers_thai_chilli, 4, 0);

        }

        if (oldVer < 2) {
            //TODO
        }
    }

}
