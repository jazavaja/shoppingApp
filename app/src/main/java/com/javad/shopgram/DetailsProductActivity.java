package com.javad.shopgram;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.shopgram.model.ModelProduct;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.General;
import com.javad.shopgram.util.PrefManager;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsProductActivity extends AppCompatActivity implements View.OnClickListener {

    CarouselView carouselView;
    List<String> images = new ArrayList<>();
    //    List<Integer> imagesList = new ArrayList<>();
    AppCompatImageView fav;
    TextView title, tozihat, addToBasket, priceORG, priceSHOP;
    boolean favorit = false;
    Bundle bundle;
    ModelProduct modelProduct;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        setAll();
        prefManager = new PrefManager(this);
//        Toast.makeText(this, "created", Toast.LENGTH_SHORT).show();
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        fav = (AppCompatImageView) findViewById(R.id.fav);
        title = findViewById(R.id.title);
        tozihat = findViewById(R.id.tozihat);
        addToBasket = findViewById(R.id.addToBasket);
        priceORG = findViewById(R.id.priceORG);
        priceSHOP = findViewById(R.id.priceOrder);

        title.setText(modelProduct.getTitle());
        tozihat.setText(modelProduct.getDesciption());
        priceORG.setText("قیمت اصلی :" + modelProduct.getShop_price() + "");
        priceSHOP.setText("قیمت با تخفیف :" + modelProduct.getVirtin_price() + "");
        if (modelProduct.getShop_price() > modelProduct.getVirtin_price()) {
            priceORG.setPaintFlags(priceORG.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        setImages();
        addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Req(DetailsProductActivity.this, Req.root + "/cart/add", new onRequest() {
                    @Override
                    public void isSucess(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("result");
                            if (result.equals("126")) {
                                Toast.makeText(DetailsProductActivity.this, General.namojod, Toast.LENGTH_SHORT).show();
                            }
                            if (result.equals("1")) {
                                Toast.makeText(DetailsProductActivity.this, General.addedToCart, Toast.LENGTH_SHORT).show();
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
                        Map<String, String> stringStringMap = new HashMap<>();
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("token", prefManager.getTokenUser());
                            jsonObject.put("product_id", modelProduct.getId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        stringStringMap.put("data", jsonObject.toString());
                        return stringStringMap;
                    }
                });
            }
        });

        fav.setOnClickListener(this);
    }

    public void setAll() {
        bundle = getIntent().getExtras();
        modelProduct = new ModelProduct();
        modelProduct.setId((bundle.getInt(General.keyIdForIntentDetail)));
        modelProduct.setTitle(bundle.getString(General.keyTitle));
        modelProduct.setDesciption(bundle.getString(General.keyDesc));
        modelProduct.setPath_photos(bundle.getString(General.keyPhotos));
        modelProduct.setShop_price((bundle.getInt(General.keyPriceOrg)));
        modelProduct.setVirtin_price((bundle.getInt(General.keyPriceSHOP)));
        modelProduct.setSeller_id((bundle.getInt(General.keyIdSeller)));

//       Log.e("PATH",modelProduct.getPath_photos()+"  ...  ");
//        ArrayList<ModelProduct> arrayList= (ArrayList<ModelProduct>) getIntent().getParcelableArrayListExtra("Model");
    }

    private void setImages() {
//        final int count;
//        final boolean isNull;
//        if (modelProduct.getPath_photos().equals("")) {
//            count=1;
//            isNull=true;
//        } else{
//            String[] urls = modelProduct.getPath_photos().split("&&");
//            count=urls.length;
//            isNull=false;
//        }
        final String[] path = modelProduct.getPath_photos().split("&&");
        if (!path[0].equals(""))
            carouselView.setPageCount(path.length);
        else
            carouselView.setPageCount(1);
        carouselView.pauseCarousel();
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {

                if (path[0].equals(""))
                    imageView.setBackgroundResource(General.image_noPicure);
                else {
                    String[] urls = modelProduct.getPath_photos().split("&&");
                    Log.e("urlIS", urls[position]);
                    Picasso.get().load(Req.rootAsli + urls[position]).error(General.fail_Image).placeholder(General.image_placeHolder).into(imageView);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fav:

                if (!favorit) {

                    fav.setImageResource(R.drawable.ic_favorite);
                    favorit = true;

                } else {

                    fav.setImageResource(R.drawable.ic_favorite_border);
                    favorit = false;

                }

                break;

        }
    }
}
