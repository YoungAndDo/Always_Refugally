package com.example.always_refugally;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler;
    NotificationManager manager;
    Notification noti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        noti = new Notification.Builder(this)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("아메리카노가 할인중입니다")
                .setSmallIcon(R.drawable.our_logo)
                .build();

        mHandler = new Handler();
        mHandler.postDelayed(runnable, 2*1000);

        //DB때려넣기
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
