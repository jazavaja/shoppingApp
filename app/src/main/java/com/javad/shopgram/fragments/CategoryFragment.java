package com.javad.shopgram.fragments;

//import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.google.gson.Gson;
import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.adapter.AdapterCategory;
import com.javad.shopgram.model.category.Category;
import com.javad.shopgram.model.category.ModelCategory;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.General;
import com.javad.shopgram.util.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryFragment extends Fragment {
//    Context context;
    RecyclerView recyclerView;
    PullRefreshLayout refreshLayout;
    PrefManager prefManager;
    View fail;
    FrameLayout linearLayout;

    public CategoryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category, container,false);
        recyclerView = view.findViewById(R.id.recyle_category);
        refreshLayout = view.findViewById(R.id.refresh);
        fail=view.findViewById(R.id.hhhhh);
        linearLayout=view.findViewById(R.id.view);
        prefManager = new PrefManager(getContext());
        getCategory();
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCategory();
            }
        });
        return view;

    }

    public void getCategory() {
        new Req(getContext(), Req.root + "/categories", new onRequest() {
            @Override
            public void isSucess(String response) {
                fail.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                refreshLayout.setRefreshing(false);
                Gson gson = new Gson();
                ModelCategory modelCategory = gson.fromJson(response, ModelCategory.class);
                if (modelCategory.getStatus() == 200 && modelCategory.getResult() == 1) {
                    List<Category> categories = modelCategory.getData().getCategories();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    AdapterCategory category = new AdapterCategory(getContext(), categories);
                    recyclerView.setAdapter(category);
                    try {
                        recyclerView.smoothScrollToPosition(0);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void isFailed(String error) {
                fail.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                General.failSnackBar(refreshLayout);
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String js = jsonObject.toString();
                map.put("data", js);
                return map;
            }
        });
    }

}
