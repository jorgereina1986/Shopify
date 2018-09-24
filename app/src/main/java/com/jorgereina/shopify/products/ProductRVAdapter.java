package com.jorgereina.shopify.products;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorgereina.shopify.R;
import com.jorgereina.shopify.products.models.Product;

public class ProductRVAdapter extends ListAdapter<Product, ProductRVAdapter.ProductViewHolder> {
    public ProductRVAdapter() {
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
        productViewHolder.productTitle.setText(product.getTitle());
    }

    public static final DiffUtil.ItemCallback<Product> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Product>() {
                @Override
                public boolean areItemsTheSame(@NonNull Product product, @NonNull Product t1) {
                    return product.getTitle().equals(t1.getTitle());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Product product, @NonNull Product t1) {
                    return product.equals(t1);
                }
            };

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productTitle;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productTitle = itemView.findViewById(R.id.product_title_tv);
        }
    }
}

