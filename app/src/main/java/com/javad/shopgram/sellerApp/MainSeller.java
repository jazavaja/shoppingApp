package com.javad.shopgram.sellerApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

import com.javad.shopgram.R;

public class MainSeller extends FragmentActivity {
    LinearLayout income;
    LinearLayout profile;
    LinearLayout tabligat;
    LinearLayout aboutus;
    LinearLayout shop;
    LinearLayout learn;
    LinearLayout btnUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_main_layout);
        find();
    }

    private void find() {
        income=findViewById(R.id.income);
        profile=findViewById(R.id.profile);
        tabligat=findViewById(R.id.tabligat);
        aboutus=findViewById(R.id.aboutus);
        shop=findViewById(R.id.shop);
        learn=findViewById(R.id.learn);
        btnUser=findViewById(R.id.btnUser);
    }
}
