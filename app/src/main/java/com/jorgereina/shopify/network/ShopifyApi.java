package com.jorgereina.shopify.network;

import com.jorgereina.shopify.models.ShopifyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopifyApi {

    //admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6
    @GET("admin/products.json?")
    Call<ShopifyResponse> getProducts(
            @Query("page") int page,
            @Query("access_token") String accessToken
    );
}
