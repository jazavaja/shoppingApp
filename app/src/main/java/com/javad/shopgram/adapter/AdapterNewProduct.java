package com.javad.shopgram.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.javad.shopgram.DetailsProductActivity;
import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.model.newProduct.NewProduct;
import com.javad.shopgram.util.General;
import java.util.List;
import me.grantland.widget.AutofitTextView;


public class AdapterNewProduct extends RecyclerView.Adapter<AdapterNewProduct.ViewHolder> {

    private Context context;
    private List<NewProduct> list;
    private LayoutInflater inflater;
    private int lastPosition = -1;

    public AdapterNewProduct(Context context, List<NewProduct> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.gallery_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            animation(holder, position);
        final NewProduct newProduct=list.get(position);
        String[] pathPhotos=newProduct.getPathPhotos().split("&&");
//        int p=pathPhotos.length;
        if (!pathPhotos[0].equals("")) {
            General.picasso(Req.rootAsli+pathPhotos[0],holder.imageView);
        }else {
            holder.imageView.setBackgroundResource(General.image_noPicure);
        }
        holder.nameProduct.setText(newProduct.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsProductActivity.class);
                Log.e("TESTID",newProduct.getId());
                intent.putExtra(General.keyIdForIntentDetail,Integer.parseInt(newProduct.getId()));
                intent.putExtra(General.keyTitle,newProduct.getTitle());
                intent.putExtra(General.keyDesc,newProduct.getDesciption());
                intent.putExtra(General.keyPhotos,newProduct.getPathPhotos());
                intent.putExtra(General.keyIdSeller,Integer.parseInt(newProduct.getSellerId()));
                intent.putExtra(General.keyPriceOrg,newProduct.getShopPrice());
                intent.putExtra(General.keyPriceSHOP,newProduct.getVitrinPrice());

                context.startActivity(intent);
            }
        });
        holder.addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
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
        ImageView imageView;
        AutofitTextView nameProduct;
        ImageView addToBasket;
//        TextView matn;
//        RoundedImageView pic;

        ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.javadImage);
            nameProduct=itemView.findViewById(R.id.title);
            addToBasket=itemView.findViewById(R.id.add);
//            matn=itemView.findViewById(R.id.matn);
//            pic=itemView.findViewById(R.id.pic);
        }
    }
}

