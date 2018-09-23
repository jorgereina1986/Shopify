package com.jorgereina.shopify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jorgereina.shopify.products.ui.ProductsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new ProductsFragment())
                .addToBackStack(null)
                .commit();
    }
}
