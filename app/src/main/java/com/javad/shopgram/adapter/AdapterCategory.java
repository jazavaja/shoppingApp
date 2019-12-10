package com.javad.shopgram.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.javad.shopgram.DetailsProductActivity;
import com.javad.shopgram.ProductCategory;
import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.model.category.Category;
import com.javad.shopgram.model.vitrin.Vitrin;
import com.javad.shopgram.util.General;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;


public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    Context context;
    List<Category> list;
//    List<Object> objects;
    LayoutInflater inflater;
    private int lastPosition = -1;

    public AdapterCategory(Context context, List<Category> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_cat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            animation(holder, position);
        final Category category=list.get(position);
        holder.textView.setText(category.getTitle());
        General.picasso(Req.rootAsli+category.getPicUrl(),holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductCategory.class);
                intent.putExtra("id" ,category.getId());
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
        CircleImageView imageView;
        TextView textView;
//        ImageView imageView;
//        AutofitTextView nameProduct;
//        ImageView addToBasket;
//        TextView matn;
//        RoundedImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            textView=itemView.findViewById(R.id.title);
//            addToBasket=itemView.findViewById(R.id.add);
//            matn=itemView.findViewById(R.id.matn);
//            pic=itemView.findViewById(R.id.pic);
        }
    }
}

