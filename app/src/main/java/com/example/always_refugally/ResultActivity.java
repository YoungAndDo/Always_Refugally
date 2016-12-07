package com.example.always_refugally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.always_refugally.DBCLASS.Store;

import java.util.ArrayList;
import java.util.PriorityQueue;

import butterknife.Bind;

public class ResultActivity extends AppCompatActivity {

    @Bind(R.id.total_1)TextView total_1;

    @Bind(R.id.store_logo_1)ImageView logo_1;

    @Bind(R.id.store_name_1)TextView name_1;

    @Bind(R.id.store_1_item_1)TextView item_1_1;

    @Bind(R.id.store_1_item_2)TextView item_1_2;

    @Bind(R.id.store_1_item_3)TextView item_1_3;

    @Bind(R.id.total_2)TextView total_2;

    @Bind(R.id.store_logo_2)ImageView logo_2;

    @Bind(R.id.store_name_2)TextView name_2;

    @Bind(R.id.store_2_item_1)TextView item_2_1;

    @Bind(R.id.store_2_item_2)TextView item_2_2;

    @Bind(R.id.store_2_item_3)TextView item_2_3;

    @Bind(R.id.total_3)TextView total_3;

    @Bind(R.id.store_logo_3)ImageView logo_3;

    @Bind(R.id.store_name_3)TextView name_3;

    @Bind(R.id.store_3_item_1)TextView item_3_1;

    @Bind(R.id.store_3_item_2)TextView item_3_2;

    @Bind(R.id.store_3_item_3)TextView item_3_3;

    int count;
    Store best_store;
    Store cheapest_store;
    Store closest_store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setThreeStore();

        setEachItem(best_store, total_1, name_1, logo_1, item_1_1, item_1_2, item_1_3);
        setEachItem(cheapest_store, total_2, name_2, logo_2, item_2_1, item_2_2, item_2_3);
        setEachItem(closest_store, total_3, name_3, logo_3, item_3_1, item_3_2, item_3_3);

//        total_1.setText(best_store.getTotal() + "원");
//        name_1.setText(best_store.getName());
//        if(best_store.getName().startsWith("GS")){
//            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/gs25_logo.jpg")
//                    .into(logo_1);
//        }else if (best_store.getName().startsWith("세븐")){
//            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/seven_logo.jpg")
//                    .into(logo_1);
//        }else if(best_store.getName().startsWith("CU")){
//            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/cu_logo.jpg")
//                    .into(logo_1);
//        }else if(best_store.getName().startsWith("미니")){
//            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/mini_logo.jpg")
//                    .into(logo_1);
//        }else if(best_store.getName().startsWith("위드")){
//            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/with_logo.jpg")
//                    .into(logo_1);
//        }else if(best_store.getName().startsWith("365")){
//            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/365_logo.jpg")
//                    .into(logo_1);
//        }else if(best_store.getName().startsWith("이마트")){
//            Glide.with(ResultActivity.this).load("http://yd1993.dothome.co.kr/test/emart_logo.jpg")
//                    .into(logo_1);
//        }
//        switch(count){
//            case 3:
//                item_1_3.setText(best_store.getProduct().get(3).getName()
//                        +"/"+best_store.getProduct().get(3).getPrice()+"원");
//            case 2:
//                item_1_2.setText(best_store.getProduct().get(1).getName()
//                        +"/"+best_store.getProduct().get(1).getPrice()+"원");
//            case 1:
//                item_1_1.setText(best_store.getProduct().get(0).getName()
//                +"/"+best_store.getProduct().get(0).getPrice()+"원");
//                break;
//            case 0:
//                break;
//        }



    }

    private void setEachItem(Store cur, TextView total, TextView name, ImageView logo, TextView item_1,
                             TextView item_2, TextView item_3){

        total.setText(cur.getTotal() + "원");
        name.setText(cur.getName());
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
        }
        switch(count){
            case 3:
                item_1.setText(cur.getProduct().get(3).getName()
                        +"/"+cur.getProduct().get(3).getPid()+"EA"
                        +"/"+cur.getProduct().get(3).getPrice()+"원");
            case 2:
                item_1.setText(cur.getProduct().get(1).getName()
                        +"/"+cur.getProduct().get(3).getPid()+"EA"
                        +"/"+cur.getProduct().get(1).getPrice()+"원");
            case 1:
                item_1.setText(cur.getProduct().get(0).getName()
                        +"/"+cur.getProduct().get(3).getPid()+"EA"
                        +"/"+cur.getProduct().get(0).getPrice()+"원");
                break;
            case 0:
                break;
        }
    }

    private void setThreeStore() {
//        Store temp = new Store();
//        temp.setTotal(10000);
//        temp.setName("365마트 천천");
//        temp.setdis(1000);
//        temp.setProduct();
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
