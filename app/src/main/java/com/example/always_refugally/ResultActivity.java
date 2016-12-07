package com.example.always_refugally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.always_refugally.DBCLASS.Store;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ResultActivity extends AppCompatActivity {

    int count;
    Store best_store;
    Store cheapest_store;
    Store closest_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setThreeStore();


    }

    private void setThreeStore() {
        Intent intent = getIntent();
        ArrayList<Store> sl = (ArrayList)intent.getSerializableExtra("store");
        ArrayList<Integer> total_list = new ArrayList<Integer>();
        ArrayList<Double> dist_list = new ArrayList<Double>();
        Double result;

        count = sl.get(0).getProduct().size();

        for(Store s : sl) {
            total_list.add(s.getTotal());
            dist_list.add(s.getdis());
        }
        int index = 0;
        double min = 10000000;
        for(int i = 0; i < total_list.size(); i++){
            result = total_list.get(i)*1 + dist_list.get(i)*1;
            if(min > result) index = i;
        }
        best_store = sl.get(index);

        for(int i = 0; i < total_list.size(); i++){
            result = total_list.get(i)*0.9 + dist_list.get(i)*0.1;
            if(min > result) index = i;
        }
        cheapest_store = sl.get(index);

        for(int i = 0; i < total_list.size(); i++){
            result = total_list.get(i)*0.1 + dist_list.get(i)*0.9;
            if(min > result) index = i;
        }
        closest_store = sl.get(index);
    }
}
