package com.example.testoneclean;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.testoneclean.components.ProductAdapter;
import com.example.testoneclean.model.Container;
import com.example.testoneclean.model.Product;
import com.example.testoneclean.network.ClientInstance;
import com.example.testoneclean.network.DataService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        DataService service = ClientInstance.getRetrofitInstance().create(DataService.class);
        Call<Container> call = service.getProducts();
        call.enqueue(new Callback<Container>() {
            @Override
            public void onResponse(Call<Container> call, Response<Container> response) {
                adapter.setData(response.body().getProducts());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Container> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error...Please try later!", Toast.LENGTH_LONG).show();
            }
        });

        bindView();
    }

    private void bindView() {
        recyclerView = findViewById(R.id.items);
        adapter = new ProductAdapter(new ArrayList<Product>(), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}