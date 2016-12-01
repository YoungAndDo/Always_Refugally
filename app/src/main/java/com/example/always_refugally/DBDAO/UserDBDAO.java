package com.example.always_refugally.DBDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.always_refugally.DB.DBLiteral;
import com.example.always_refugally.DB.DatabaseHelper;
import com.example.always_refugally.DB.User;

import java.sql.SQLClientInfoException;

/**
 * Created by yd199 on 2016-11-26.
 */

public class UserDBDAO {
    DatabaseHelper helper;
    SQLiteDatabase database;
    Context context;
    private String tableName;
    private String[] columnList;

    public UserDBDAO(Context context){
        this.context = context;
        this.helper = DatabaseHelper.getHelper(context);
        database = this.helper.getWritableDatabase();
        tableName = DBLiteral.USER_TABLE;
        columnList = new String[]{DBLiteral.user_id_column, DBLiteral.name_column,
                DBLiteral.pw_column};
        open();
    }

    private void open() {
        if(this.helper == null){
            this.helper = DatabaseHelper.getHelper(context);
            this.database = this.helper.getWritableDatabase();
        }
    }

    public void insert(User data){
        ContentValues values = getContentValues(data);

        try{
            database.insert(tableName, null, values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
    }

    @NonNull
    private ContentValues getContentValues(User data){
        ContentValues values = new ContentValues();
        values.put(DBLiteral.user_id_column, data.getUser_id());
        values.put(DBLiteral.name_column, data.getName());
        values.put(DBLiteral.pw_column, data.getPw());
        return values;
    }

    public void delete(String user_id){
        database.delete(tableName,
                DBLiteral.WHERE_USER_ID_EQUALS, new String[]{user_id});
    }
    public void update(User data, String user_id){
        ContentValues values = getContentValues(data);
        database.update(tableName, values,
                DBLiteral.WHERE_USER_ID_EQUALS, new String[]{user_id});
    }
    private User cursorToUser(Cursor cursor){
        User data = new User();
        data.setUser_id(cursor.getString(0));
        data.setName(cursor.getString(1));
        data.setPw(cursor.getString(2));
        return data;
    }
    public User selectById(String user_id){
        Cursor cursor = database.query(tableName, columnList,
                DBLiteral.WHERE_USER_ID_EQUALS, new  String[]{user_id}, null, null, null);
        if(cursor.getCount() == 0){
            return null;
        }
        cursor.moveToFirst();
        return cursorToUser(cursor);
    }
}
