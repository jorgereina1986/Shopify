package com.jorgereina.shopify.products.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopifyResponse {

    @SerializedName("products")
    List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
