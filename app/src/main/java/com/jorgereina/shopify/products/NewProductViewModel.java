package com.jorgereina.shopify.products;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.jorgereina.shopify.products.models.DataRepository;
import com.jorgereina.shopify.products.models.Product;

import java.util.List;

public class NewProductViewModel extends ViewModel {

    private DataRepository dataRepository = new DataRepository();

    private MutableLiveData<List<Product>> mutableProductList = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getMutableProductList() {
        return dataRepository.getProductList();
    }
}
