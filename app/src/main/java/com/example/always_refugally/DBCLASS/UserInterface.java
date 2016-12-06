package com.example.always_refugally.DBCLASS;

import com.example.always_refugally.DBCLASS.SearchData;
import com.example.always_refugally.DBCLASS.StoreData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Dodo on 2016. 12. 6..
 */
public interface UserInterface {
//    @GET("user/{username}/")
//    Call<Store> getUser(@Path("username") String username);
    @POST("search/")
    Call<StoreData> search(@Body SearchData para);
}
