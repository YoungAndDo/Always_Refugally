package com.example.always_refugally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.always_refugally.Adapter.MyRecyclerView;
import com.example.always_refugally.Model.Store;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {

    @Bind(R.id.productName)
    TextView name;

    @Bind(R.id.RecyclerView)
    RecyclerView recyclerView;

    MyRecyclerView adapter;
    ArrayList<Store> store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        store = new ArrayList<Store>();

        for(int i=0;i<10;i++){
            Store temp = new Store(R.drawable.logo_gs25, "Name"+i, "Address"+i,"100m", 1000+i);
            store.add(temp);
        }

        adapter = new MyRecyclerView(ListActivity.this, store);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
