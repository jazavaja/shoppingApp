package com.javad.shopgram.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import com.javad.shopgram.DetailsProductActivity;
import com.javad.shopgram.R;
import com.javad.shopgram.model.ModelProduct;
import com.javad.shopgram.service.OnLoadMoreListener;

/**
 * Created by AMIR on 8/22/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ModelProduct> list;
    LayoutInflater inflater;
    private int lastPosition = -1;
    private OnLoadMoreListener onLoadMoreListener;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean isLoading;
    private Activity activity;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public ProductAdapter(RecyclerView recyclerView, Activity activity, List<ModelProduct> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(activity);
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (onLoadMoreListener != null){
                    onLoadMoreListener.onLoadMore();
                }
                isLoading = true;
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM){

            View view = inflater.inflate(R.layout.item_pro_list, parent, false);
            return new UserViewHolder(view);

        }else if (viewType == VIEW_TYPE_LOADING){

            View view = inflater.inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof UserViewHolder){

            ((UserViewHolder) holder).root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    activity.startActivity(new Intent(activity, DetailsProductActivity.class));
                    Toast.makeText(activity, "click", Toast.LENGTH_SHORT).show();

                }
            });

            // set item
            animation(holder, position);

        }else if (holder instanceof LoadingViewHolder){

            Log.e("loading", "loading");
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);

        }

    }

    private void animation(RecyclerView.ViewHolder holder, int position){

        // animation
        Animation animation = AnimationUtils.loadAnimation(activity,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.bottom_from_up);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setLoaded(){
        isLoading = false;
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder{

        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progress);
        }
    }

    private class UserViewHolder extends RecyclerView.ViewHolder{

        LinearLayout root;

        public UserViewHolder(View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.root);

        }
    }
}

