package com.jorgereina.shopify.products;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.jorgereina.shopify.products.models.Product;

public class ProductViewModel extends ViewModel {

    public LiveData productPagedList;
    LiveData<PageKeyedDataSource<Integer, Product>> liveDataSource;

    public ProductViewModel() {
        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory();
        liveDataSource = productDataSourceFactory.getProductLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(50).build();

        //Building the paged list
        productPagedList = (new LivePagedListBuilder(productDataSourceFactory, pagedListConfig))
                .build();
    }
}
