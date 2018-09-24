package com.jorgereina.shopify.products.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorgereina.shopify.R;
import com.jorgereina.shopify.products.NewProductViewModel;
//import com.jorgereina.shopify.products.ProductAdapter;
import com.jorgereina.shopify.products.ProductRVAdapter;
//import com.jorgereina.shopify.products.ProductViewModel;
import com.jorgereina.shopify.products.models.Product;

import java.util.List;

public class ProductsFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.products_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        NewProductViewModel productViewModel = ViewModelProviders.of(this.getActivity()).get(NewProductViewModel.class);

        final ProductRVAdapter adapter = new ProductRVAdapter();


        productViewModel.getMutableProductList().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapter.submitList(products);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
