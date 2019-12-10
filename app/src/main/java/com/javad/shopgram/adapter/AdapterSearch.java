package com.javad.shopgram.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.javad.shopgram.DetailsProductActivity;
import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.model.search.Product;
import com.javad.shopgram.util.General;

import java.util.List;

import me.grantland.widget.AutofitTextView;

/**
 * Created by -US- on 10/24/2017.
 */

public class AdapterSearch extends BaseAdapter {

    private Context context;

    private List<Product> alphabetList;
    int last = -1;

    public AdapterSearch(Context context, List<Product> list) {
        this.context = context;
        this.alphabetList = list;

    }



    @Override
    public int getCount() {
        return alphabetList.size();
    }

    @Override
    public Object getItem(int position) {
        return alphabetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        ImageView imageView = view.findViewById(R.id.javadImage);
        AutofitTextView textView=view.findViewById(R.id.title);
        textView.setText(alphabetList.get(position).getTitle());
        String urls = alphabetList.get(position).getPathPhotos();
        String[] urlValid = urls.split("&&");
        General.picasso(Req.rootAsli+urlValid[0],imageView);
//        Picasso.get()
//                .load(Req.rootAsli+urlValid[0])
//                .error(General.fail_Image)
//                .placeholder(General.image_placeHolder)
//                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsProductActivity.class);
//                ArrayList<ModelProduct> arrayList= (ArrayList<ModelProduct>) alphabetList;
                intent.putExtra("title",alphabetList.get(position).getTitle());

                intent.putExtra("priceORG",alphabetList.get(position).getShopPrice());
                intent.putExtra("priceSHOP",alphabetList.get(position).getVitrinPrice());
                intent.putExtra("photos",alphabetList.get(position).getPathPhotos());
                intent.putExtra("desc",alphabetList.get(position).getDesciption());
                intent.putExtra("id_seller",Integer.parseInt(alphabetList.get(position).getSellerId()));
                intent.putExtra("id",Integer.parseInt(alphabetList.get(position).getId()));
                intent.putExtra("week_shop",alphabetList.get(position).getWeekMarketPrice());

                Log.e("testMM",alphabetList.get(position).getPathPhotos());
//                intent.putExtra("Model",alphabetList.get(0));
                context.startActivity(intent);
            }
        });
        return view;
    }

}