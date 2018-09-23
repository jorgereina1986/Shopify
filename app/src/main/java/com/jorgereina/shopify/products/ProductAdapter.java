package com.jorgereina.shopify.products;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorgereina.shopify.R;
import com.jorgereina.shopify.products.models.Product;

public class ProductAdapter extends PagedListAdapter<Product, ProductAdapter.ProductViewHolder> {

    public ProductAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Product product = getItem(i);

        if (product != null) {
            productViewHolder.textView.setText(product.getTitle());
        }

    }

    private static DiffUtil.ItemCallback<Product> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Product>() {
                @Override
                public boolean areItemsTheSame(Product oldItem, Product newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle());
                }

                @Override
                public boolean areContentsTheSame(Product oldItem, Product newItem) {
                    return oldItem.equals(newItem);
                }
            };


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.product_title_tv);
        }
    }
}
