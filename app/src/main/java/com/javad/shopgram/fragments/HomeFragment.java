package com.javad.shopgram.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baoyz.widget.PullRefreshLayout;
import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.adapter.AdapterImages;
import com.javad.shopgram.adapter.AdapterStory;
import com.javad.shopgram.model.ModelGetSellerStory;
import com.javad.shopgram.model.ModelGetStory;
import com.javad.shopgram.model.ModelProduct;
import com.javad.shopgram.model.ModelProductFolow;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.PrefManager;
import com.race604.drawable.wave.WaveDrawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment implements View.OnClickListener {
    AdapterStory adapterStory;
    AdapterImages adapterImages;
    RecyclerView story_recycle;
    GridView gridViewGallery;
    PrefManager prefManager;
    View failOne;
    View failTwo;
    View progress;
    PullRefreshLayout refreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        story_recycle = view.findViewById(R.id.story_recycle);
        gridViewGallery = view.findViewById(R.id.gridview);
        WaveDrawable drawable=new WaveDrawable(getActivity(),R.drawable.logo);
        failOne=view.findViewById(R.id.fail1);
        failTwo=view.findViewById(R.id.fail2);
        progress=view.findViewById(R.id.progress);
        refreshLayout=view.findViewById(R.id.refresh);
        prefManager=new PrefManager(getContext());
        getShopList();
        getImagesList();
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getShopList();
                getImagesList();
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }

    }

    public void getShopList() {
        new Req(getContext(), Req.root + "/shop/advertsing",
                new onRequest() {
                    @Override
                    public void isSucess(String response)
                    {
                        try
                        {
                            refreshLayout.setRefreshing(false);
                            Req.sucessSetView(progress,failOne,story_recycle);
                            ModelGetStory modelStory= jsonToModelStory(response);
                            if (modelStory.getStatus()==200&&modelStory.getResult()==1)
                            {
                                List<ModelGetSellerStory> sellerStory=modelStory.getModelGetSellerStories();
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
                                story_recycle.setLayoutManager(linearLayoutManager);
                                try {
                                    adapterStory = new AdapterStory(getActivity(), sellerStory);
                                    story_recycle.setAdapter(adapterStory);
                                }catch (Exception e){
                                    Log.e("j",e.toString());
                                }
                            }
                        }
                        catch (JSONException e)
                        {
                            Req.failedSetView(progress,failOne,story_recycle);
//                            story_recycle.setVisibility(View.GONE);
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void isFailed(String s) {
                        refreshLayout.setRefreshing(false);

                        Req.failedSetView(progress,failOne,story_recycle);
//                        refreshLayout.setRefreshing(false);
//                        story_recycle.setVisibility(View.GONE);
//                        failed1.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void OnProgress() {
//                        refreshLayout.setRefreshing(true);
                    }

                    @Override
                    public Map<String, String> Paramets() {
                        Map<String,String> map=new HashMap<>();
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("token",prefManager.getTokenUser() );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String js=jsonObject.toString();
                        map.put("data",js);
                        return map;
                    }
                });
    }

    public ModelGetStory jsonToModelStory(String response) throws JSONException {
        ModelGetStory story=new ModelGetStory();
        JSONObject jsonObject=new JSONObject(response);
        story.setStatus(Integer.parseInt(jsonObject.getString("status")));
        story.setResult(Integer.parseInt(jsonObject.getString("result")));
        String data=jsonObject.getString("data");
        JSONObject object=new JSONObject(data);
        String adv=object.getString("advertsingSellers");
        JSONArray array=new JSONArray(adv);
        List<ModelGetSellerStory> modelGetSellerStories =new ArrayList<>();
        for (int i=0;i<array.length();i++){
            ModelGetSellerStory modelGetSellerStory =new ModelGetSellerStory();
            JSONObject w=array.getJSONObject(i);
            modelGetSellerStory.setId(Integer.parseInt(w.getString("id")));
            modelGetSellerStory.setPic_url(w.getString("pic_url"));
            modelGetSellerStory.setMatn(w.getString("name_shop"));
            modelGetSellerStories.add(modelGetSellerStory);
        }
        story.setModelGetSellerStories(modelGetSellerStories);

        return story;
    }

    public static ModelProductFolow jsonToModelProductFollow(String response) throws JSONException {
        ModelProductFolow modelProductFolow=new ModelProductFolow();
        JSONObject jsonObject=new JSONObject(response);
        modelProductFolow.setStatus(Integer.parseInt(jsonObject.getString("status")));
        modelProductFolow.setResult(Integer.parseInt(jsonObject.getString("result")));
        List<ModelProduct> modelProducts=new ArrayList<>();
        String data=jsonObject.getString("data");
        JSONObject object=new JSONObject(data);
        String product=object.getString("products");
//        Log.e("sarlak",product);
        JSONArray array=new JSONArray(product);

        for (int i=0;i<array.length();i++){
            ModelProduct modelProduct=new ModelProduct();
            JSONObject w=array.getJSONObject(i);
            modelProduct.setId(Integer.parseInt(w.getString("id")));
            modelProduct.setTitle(w.getString("title"));
            modelProduct.setDesciption(w.getString("desciption"));
            modelProduct.setSeller_id(Integer.parseInt(w.getString("seller_id")));
            modelProduct.setShop_price(w.getInt("shop_price"));
            modelProduct.setVirtin_price(w.getInt("vitrin_price"));
            modelProduct.setPath_photos(w.getString("path_photos"));
            modelProduct.setWeek_market_price(w.getInt("week_market_price"));
            modelProducts.add(modelProduct);
        }
        modelProductFolow.setModelProducts(modelProducts);

        return modelProductFolow;
    }

    public void getImagesList() {
        new Req(getContext(), Req.root + "/product/following", new onRequest() {
            @Override
            public void isSucess(String response) {
                try {
                    Log.e("klk",response);
                    refreshLayout.setRefreshing(false);

                    Req.sucessSetView(progress,failTwo,gridViewGallery);

                    ModelProductFolow w= jsonToModelProductFollow(response);
                    List<ModelProduct> product=w.getModelProducts();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
                    adapterImages=new AdapterImages(getActivity(),product);
//                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    gridViewGallery.setAdapter(adapterImages);


                } catch (JSONException e) {
                    Req.failedSetView(progress,failTwo,gridViewGallery);
//                    Log.e("klk","JSONNOTwork");
                    e.printStackTrace();
                }
            }

            @Override
            public void isFailed(String err) {
                Req.failedSetView(progress,failTwo,gridViewGallery);
                refreshLayout.setRefreshing(false);

//                Log.e("klk","workFAIL");
                gridViewGallery.setVisibility(View.GONE);
//                failed2.setVisibility(View.VISIBLE);
            }

            @Override
            public void OnProgress() {
//                refreshLayout.setRefreshing(true);
            }

            @Override
            public Map<String, String> Paramets() {
                Map<String,String> map=new HashMap<>();
                JSONObject object=new JSONObject();
                try {
                    object.put("token",prefManager.getTokenUser());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                map.put("data",object.toString());
                return map;
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_transition);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .3f, .3f);
        gridViewGallery.setLayoutAnimation(controller);

//        adapterImages = new AdapterImages(getActivity(), galleryList);
    }


}
