package com.example.always_refugally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.always_refugally.DBCLASS.Store;

import java.util.ArrayList;
import java.util.PriorityQueue;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @Bind(R.id.total_price_1)TextView total_1;

    @Bind(R.id.store_logo_1)ImageView logo_1;

    @Bind(R.id.store_name_1)TextView name_1;

    @Bind(R.id.store_1_item_1)TextView item_1_1;

    @Bind(R.id.store_1_item_2)TextView item_1_2;

    @Bind(R.id.store_1_item_3)TextView item_1_3;

    @Bind(R.id.total_price_2)TextView total_2;

    @Bind(R.id.store_logo_2)ImageView logo_2;

    @Bind(R.id.store_name_2)TextView name_2;

    @Bind(R.id.store_2_item_1)TextView item_2_1;

    @Bind(R.id.store_2_item_2)TextView item_2_2;

    @Bind(R.id.store_2_item_3)TextView item_2_3;

    @Bind(R.id.total_price_3)TextView total_3;

    @Bind(R.id.store_logo_3)ImageView logo_3;

    @Bind(R.id.store_name_3)TextView name_3;

    @Bind(R.id.store_3_item_1)TextView item_3_1;

    @Bind(R.id.store_3_item_2)TextView item_3_2;

    @Bind(R.id.store_3_item_3)TextView item_3_3;

    @Bind(R.id.total_price_4)TextView total_4;

    @Bind(R.id.store_logo_4)ImageView logo_4;

    @Bind(R.id.store_name_4)TextView name_4;

    @Bind(R.id.store_4_item_1)TextView item_4_1;

    @Bind(R.id.store_4_item_2)TextView item_4_2;

    @Bind(R.id.store_4_item_3)TextView item_4_3;

    int count;
    Store best_store;
    Store cheapest_store;
    Store closest_store;
    Store online_store;
    Store cur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        best_store = new Store();
        cheapest_store = new Store();
        closest_store = new Store();
        online_store = new Store();
        cur = new Store();

        setFourStores();

        cur = best_store;
        setEachItem(total_1, name_1, logo_1, item_1_1, item_1_2, item_1_3);
        cur = cheapest_store;
        setEachItem(total_2, name_2, logo_2, item_2_1, item_2_2, item_2_3);
        cur = closest_store;
        setEachItem(total_3, name_3, logo_3, item_3_1, item_3_2, item_3_3);
        cur = online_store;
        setEachItem(total_4, name_4, logo_4, item_4_1, item_4_2, item_4_3);

    }

    private void setEachItem(TextView total, TextView name, ImageView logo, TextView item_1,
                             TextView item_2, TextView item_3){

        total.setText(cur.getTotal() + "원");
        name.setText(cur.getName()+" "+cur.getdis()+"m");
        if(cur.getName().startsWith("GS")){
            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/gs25_logo.jpg")
                    .into(logo);
        }else if (cur.getName().startsWith("세븐")){
            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/seven_logo.jpg")
                    .into(logo);
        }else if(cur.getName().startsWith("CU")){
            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/cu_logo.jpg")
                    .into(logo);
        }else if(cur.getName().startsWith("미니")){
            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/mini_logo.jpg")
                    .into(logo);
        }else if(cur.getName().startsWith("위드")){
            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/with_logo.jpg")
                    .into(logo);
        }else if(cur.getName().startsWith("365")){
            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/365_logo.jpg")
                    .into(logo);
        }else if(cur.getName().startsWith("이마트")){
            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/emart_logo.jpg")
                    .into(logo);
        }else if(cur.getName().startsWith("지마켓")){
            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/gmarket_logo.png")
                    .into(logo);
            name.setText(cur.getName());
        }
        switch(count){
            case 3:
                item_3.setText(cur.getProduct().get(2).getName()
                        +"/"+cur.getProduct().get(2).getPid()+"EA"
                        +"/"+cur.getProduct().get(2).getPrice()+"원");
            case 2:
                item_2.setText(cur.getProduct().get(1).getName()
                        +"/"+cur.getProduct().get(1).getPid()+"EA"
                        +"/"+cur.getProduct().get(1).getPrice()+"원");
            case 1:
                item_1.setText(cur.getProduct().get(0).getName()
                        +"/"+cur.getProduct().get(0).getPid()+"EA"
                        +"/"+cur.getProduct().get(0).getPrice()+"원");
                break;
            case 0:
                break;
        }
        if(count == 0){
            item_1.setText("");
            item_2.setText("");
            item_3.setText("");
        } else if(count == 1){
            item_2.setText("");
            item_3.setText("");
        }else if(count == 2){
            item_3.setText("");
        }
    }


    private void setFourStores() {
//
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
            if(min > result)
            {
                min = result;
                index = i;
            }
        }
        best_store = sl.get(index);
        index = 0;
        for(int i = 0; i < total_list.size(); i++){
            result = total_list.get(i)*0.9 + dist_list.get(i)*0.1;
            if(min > result)
            {
                min = result;
                index = i;
            }
        }
        cheapest_store = sl.get(index);
        index = 0;
        for(int i = 0; i < total_list.size(); i++){
            result = total_list.get(i)*0.1 + dist_list.get(i)*0.9;
            if(min > result)
            {
                min = result;
                index = i;
            }
        }
        closest_store = sl.get(index);


        online_store = sl.get(index);
    }
}
