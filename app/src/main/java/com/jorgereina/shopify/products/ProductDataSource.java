package com.jorgereina.shopify.products;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jorgereina.shopify.products.models.Product;
import com.jorgereina.shopify.products.models.ShopifyResponse;
import com.jorgereina.shopify.products.network.RetrofitClient;
import com.jorgereina.shopify.products.network.ShopifyApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDataSource extends PageKeyedDataSource<Integer, Product> {

    private static final String TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";
    private static final int FIRST_PAGE = 1;

    private List<String> tagsList = new ArrayList<>();
    private ShopifyApi service;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Product> callback) {


        service = RetrofitClient.getClient().create(ShopifyApi.class);
        Call<ShopifyResponse> call = service.getProducts(FIRST_PAGE, TOKEN);
        call.enqueue(new Callback<ShopifyResponse>() {
            @Override
            public void onResponse(Call<ShopifyResponse> call, Response<ShopifyResponse> response) {
                if (response.body() != null) {
                    callback.onResult(response.body().getProductList(), null, FIRST_PAGE + 1);

                    Log.d("lagarto", "onResponse: " + response.body().getProductList().get(0).getTags());
                    for (Product product : response.body().getProductList()) {
                        String[] tags = product.getTags().split(",");
                        tagsList.addAll(Arrays.asList(tags));
                    }
                }
            }

            @Override
            public void onFailure(Call<ShopifyResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {

        service = RetrofitClient.getClient().create(ShopifyApi.class);
        Call<ShopifyResponse> call = service.getProducts(params.key, TOKEN);
        call.enqueue(new Callback<ShopifyResponse>() {
            @Override
            public void onResponse(Call<ShopifyResponse> call, Response<ShopifyResponse> response) {
                if (response.body() != null) {
                    callback.onResult(response.body().getProductList(), params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<ShopifyResponse> call, Throwable t) {

            }
        });
    }
}
