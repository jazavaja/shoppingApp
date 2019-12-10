package com.javad.shopgram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.google.gson.Gson;
import com.javad.shopgram.adapter.AdapterSearchCategory;
import com.javad.shopgram.model.searchCategory.ModelSearchCategory;
import com.javad.shopgram.model.searchCategory.Product;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.General;
import com.javad.shopgram.util.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCategory extends FragmentActivity {
    GridView gridView;
    PullRefreshLayout refreshLayout;
    PrefManager prefManager;
    View failView;
    View empty;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);
        find();
        getProductFromCategory();

        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProductFromCategory();

            }
        });
    }

    private void getProductFromCategory() {
        new Req(this, Req.root + "/search/category", new onRequest() {
            @Override
            public void isSucess(String response) {
                refreshLayout.setRefreshing(false);
                Gson f = new Gson();
                ModelSearchCategory product = f.fromJson(response, ModelSearchCategory.class);
                List<Product> allProducts = product.getData().getProducts();
//                failView.setVisibility(View.GONE);
//                empty.setVisibility(View.GONE);
//                linearLayout.setVisibility(View.VISIBLE);
                if (allProducts.size()==0){
                    General.customSnack(linearLayout,"محصولی وجود ندارد");
                }

                AdapterSearchCategory adapterAll = new AdapterSearchCategory(ProductCategory.this, allProducts);
                gridView.setAdapter(adapterAll);
            }

            @Override
            public void isFailed(String error) {
                General.failSnackBar(linearLayout);
//                linearLayout.setVisibility(View.GONE);
//                empty.setVisibility(View.GONE);
//                failView.setVisibility(View.VISIBLE);
            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                Bundle bundle = getIntent().getExtras();
                Map<String, String> stringStringHashMap = new HashMap<>();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("token", prefManager.getTokenUser());
                    assert bundle != null;
                    jsonObject.put("category_id", bundle.getInt("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                stringStringHashMap.put("data", jsonObject.toString());
                return stringStringHashMap;
            }
        });
    }

    private void find() {
        gridView = findViewById(R.id.gridview);
        refreshLayout = findViewById(R.id.refresh);
        prefManager = new PrefManager(this);
        failView = findViewById(R.id.fail);
        empty = findViewById(R.id.empty);
        linearLayout = findViewById(R.id.view);
    }

}

