package com.example.always_refugally.DBCLASS;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dodo on 2016. 12. 6..
 */
public class SendData extends Thread {
    private static final String BASE = "http://10.0.2.2:8000/";
    public void run(HashMap<String, Integer> input) throws IOException {
        super.run();
        List<Item> item_list = new ArrayList<Item>();
        for(String s : input.keySet())
        {
            item_list.add(new Item(s, input.get(s)));
        }
//        Item i1 = new Item();
//        Item i2 = new Item();
//        Item i3 = new Item();
//        Item i4 = new Item();
//        i1.setPid("코카콜라");
//        i2.setPid("누드 빼빼로");
//        i3.setPid("핫식스");
//        i4.setPid("새우깡");
//        i1.setVolume(3);
//        i2.setVolume(1);
//        i3.setVolume(4);
//        i4.setVolume(6);
//        item_list.add(i1);
//        item_list.add(i2);
//        item_list.add(i3);
//        item_list.add(i4);
        SearchData sd = new SearchData();
        sd.setItem(item_list);
//        TaskService taskService = ServiceGenerator.createService(TaskService.class);
//        Call<List<Task>> call = taskService.getTasks();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserInterface apiService =
                retrofit.create(UserInterface.class);
        Call<StoreData> call = apiService.search(sd);
//        Response<StoreData> response = call.execute();
//        StoreData ret = response.body();
//        StoreData ret = call.execute().body();
//        Log.d("nyan", "SendData : " + ret.toString());
//        return ret;
        call.enqueue(new Callback<StoreData>() {
            @Override
            public void onResponse(Call<StoreData> call, Response<StoreData> response) {
                int statusCode = response.code();
                StoreData ret = response.body();
                List<Store> sl = ret.getStore();
                for (Store s : sl)
                {
                    Log.d("nyan", "onResponse: " + s.getName());
                    Log.d("nyan", "onResponse: " + s.getLat());
                    Log.d("nyan", "onResponse: " + s.getLon());
                    Log.d("nyan", "onResponse: " + s.getTotal());
                    List<Product> pl = new ArrayList<Product>();
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
}
