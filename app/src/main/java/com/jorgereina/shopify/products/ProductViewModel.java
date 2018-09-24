package com.jorgereina.shopify.products;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.jorgereina.shopify.products.models.DataRepository;
import com.jorgereina.shopify.products.models.Product;

import java.util.List;

public class ProductViewModel extends ViewModel {

    private DataRepository dataRepository = new DataRepository();

    public MutableLiveData<List<Product>> getMutableProductList() {
        return dataRepository.getProductList();
    }
}
