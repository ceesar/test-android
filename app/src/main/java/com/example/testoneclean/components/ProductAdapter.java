package com.example.testoneclean.components;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testoneclean.MainActivity;
import com.example.testoneclean.R;
import com.example.testoneclean.model.Size;
import com.example.testoneclean.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private MainActivity mContext;
    private ActionMode mActionMode;
    private ActionMode.Callback mCallback;
    private boolean multiSelect;
    private List<Product> mData;
    private List<Product> mSelectedItems;

    public ProductAdapter(MainActivity context) {
        mContext = context;
        multiSelect = false;
        mData = new ArrayList<>();
        mSelectedItems = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        final Product product = mData.get(position);
        holder.id.setText("ID: " + product.getId());
        holder.name.setText("Name: " + product.getName());
        holder.color.setText("Color: " + product.getColors().get(0).getNameColor());

        String sizeResult = "";
        for (Size size : product.getColors().get(0).getSize()) {
            sizeResult += size.getName() + " : " + size.getQuantity() + "\n";
        }

        holder.size.setText(sizeResult);

        if (mSelectedItems.contains(product)) {
            holder.mView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.mView.setBackgroundColor(Color.WHITE);
        }

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ((AppCompatActivity)view.getContext()).startSupportActionMode(actionModeCallbacks);
                holder.selectItem(product);
                return true;
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (multiSelect) {
                    holder.selectItem(product);

                    if (mSelectedItems.isEmpty()) {
                        mActionMode.finish();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            ProductAdapter.this.mActionMode = actionMode;
            multiSelect = true;
            menu.add("Send");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            mContext.sendProducts(mSelectedItems);
            mSelectedItems.clear();
            actionMode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            multiSelect = false;
            mSelectedItems.clear();
            notifyDataSetChanged();
        }
    };

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

        void selectItem(Product item) {
            if (multiSelect) {
                if (mSelectedItems.contains(item)) {
                    mSelectedItems.remove(item);
                    mView.setBackgroundColor(Color.WHITE);
                } else {
                    mSelectedItems.add(item);
                    mView.setBackgroundColor(Color.LTGRAY);
                }
            }
        }
    }
}