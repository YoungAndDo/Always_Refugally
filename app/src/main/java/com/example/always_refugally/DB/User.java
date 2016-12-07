package com.example.always_refugally.DB;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yd199 on 2016-11-26.
 */

public class User implements Parcelable {
    String user_id;
    String name;
    String pw;
    String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public User() {super(); }
    public User(Parcel in) {
        super();
        this.user_id = in.readString();
        this.name = in.readString();
        this.pw = in.readString();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getUser_id());
        parcel.writeString(getName());
        parcel.writeString(getPw());
    }

    public String db_barcode(String bar)
    {
        if(bar.equals("8801062640645"))
        {
            return "누드 빼빼로";
        }
        else if(bar.equals("8801056038861"))
        {
            return "핫식스";
        }
        return "";
    }
}
