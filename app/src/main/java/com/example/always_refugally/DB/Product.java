package com.example.always_refugally.DB;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by yd199 on 2016-11-18.
 */

public class Product implements Parcelable {
    String name;
    String img_url;

    public Product() {super(); }
    public Product(Parcel in) {
        super();
        this.name = in.readString();
        this.img_url = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getName());
        parcel.writeString(getImg_url());
    }
}
