package com.darkappfactory.hudsoncafe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class ShakesFragment extends Fragment {

    View view;
    ListView listView;
    DishListAdapter dishListAdapter;

    public ShakesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shakes, container, false);

        listView = (ListView) view.findViewById(R.id.listViewS);

        dishListAdapter = new DishListAdapter(getContext(), R.layout.row_layout, SubmenuActivity.dishesS, "SHAKES", SubmenuActivity.db);
        listView.setAdapter(dishListAdapter);
        return view;

    }
}
