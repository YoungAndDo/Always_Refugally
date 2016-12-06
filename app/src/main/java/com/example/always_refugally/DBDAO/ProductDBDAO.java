package com.example.always_refugally.DBDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.always_refugally.DB.DBLiteral;
import com.example.always_refugally.DB.DatabaseHelper;
import com.example.always_refugally.DB.Product;
import com.example.always_refugally.DB.User;

/**
 * Created by yd199 on 2016-12-07.
 */

public class ProductDBDAO {
    DatabaseHelper helper;
    SQLiteDatabase database;
    Context context;
    private String tableName;
    private String[] columnList;

    public ProductDBDAO(Context context){
        this.context = context;
        this.helper = DatabaseHelper.getHelper(context);
        database = this.helper.getWritableDatabase();
        tableName = DBLiteral.PRODUCT_TABLE;
        columnList = new String[]{DBLiteral.product_id_column, DBLiteral.imgUrl_column};
        open();
    }

    private void open() {
        if(this.helper == null){
            this.helper = DatabaseHelper.getHelper(context);
            this.database = this.helper.getWritableDatabase();
        }
    }

    public void insert(Product data){
        ContentValues values = getContentValues(data);

        try{
            database.insert(tableName, null, values);
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
    }

    @NonNull
    private ContentValues getContentValues(Product data){
        ContentValues values = new ContentValues();
        values.put(DBLiteral.product_id_column, data.getName());
        values.put(DBLiteral.imgUrl_column, data.getImg_url());
        return values;
    }

    public void delete(String name){
        database.delete(tableName,
                DBLiteral.WHERE_PRODUCT_ID_EQUALS, new String[]{name});
    }
    public void update(Product data, String name){
        ContentValues values = getContentValues(data);
        database.update(tableName, values,
                DBLiteral.WHERE_PRODUCT_ID_EQUALS, new String[]{name});
    }
    private Product cursorToProduct(Cursor cursor){
        Product data = new Product();
        data.setName(cursor.getString(0));
        data.setImg_url(cursor.getString(1));
        return data;
    }

    public Product selectById(String name){
        Cursor cursor = database.query(tableName, columnList,
                DBLiteral.WHERE_PRODUCT_ID_EQUALS, new  String[]{name}, null, null, null);
        if(cursor.getCount() == 0){
            return null;
        }
        cursor.moveToFirst();
        return cursorToProduct(cursor);
    }

}
