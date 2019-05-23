package com.example.testoneclean;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.testoneclean.components.ProductAdapter;
import com.example.testoneclean.model.Container;
import com.example.testoneclean.model.ContainerToSend;
import com.example.testoneclean.model.Product;
import com.example.testoneclean.model.ProductToSend;
import com.example.testoneclean.network.ClientInstance;
import com.example.testoneclean.network.DataService;

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
                progressDialog.dismiss();
                adapter.setData(response.body().getProducts());
            }

            @Override
            public void onFailure(Call<Container> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error...Please try later!", Toast.LENGTH_LONG).show();
            }
        });

        bindView();
    }

    public void sendProducts(List<Product> products) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending data....");
        progressDialog.show();

        ContainerToSend container = new ContainerToSend();
        List<ProductToSend> productsToSend = new ArrayList<>();

        for(Product product : products) {
            ProductToSend prodTemp = new ProductToSend();
            prodTemp.setId(product.getId());
            prodTemp.setColor(product.getColors().get(0).getNameColor());
            prodTemp.setAmount(product.getAvailable());
            prodTemp.setSize(product.getColors().get(0).getSize().get(0).getName());

            productsToSend.add(prodTemp);
        }

        container.setProducts(productsToSend);

        DataService service = ClientInstance.getRetrofitInstance().create(DataService.class);
        Call<ContainerToSend> call = service.sendProducts(container);
        call.enqueue(new Callback<ContainerToSend>() {
            @Override
            public void onResponse(Call<ContainerToSend> call, Response<ContainerToSend> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Successful!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ContainerToSend> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error...Please try later!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void bindView() {
        recyclerView = findViewById(R.id.items);
        adapter = new ProductAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}