package com.jorgereina.shopify;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.jorgereina.shopify.models.Product;

public class ProductViewModel extends ViewModel {

    LiveData productPagedList;
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
