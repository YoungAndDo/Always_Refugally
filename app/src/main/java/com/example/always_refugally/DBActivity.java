package com.example.always_refugally;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.always_refugally.DBCLASS.Item;
import com.example.always_refugally.DBCLASS.Product;
import com.example.always_refugally.DBCLASS.SearchData;
import com.example.always_refugally.DBCLASS.Store;
import com.example.always_refugally.DBCLASS.StoreData;
import com.example.always_refugally.DBCLASS.UserInterface;
import com.example.always_refugally.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DBActivity extends AppCompatActivity {
//    private static final String BASE = "http://127.0.0.1:8000/";
    private static final String BASE = "http://39.117.12.76:8000/";

    //EditText position;
    TextView info;
    HashMap<String, Integer> input;
    ArrayList<Store> sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        Intent intent = getIntent();
        input = (HashMap<String, Integer>) intent.getExtras().get("item");

        /** 네트워킹 시작부분 */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserInterface apiService =
                retrofit.create(UserInterface.class);
        List<Item> item_list = new ArrayList<Item>();
        for(String s : input.keySet())
        {
            item_list.add(new Item(s, input.get(s)));
        }
        SearchData sd = new SearchData();
        sd.setItem(item_list);
        Call<StoreData> call = apiService.search(sd);
        call.enqueue(new Callback<StoreData>() {
            @Override
            public void onResponse(Call<StoreData> call, Response<StoreData> response) {
                int statusCode = response.code();
                StoreData ret = response.body();
                sl = (ArrayList)ret.getStore();
                for (Store s : sl)
                {
                    Log.d("nyan", "onResponse: " + s.getName());
                    Log.d("nyan", "onResponse: " + s.getLat());
                    Log.d("nyan", "onResponse: " + s.getLon());
                    Log.d("nyan", "onResponse: " + s.getTotal());
                    List<Product> pl = s.getProduct();
                    for (Product p : pl)
                    {
                        Log.d("nyan", "onResponse: " + p.getName());
                        Log.d("nyan", "onResponse: " + p.getPid());
                        Log.d("nyan", "onResponse: " + p.getPrice());
                    }
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i;
                        i = new Intent(DBActivity.this, MapActivity.class);
                        i.putExtra("store", sl);
                        startActivity(i);
                    }
                }, 1500);
            }
            @Override
            public void onFailure(Call<StoreData> call, Throwable t) {
                Log.d("nyan", "onFailure: " + t.toString());
            }
        });
    }
}

