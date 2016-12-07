package com.example.always_refugally;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.always_refugally.DB.Product;
import com.example.always_refugally.DBDAO.ProductDBDAO;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler;
    NotificationManager manager;
    Notification noti;

    ProductDBDAO productDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        noti = new Notification.Builder(this)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("현재 할인 중인 상품이 있습니다.")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.drawable.app_icon)
                .build();

        insertProductData();
        mHandler = new Handler();
        mHandler.postDelayed(runnable, 2*1000);

    }

    private void insertProductData() {
        productDB = new ProductDBDAO(SplashActivity.this);

        String img_URL = "http://yd1993.dothome.co.kr/test/";
        String[] prod_list = {"코카콜라", "미에로 화이바", "핫식스", "새우깡", "누드 빼빼로"};

        for(int i = 0 ; i < 5; i++){
            Product temp = new Product();
            temp.setName(prod_list[i]);
            temp.setImg_url(img_URL + "product_"+(i+1)+".jpg");
            productDB.insert(temp);
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            manager.notify(0, noti);

            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    };
}
