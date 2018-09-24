package com.jorgereina.shopify.products.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://shopicruit.myshopify.com/";
//    private static RetrofitClient mInstance;
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
//    private RetrofitClient() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }
//
//    public static synchronized RetrofitClient getInstance() {
//        if (mInstance == null) {
//            mInstance = new RetrofitClient();
//        }
//        return mInstance;
//    }
//
//    public ShopifyApi getApi() {
//        return retrofit.create(ShopifyApi.class);
//    }
}
