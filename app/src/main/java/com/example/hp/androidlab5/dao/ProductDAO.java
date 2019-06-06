package com.example.hp.androidlab5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hp.androidlab5.SQLite;
import com.example.hp.androidlab5.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public SQLite dbHelper;
    public SQLiteDatabase db;

//    public ProductDAO(Context context){
//        dbHelper = new SQLite(context);
//        db = dbHelper.getWritableDatabase();
//    }
//
//    public int insertProduct(Product product){
//        ContentValues values = new ContentValues();
//        values.put("id",product.getId());
//        values.put("name",product.getName());
//        values.put("price",product.getPrice());
//
//        try{
//            if (db.insert(TABLE_NAME,null,values)<0){
//                return -1;
//            }
//        }catch (Exception ex){
//            Log.e("ProductDAO",ex.getMessage());
//        }
//
//        return 1;
//    }
//
//    public List<Product> getAllProduct(){
//        List<Product> ls = new ArrayList<Product>();
//        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast()==false){
//            Product product = new Product();
//            product.setId(cursor.getString(0));
//            product.setName(cursor.getString(1));
//            product.setPrice(cursor.getString(2));
//            ls.add(product);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return ls;
//    }

}

