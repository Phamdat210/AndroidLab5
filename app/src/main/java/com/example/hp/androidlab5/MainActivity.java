package com.example.hp.androidlab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.hp.androidlab5.adapter.ProductAdapter;
import com.example.hp.androidlab5.dao.ProductDAO;
import com.example.hp.androidlab5.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Product> dsProduct = new ArrayList<>();
    private ListView lv;
    SQLite sqLite;
    ProductAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        sqLite = new SQLite(MainActivity.this);
        dsProduct = sqLite.getAllProduct();

        adapter = new ProductAdapter(MainActivity.this,dsProduct);
        lv.setAdapter(adapter);

    }

    public void viewAdd(View view) {
        Intent i =  new Intent(MainActivity.this,AddProductActivity.class);
        startActivity(i);
        finish();
    }
}
