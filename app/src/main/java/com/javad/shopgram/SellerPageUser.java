package com.javad.shopgram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.javad.shopgram.adapter.AdapterAll;
import com.javad.shopgram.adapter.AdapterNewProduct;
import com.javad.shopgram.adapter.AdapterVitrin;
import com.javad.shopgram.model.Info.Data;
import com.javad.shopgram.model.Info.InfoSeller;
import com.javad.shopgram.model.Info.ModelInfoSeller;
import com.javad.shopgram.model.all.AllProduct;
import com.javad.shopgram.model.all.ModelAllProduct;
import com.javad.shopgram.model.newProduct.ModelNewProduct;
import com.javad.shopgram.model.newProduct.NewProduct;
import com.javad.shopgram.model.vitrin.ModelVitrin;
import com.javad.shopgram.model.vitrin.Vitrin;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.General;
import com.javad.shopgram.util.PrefManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SellerPageUser extends FragmentActivity {
    TextView countFollowers, countProducts, nameSHOP;
    ImageView logoShop;
    ImageView back;
    CircleImageView iconShop;
    RecyclerView newProduct;
    RecyclerView vitrinProduct;
    CardView folowSet;
    LinearLayout layout;
    GridView allProduct;
    Bundle bundle;
    PrefManager prefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_seller_page);

        find();
        getInfoShop();
        setNewProduct();
        setVitrinProduct();
        setAllProduct();
        folowSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFollow();
            }
        });
    }

    private void setAllProduct() {
        new Req(SellerPageUser.this, Req.root + "/product/all", new onRequest() {
            @Override
            public void isSucess(String response) {
                Gson gson = new Gson();
                ModelAllProduct modelAllProduct = gson.fromJson(response, ModelAllProduct.class);
                if (modelAllProduct.getStatus() == 200 && modelAllProduct.getResult() == 1) {
                    List<AllProduct> list = modelAllProduct.getData().getAllProducts();
                    AdapterAll adapterAll = new AdapterAll(SellerPageUser.this, list);
                    allProduct.setAdapter(adapterAll);
                }
            }

            @Override
            public void isFailed(String error) {

            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                Map<String, String> map = new HashMap<>();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("token", prefManager.getTokenUser());
                    jsonObject.put("seller_id", bundle.getInt("id"));
                    map.put("data", jsonObject.toString());
                } catch (Exception e) {

                }
                return map;
            }
        });
    }

    private void setVitrinProduct() {
        new Req(this, Req.root + "/product/vitrin", new onRequest() {
            @Override
            public void isSucess(String response) {
                Gson gson = new Gson();
                ModelVitrin modelVitrin = gson.fromJson(response, ModelVitrin.class);
                if (modelVitrin.getStatus() == 200 && modelVitrin.getResult() == 1) {
                    List<Vitrin> vitrin = modelVitrin.getData().getVitrin();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SellerPageUser.this, LinearLayoutManager.HORIZONTAL, true);
                    vitrinProduct.setLayoutManager(linearLayoutManager);
                    AdapterVitrin adapterVitrin = new AdapterVitrin(SellerPageUser.this, vitrin);
                    vitrinProduct.setAdapter(adapterVitrin);

                }
            }

            @Override
            public void isFailed(String error) {

            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                Map<String, String> map = new HashMap<>();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("token", prefManager.getTokenUser());
                    jsonObject.put("seller_id", bundle.getInt("id"));
                    map.put("data", jsonObject.toString());
                } catch (Exception e) {

                }
                return map;
            }
        });
    }

    public void setNewProduct() {
        new Req(this, Req.root + "/product/new", new onRequest() {
            @Override
            public void isSucess(String response) {
                Gson gson = new Gson();
                ModelNewProduct modelNewProduct = gson.fromJson(response, ModelNewProduct.class);
                if (modelNewProduct.getResult() == 1 && modelNewProduct.getStatus() == 200) {
                    List<NewProduct> newProductList = modelNewProduct.getData().getNewProducts();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SellerPageUser.this, LinearLayoutManager.HORIZONTAL, true);
                    newProduct.setLayoutManager(linearLayoutManager);

                    Log.e("PPP", newProductList.size() + "");

                    AdapterNewProduct adapterNewProduct = new AdapterNewProduct(SellerPageUser.this, newProductList);
                    newProduct.setAdapter(adapterNewProduct);
                }
            }

            @Override
            public void isFailed(String error) {

            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                Map<String, String> map = new HashMap<>();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("token", prefManager.getTokenUser());
                    jsonObject.put("seller_id", bundle.getInt("id"));
                    map.put("data", jsonObject.toString());
                } catch (Exception e) {

                }
                return map;
            }
        });
    }

    public void find() {
        layout = findViewById(R.id.linMain);
        folowSet = findViewById(R.id.follow);
        nameSHOP = findViewById(R.id.title_shop);
        countProducts = findViewById(R.id.count_product);
        countFollowers = findViewById(R.id.count_follower_shop);
        logoShop = findViewById(R.id.logo_shop);
        iconShop = findViewById(R.id.icon_shop);
        newProduct = findViewById(R.id.newProduct_recycle);
        vitrinProduct = findViewById(R.id.vitrin_product_recycle);
        allProduct = findViewById(R.id.gridview_all_product);

    }

    private void setFollow() {
        new Req(this, Req.root + "/shop/setfollow", new onRequest() {
            @Override
            public void isSucess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String s = jsonObject.getString("data");
                    JSONObject jsonObject1 = new JSONObject(s);
                    if (jsonObject.getString("result").equals("130")){
                        Toast.makeText(SellerPageUser.this, "فروشگاه آنفالو شد", Toast.LENGTH_SHORT).show();

                    }
                    if (jsonObject.getString("result").equals("129")){
                        Toast.makeText(SellerPageUser.this, "فروشگاه فالو شد", Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void isFailed(String error) {

            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                Map<String, String> map = new HashMap<>();
                JSONObject json = new JSONObject();
                try {
                    json.put("token", prefManager.getTokenUser());
                    json.put("seller_id", bundle.getInt("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                map.put("data", json.toString());
                return map;
            }
        });
    }

    public void getInfoShop() {
        prefManager = new PrefManager(this);
        bundle = getIntent().getExtras();
        new Req(this, Req.root + "/shop/info", new onRequest() {
            @Override
            public void isSucess(String response) {
                Gson gson = new Gson();
                ModelInfoSeller seller = gson.fromJson(response, ModelInfoSeller.class);
                Data data = seller.getData();
                List<InfoSeller> infoSellers = data.getInfoSeller();
                InfoSeller infoSeller = infoSellers.get(0);
                nameSHOP.setText(infoSeller.getNameShop());
                countProducts.setText(infoSeller.getProductsCount());
                countFollowers.setText(infoSeller.getFollowersCount());
                Log.e("QWE", infoSeller.getPicUrl() + "    " + infoSeller.getLogoUrl());
                Picasso.get()
                        .load(Req.rootAsli + infoSeller.getPicUrl())
                        .placeholder(General.image_placeHolder)
                        .error(General.image_noPicure)
                        .into(iconShop);
                Picasso.get().load(Req.rootAsli + infoSeller.getLogoUrl())
                        .placeholder(General.image_placeHolder)
                        .error(General.image_noPicure)
                        .into(logoShop);
            }

            @Override
            public void isFailed(String s) {
                countFollowers.setText(General.failMssg);
                countProducts.setText(General.failMssg);
                nameSHOP.setText(General.failMssg);
            }

            @Override
            public void OnProgress() {
                countFollowers.setText(General.progress);
                countProducts.setText(General.progress);
                nameSHOP.setText(General.progress);
            }

            @Override
            public Map<String, String> Paramets() {
                Map<String, String> map = new HashMap<>();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("token", prefManager.getTokenUser());
                    jsonObject.put("seller_id", bundle.getInt("id"));
                    map.put("data", jsonObject.toString());
                } catch (Exception e) {

                }
                return map;
            }
        });
    }

    public void follow(View view) {
//        129 mean Follow
//        130 mean unFollow
        new Req(SellerPageUser.this, Req.root + "/shop/setfollow", new onRequest() {
            @Override
            public void isSucess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String s = jsonObject.getString("result");
                    if (s.equals("129")) {
                        General.customSnack(layout, "با موفقیت فروشگاه فالو شد");
                    }
                    if (s.equals("130"))
                        General.customSnack(layout, "با موفقیت فروشگاه آنفاالو شد");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void isFailed(String error) {

            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                return null;
            }
        });
    }
}
