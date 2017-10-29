package com.darkappfactory.hudsoncafe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

class Functions {

    static HashMap<String, String> threeArrayListToHashmap(ArrayList<Dish> dishesM, ArrayList<Dish> dishesS, ArrayList<Dish> dishesA) {
        //Converts three ArrayLists to HashMap<String, String>
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < dishesM.size(); i++) {
            if (dishesM.get(i).getQuantity() > 0) {
                Dish dish = dishesM.get(i);
                Gson gson = new Gson();
                String dishJson = gson.toJson(dish);
                hashMap.put(dish.getName(), dishJson);
            }
        }
        for (int i = 0; i < dishesS.size(); i++) {
            if (dishesS.get(i).getQuantity() > 0) {
                Dish dish = dishesS.get(i);
                Gson gson = new Gson();
                String dishJson = gson.toJson(dish);
                hashMap.put(dish.getName(), dishJson);
            }
        }
        for (int i = 0; i < dishesA.size(); i++) {
            if (dishesA.get(i).getQuantity() > 0) {
                Dish dish = dishesA.get(i);
                Gson gson = new Gson();
                String dishJson = gson.toJson(dish);
                hashMap.put(dish.getName(), dishJson);
            }
        }

        return hashMap;

    }

    static HashMap<String, String> updateHashMapFromArrayLists(HashMap<String, String> hashMap, ArrayList<Dish> dishesM, ArrayList<Dish> dishesS, ArrayList<Dish> dishesA) {
        //Updates existing HashMap according to three ArrayLists
        for (int i = 0; i < dishesM.size(); i++) {
            if (dishesM.get(i).getQuantity() > 0) {
                Dish dish = dishesM.get(i);
                Gson gson = new Gson();
                String dishJson = gson.toJson(dish);
                hashMap.put(dish.getName(), dishJson);
            }
        }
        for (int i = 0; i < dishesS.size(); i++) {
            if (dishesS.get(i).getQuantity() > 0) {
                Dish dish = dishesS.get(i);
                Gson gson = new Gson();
                String dishJson = gson.toJson(dish);
                hashMap.put(dish.getName(), dishJson);
            }
        }
        for (int i = 0; i < dishesA.size(); i++) {
            if (dishesA.get(i).getQuantity() > 0) {
                Dish dish = dishesA.get(i);
                Gson gson = new Gson();
                String dishJson = gson.toJson(dish);
                hashMap.put(dish.getName(), dishJson);
            }
        }

        return hashMap;

    }

    static String hashMapToJson(HashMap<String, String> hashMap) {
        //Converts HashMap<String,String> to Json
        Gson gson = new Gson();
        return gson.toJson(hashMap);
    }

    static HashMap<String, String> jsonToHashMap(String json) {
        //Converts HashMap in Json to HashMap<String, String>
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<HashMap<String, String>>() {}.getType());
    }

    static Dish dishFromJson(String json) {
        //Converts dish in json to Dish object
        Gson gson = new Gson();
        return gson.fromJson(json, Dish.class);
    }

    static String dishToJson(Dish dish) {
        //Converts Dish object to Json
        Gson gson = new Gson();
        return gson.toJson(dish);
    }

}
