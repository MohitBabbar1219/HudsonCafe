package com.darkappfactory.hudsoncafe;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class AppetizerFragment extends Fragment {

    View view;
    ListView listView;
    DishListAdapter dishListAdapter;

    public AppetizerFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.appetizers, container, false);


        listView = (ListView) view.findViewById(R.id.listView);

        dishListAdapter = new DishListAdapter(getContext(), R.layout.row_layout, SubmenuActivity.dishesA, "APPETIZERS", SubmenuActivity.db);
        listView.setAdapter(dishListAdapter);
        return view;
    }
}
