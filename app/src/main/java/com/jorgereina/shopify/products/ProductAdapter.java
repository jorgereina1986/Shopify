package com.jorgereina.shopify.products;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorgereina.shopify.MainActivity;
import com.jorgereina.shopify.R;
import com.jorgereina.shopify.products.models.Product;
import com.jorgereina.shopify.tags.TagsFragment;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder> {
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

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView productTitle;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.product_title_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("lagarto", "onClick: " + getAdapterPosition());

            MainActivity mainActivity = (MainActivity) view.getContext();

            mainActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new TagsFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}

