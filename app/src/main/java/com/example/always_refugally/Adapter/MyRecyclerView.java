package com.example.always_refugally.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.always_refugally.Model.Store;
import com.example.always_refugally.R;

import java.util.List;

/**
 * Created by yd199 on 2016-11-18.
 */

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.CustomViewHolder> {

    private Context mContext;
    List<Store> store;

    public MyRecyclerView(Context mContext, List<Store> store) {
        this.mContext = mContext;
        this.store = store;
    }


    @Override
    public MyRecyclerView.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.viewholder_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerView.CustomViewHolder holder, int i) {
        Store item = store.get(i);
        holder.logo.setBackgroundResource(item.getLogo());
        holder.name.setText(item.getName());
        holder.address.setText("주소: "+item.getAddress());
        holder.distance.setText(item.getDistance());
        holder.price.setText(item.getPrice()+"원");
    }

    @Override
    public int getItemCount() {
        return (store != null ? store.size(): 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView logo;
        protected TextView name, address, distance, price;
        protected View view;

        public CustomViewHolder(View view) {
            super(view);
            this.view = view;
            this.logo = (ImageView) view.findViewById(R.id.logoIV);
            this.name = (TextView) view.findViewById(R.id.nameTV);
            this.address = (TextView) view.findViewById(R.id.addressTV);
            this.distance = (TextView) view.findViewById(R.id.disanceTV);
            this.price = (TextView) view.findViewById(R.id.priceTV);
        }
    }
}
