package com.example.hp.androidlab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.androidlab5.dao.ProductDAO;
import com.example.hp.androidlab5.model.Product;

public class AddProductActivity extends AppCompatActivity {

    private EditText edID;
    private EditText edName;
    private EditText edPrice;
    SQLite sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        edID = (EditText) findViewById(R.id.edID);
        edName = (EditText) findViewById(R.id.edName);
        edPrice = (EditText) findViewById(R.id.edPrice);

        try {
            Intent intent = getIntent();
            if (intent != null) {
                Bundle bundle = intent.getBundleExtra("bundle");
                edID.setText(bundle.getString("id"));
                edName.setText(bundle.getString("name"));
                edPrice.setText(bundle.getString("price"));
            }
        } catch (Exception ex){

        }

    }

    public void addProduct(View view) {
        sqLite = new SQLite(AddProductActivity.this);
        Product product = new Product(edID.getText().toString(), edName.getText().toString(), edPrice.getText().toString());
        if (sqLite.insertProduct(product)==1){
            Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddProductActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
        }
    }

    public void updateProduct(View view) {
        sqLite = new SQLite(AddProductActivity.this);
        Product product = new Product(edID.getText().toString(),
                edName.getText().toString(),
                edPrice.getText().toString());
        if (sqLite.updateProduct(product)==1){
            Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddProductActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Cập nhật thất bại",Toast.LENGTH_LONG).show();
        }
    }

    public void cancelProduct(View view) {
        Intent intent = new Intent(AddProductActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
