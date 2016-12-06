package com.example.always_refugally;

import android.content.Intent;
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
    Button getButton;
    Button searchButton;
    TextView info;
    HashMap<String, Integer> input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        Intent intent = getIntent();
        input = (HashMap<String, Integer>) intent.getExtras().get("item");
        getButton = (Button) findViewById(R.id.loadUserData);
        searchButton = (Button) findViewById(R.id.loadRepositories);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("nyan", "Nyan");
//                SendData send = new SendData();
//                try {
//                    send.run(input);
//                    Log.d("nyan", "Success");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserInterface apiService =
                        retrofit.create(UserInterface.class);
//                Item i1 = new Item();
//                Item i2 = new Item();
//                Item i3 = new Item();
//                Item i4 = new Item();
//                i1.setPid("코카콜라");
//                i2.setPid("누드 빼빼로");
//                i3.setPid("핫식스");
//                i4.setPid("새우깡");
//                i1.setVolume(3);
//                i2.setVolume(1);
//                i3.setVolume(4);
//                i4.setVolume(6);
                List<Item> item_list = new ArrayList<Item>();
                for(String s : input.keySet())
                {
                    item_list.add(new Item(s, input.get(s)));
                }
//                item_list.add(i1);
//                item_list.add(i2);
//                item_list.add(i3);
//                item_list.add(i4);
                SearchData sd = new SearchData();
                sd.setItem(item_list);
                Call<StoreData> call = apiService.search(sd);
                call.enqueue(new Callback<StoreData>() {
                    @Override
                    public void onResponse(Call<StoreData> call, Response<StoreData> response) {
                        int statusCode = response.code();
                        StoreData ret = response.body();
                        List<Store> sl = ret.getStore();
                        for (Store s : sl)
                        {
                            Log.d("nyan", "onResponse: " + s.getName());
                            //Log.d("nyan", "onResponse: " + s.getLat());
                            //Log.d("nyan", "onResponse: " + s.getLon());
                            Log.d("nyan", "onResponse: " + s.getTotal());
                            List<Product> pl = s.getProduct();
                            for (Product p : pl)
                            {
                                Log.d("nyan", "onResponse: " + p.getName());
                                Log.d("nyan", "onResponse: " + p.getPid());
                                Log.d("nyan", "onResponse: " + p.getPrice());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<StoreData> call, Throwable t) {
                        Log.d("nyan", "onFailure: " + t.toString());
                    }
                });
            }
        });
    }
}

