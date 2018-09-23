package com.jorgereina.shopify;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jorgereina.shopify.models.Product;
import com.jorgereina.shopify.models.ShopifyResponse;
import com.jorgereina.shopify.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDataSource extends PageKeyedDataSource<Integer, Product> {

    private static final int FIRST_PAGE = 1;
    private static final String TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Product> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getProducts(FIRST_PAGE, TOKEN)
                .enqueue(new Callback<ShopifyResponse>() {
                    @Override
                    public void onResponse(Call<ShopifyResponse> call, Response<ShopifyResponse> response) {
                        Log.d("lagarto", "onResponse: " + "on initial response success " + response.isSuccessful());
                        if (response.body() != null) {
                            callback.onResult(response.body().getProductList(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<ShopifyResponse> call, Throwable t) {
                        Log.d("lagarto", "onFailure: " + t.getMessage());
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getProducts(params.key, TOKEN)
                .enqueue(new Callback<ShopifyResponse>() {
                    @Override
                    public void onResponse(Call<ShopifyResponse> call, Response<ShopifyResponse> response) {
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
                            callback.onResult(response.body().getProductList(), adjacentKey);
                        }

                    }

                    @Override
                    public void onFailure(Call<ShopifyResponse> call, Throwable t) {
                        Log.d("lagarto", "onFailure: " + t.getMessage());
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getProducts(params.key, TOKEN)
                .enqueue(new Callback<ShopifyResponse>() {
                    @Override
                    public void onResponse(Call<ShopifyResponse> call, Response<ShopifyResponse> response) {
                        if (response.body() != null) {
                            Log.d("lagarto", "onResponse: " + params.key);
                            callback.onResult(response.body().getProductList(), params.key + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<ShopifyResponse> call, Throwable t) {
                        Log.d("lagarto", "onFailure: " + t.getMessage());

                    }
                });
    }
}
