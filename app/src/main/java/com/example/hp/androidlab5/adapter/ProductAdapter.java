package com.example.hp.androidlab5.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.androidlab5.AddProductActivity;
import com.example.hp.androidlab5.R;
import com.example.hp.androidlab5.SQLite;
import com.example.hp.androidlab5.model.Product;

import java.util.List;

public class ProductAdapter  extends BaseAdapter{

    private Context context;
    private List<Product> productList;
    public SQLite sqLite;

    public ProductAdapter(Context context, List<Product> productList){
        this.productList = productList;
        this.context = context;
        sqLite = new SQLite(context);

    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        TextView tvname=convertView.findViewById(R.id.tvName);
        TextView tvprice=convertView.findViewById(R.id.tvPrice);
        Button btnDel=convertView.findViewById(R.id.btnDeleted);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLite.deleteProduct(productList.get(position).getId());
                Product product=productList.get(position);
                productList.remove(product);
                notifyDataSetChanged();
            }
        });

        final Product prod=(Product) getItem(position);
        tvname.setText(prod.getName());
        tvprice.setText(prod.getPrice());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product= productList.get(position);
                Intent intent= new Intent(context,AddProductActivity.class);
                Bundle bundle= new Bundle();
                bundle.putString("id",product.getId());
                bundle.putString("name",product.getName());
                bundle.putString("price",product.getPrice());
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

//    public static class ViewHolder{
//        public ImageView ivProduct;
//        public TextView tvName, tvPrice;
//        public Button btnDel;
//
//    }
}


