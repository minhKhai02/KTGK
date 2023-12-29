package com.example.KTGK.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.KTGK.DAO.ProductDAO;
import com.example.KTGK.R;
import com.example.KTGK.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> listProduct;
    private ProductDAO productDAO;
    public ProductAdapter(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewProduct = LayoutInflater.from( parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(viewProduct);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = listProduct.get(position);
        holder.txtName.setText(p.getName());
        holder.txtPrice.setText(String.valueOf(p.getPrice()));
        holder.deleteButton.setId(position);
     
        if(p.getImage().startsWith("https://"))
        {
            Glide.with(holder.imgView.getContext()).load(p.getImage()).into(holder.imgView);
        }
    }
    @Override
    public int getItemCount() {
        return listProduct.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtName, txtPrice;
        Button deleteButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.imgProduct);
            txtName = (TextView)   itemView.findViewById(R.id.txtName);
            txtPrice = (TextView)   itemView.findViewById(R.id.txtPrice);
            deleteButton = itemView.findViewById(R.id.xoa);


        }
    }
}
