package com.example.testoneclean.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testoneclean.R;
import com.example.testoneclean.model.Size;
import com.example.testoneclean.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> mData;
    private Context mContext;

    public ProductAdapter(List<Product> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    public void setData(List<Product> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.product_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mData.get(position);
        holder.id.setText("ID: " + product.getId());
        holder.name.setText("Name: " + product.getName());
        holder.color.setText("Color: " + product.getColors().get(0).getNameColor());

        String sizeResult = "";
        for (Size size : product.getColors().get(0).getSize()) {
            sizeResult += size.getName() + " : " + size.getQuantity() + "\n";
        }

        holder.size.setText(sizeResult);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView id;
        TextView name;
        TextView color;
        TextView size;

        ProductViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            id = mView.findViewById(R.id.id);
            name = mView.findViewById(R.id.name);
            color = mView.findViewById(R.id.color);
            size = mView.findViewById(R.id.size);
        }
    }
}