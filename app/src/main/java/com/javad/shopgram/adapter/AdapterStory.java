package com.javad.shopgram.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.SellerPageUser;
import com.javad.shopgram.model.ModelGetSellerStory;
import com.javad.shopgram.util.General;
import com.makeramen.roundedimageview.RoundedImageView;


public class AdapterStory extends RecyclerView.Adapter<AdapterStory.ViewHolder> {

    Context context;
    List<ModelGetSellerStory> list;
    LayoutInflater inflater;
    private int lastPosition = -1;

    public AdapterStory(Context context, List<ModelGetSellerStory> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.story_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            animation(holder, position);
        final ModelGetSellerStory story=list.get(position);
//        Log.e("testAdapter",story.getMatn()+" $ "+story.getPic_url());

        General.picasso(Req.rootAsli+story.getPic_url(),holder.pic);
//        Picasso.get().load(Req.rootAsli+story.getPic_url()).into(holder.pic);
        holder.matn.setText(story.getMatn());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(context, SellerPageUser.class);

                intent.putExtra("id",story.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void animation(ViewHolder holder, int position){

        // animation
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.left_to_right
                        : R.anim.right_to_left);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView matn;
        RoundedImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);
            matn=itemView.findViewById(R.id.matn);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}

