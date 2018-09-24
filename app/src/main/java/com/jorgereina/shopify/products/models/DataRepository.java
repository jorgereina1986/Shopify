package com.jorgereina.shopify.products.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jorgereina.shopify.products.network.RetrofitClient;
import com.jorgereina.shopify.products.network.ShopifyApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private static final String TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";
    private static final int FIRST_PAGE = 1; //requirements only state to get data from first page

    private ShopifyApi service;

    public MutableLiveData<List<Product>> getProductList() {
        final MutableLiveData mutableProductList = new MutableLiveData();
        service = RetrofitClient.getClient().create(ShopifyApi.class);
        Call<ShopifyResponse> call = service.getProducts(FIRST_PAGE, TOKEN);
        call.enqueue(new Callback<ShopifyResponse>() {
            @Override
            public void onResponse(Call<ShopifyResponse> call, Response<ShopifyResponse> response) {
                if (response.body() != null) {
                    Log.d("lagarto", "onResponse: " + response.body().getProductList().get(0).getTags());
                    mutableProductList.postValue(response.body().getProductList());
                }
            }

            @Override
            public void onFailure(Call<ShopifyResponse> call, Throwable t) {

            }
        });
        return mutableProductList;
    }
}
