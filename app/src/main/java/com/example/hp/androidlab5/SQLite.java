package com.example.hp.androidlab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hp.androidlab5.dao.ProductDAO;
import com.example.hp.androidlab5.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {
    public static final String DBNAME = "QLBanHang";
    public static final int VERSION = 1;
    private final String CREATE_TABLE = "CREATE TABLE Product (id VARCHAR PRIMARY KEY, name NVARCHAR, price NVARCHAR)";
    private final String COLUMN_ID = "id";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_PRICE = "price";
    private final String TABLE_NAME = "Product";
    public SQLite dbHelper;
    public SQLiteDatabase db;

    public SQLite(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
    }

    public long insertProduct(Product product){
        //xin quyền
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //ghép cặp dữ liệu
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,product.getId());
        contentValues.put(COLUMN_NAME,product.getName());
        contentValues.put(COLUMN_PRICE,product.getPrice());

        //sử dụng lệnh insert
        long result = sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_ID+"=?",new String[]{product.getId()});

        //đóng kết nối
        sqLiteDatabase.close();

        return result;
    }

    public int deleteProduct(String id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int result =  db.delete(TABLE_NAME, COLUMN_ID+"=?",new String[]{id});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    public int updateProduct(Product product){
        ContentValues values = new ContentValues();
        values.put("id",product.getId());
        values.put("name",product.getName());
        values.put("price",product.getPrice());

        try{
            if (db.update(TABLE_NAME,values, COLUMN_ID+"=?",new String[]{product.getId()})<0){
                return -1;
            }
        }catch (Exception ex){
            Log.e("ProductDAO",ex.getMessage());
        }

        return 1;
    }

    public List<Product> getAllProduct(){
        List<Product> productList = new ArrayList<>();
        //Xin quyền
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //viết câu lệnh select
        String select = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {
                Product product = new Product();
                product.setId(cursor.getString(0));
                product.setName(cursor.getString(1));
                product.setPrice(cursor.getString(2));

                productList.add(product);
            } while (cursor.moveToNext());

            cursor.close();
        }
        //đóng kết nối DB
        sqLiteDatabase.close();
        return productList;
    }
}
