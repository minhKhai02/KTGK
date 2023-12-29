package com.example.KTGK;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.KTGK.DAO.ProductDAO;
import com.example.KTGK.adapter.ProductAdapter;
import com.example.KTGK.models.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private ProductAdapter adapter;
    private ProductDAO productDAO;
    Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rcvProduct = findViewById(R.id.rcvProduct);
        productDAO = new ProductDAO(this);

        // Lấy danh sách sản phẩm từ ProductDAO
        List<Product> listProduct = productDAO.GetAll();

        // Tạo adapter và thiết lập RecyclerView
        adapter = new ProductAdapter(listProduct);
        rcvProduct.setAdapter(adapter);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvProduct.addItemDecoration(itemDecoration);

        //Animators
        //ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeItem(adapter, this));
        //itemTouchHelper.attachToRecyclerView(rcvProduct);

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the custom dialog layout
                View viewDialog = getLayoutInflater().inflate(R.layout.dialog_product, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(viewDialog);
                AlertDialog alert = builder.create();
                alert.show();
                //tiếp tục viết sự kiện trong viewDialog
                EditText txtName = viewDialog.findViewById(R.id.edtName);
                EditText txtImage = viewDialog.findViewById(R.id.edtImage);
                EditText txtprice = viewDialog.findViewById(R.id.edtPrice);
                //sụ kien Save
                viewDialog.findViewById(R.id.btnDialogSaveProduct).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Product p = new Product(txtName.getText().toString(), Float.parseFloat(txtprice.getText().toString()), txtImage.getText().toString());
                        productDAO.add(p); // Thêm sản phẩm mới vào cơ sở dữ liệu
                        listProduct.clear(); // Xóa danh sách cũ
                        listProduct.addAll(productDAO.GetAll()); // Lấy danh sách sản phẩm mới từ cơ sở dữ liệu
                        adapter.notifyDataSetChanged(); // Cập nhật RecyclerView
                        Toast.makeText(viewDialog.getContext(), "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
                        alert.dismiss(); // Đóng dialog
                    }
                });
            }
        });



    }
}