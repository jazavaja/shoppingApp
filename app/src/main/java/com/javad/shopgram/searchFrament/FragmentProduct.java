package com.javad.shopgram.searchFrament;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.javad.shopgram.Req;
import com.javad.shopgram.adapter.AdapterSearch;
import com.javad.shopgram.adapter.AdapterSearchCategory;
import com.javad.shopgram.model.search.ModelSearch;
import com.javad.shopgram.model.search.Product;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.General;
import com.trafi.anchorbottomsheetbehavior.AnchorBottomSheetBehavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.javad.shopgram.MainActivity;
import com.javad.shopgram.R;
import com.javad.shopgram.adapter.ProductAdapter;
import com.javad.shopgram.model.ModelProduct;
import com.javad.shopgram.service.OnLoadMoreListener;
import com.javad.shopgram.util.MyScrollListener;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentProduct extends Fragment implements View.OnClickListener{

    CardView cv_filter;
    GridView recyclerView;
    List<ModelProduct> list;
    ProductAdapter productAdapter;
    private String searchKey="";
    private String minPrice="";
    private String maxPrice="";
    private String category_id="";


//
//    private void refrenceRadioButton(View view){
//
//        labaniat = view.findViewById(R.id.labaniat);
//        superMarket = view.findViewById(R.id.superMarket);
//
//        labaniat.setOnClickListener(this);
//
//        boolean checked = (labaniat).isChecked();
//
//        switch (view.getId()){
//
//            case R.id.labaniat:
//                if (checked){
//
//                    labaniat.setChecked(false);
//
//                }else {
//
//                    labaniat.setChecked(true);
//                }
//
//                break;
//
//
//        }
//        if (superMarket.isChecked()){
//            superMarket.setChecked(false);
//        }
//
//
//        if (labaniat.isChecked()){
//            labaniat.setChecked(false);
//
//        }
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pro,null);
        init(view);

//        recyclerView.setOnScrollListener(new MyScrollListener(getActivity()) {
//            @Override
//            public void onMoved(int distance) {
//
//                cv_filter.setTranslationY(distance);
//
//            }
//        });

        setAdapter();
        bottomSheetBehavior();

        return view;
    }

    private void init(View view){

        list = new ArrayList<>();

        cv_filter = view.findViewById(R.id.cv_filter);
        recyclerView = (GridView) view.findViewById(R.id.list);

        cv_filter.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.cv_filter:
//                MainActivity.behavior.setState(BottomSheetBehaviorGoogleMapsLike.STATE_ANCHOR_POINT);
                MainActivity.bg.animate().alpha(1).setDuration(100).start();
                MainActivity.sheetBehavior.setState(AnchorBottomSheetBehavior.STATE_ANCHORED);
                break;
        }

    }

    private void setAdapter(){

        new Req(getContext(), Req.root + "/search/product", new onRequest() {
            @Override
            public void isSucess(String response) {
                Gson gson=new Gson();
                ModelSearch modelProduct=gson.fromJson(response,ModelSearch.class);
                if (modelProduct.getResult()==1){
                    List<Product> products=modelProduct.getData().getProducts();
                    if (products.size()==0)
                    {
                        Toast.makeText(getContext(), General.empty, Toast.LENGTH_SHORT).show();
                    }else {
                        AdapterSearch category=new AdapterSearch(getContext(),products);
                        recyclerView.setAdapter(category);
                    }
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
                Map<String,String> stringMap=new HashMap<>();
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("category_id",category_id);
                    jsonObject.put("search_key",searchKey);
                    jsonObject.put("min_price",minPrice);
                    jsonObject.put("man_price",maxPrice);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                stringMap.put("data",jsonObject.toString());
                return stringMap;
            }
        });
//
//        for (int i = 0; i < 30; i++) {
//
//            list.add(new ModelProduct());
//
//        }
//
//
//        AdapterSearchCategory adapterSearchCategory
//        productAdapter = new ProductAdapter(recyclerView,getActivity(), list);
//        recyclerView.setAdapter(productAdapter);
//
//
//        productAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//
//                if (list.size() <= 10){
//
//                    list.add(null);
//                    productAdapter.notifyItemInserted(list.size() - 1);
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            productAdapter.notifyItemRemoved(list.size());
//                            int index = list.size();
//                            int end = index + 5 ;
//                            for (int i = 0; i < end; i++) {
//                                list.add(new ModelProduct());
//                            }
//
//                            productAdapter.notifyDataSetChanged();
//                            productAdapter.setLoaded();
//
//                        }
//                    }, 5000);
//                }else {
//
//                    Toast.makeText(getActivity(), "loading data completed", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
    }

    private void bottomSheetBehavior(){

        MainActivity.sheetBehavior.addBottomSheetCallback(new AnchorBottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {

                if (MainActivity.sheetBehavior.getState() == AnchorBottomSheetBehavior.STATE_COLLAPSED){


                    MainActivity.bg.animate().alpha(0).setDuration(100).start();

                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

    }
}
