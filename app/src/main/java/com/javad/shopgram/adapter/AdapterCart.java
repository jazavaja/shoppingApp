package com.javad.shopgram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.model.cart.Cart;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.General;
import com.javad.shopgram.util.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {

    Context context;
    List<Cart> list;
    //    List<Object> objects;
    LayoutInflater inflater;
    PrefManager prefManager;
    private int lastPosition = -1;
    int tedad;
    String title;
     String cart_id;

    public AdapterCart(Context context, List<Cart> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_basket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        animation(holder, position);
        prefManager = new PrefManager(context);

        List<Cart> cartList=list;

        final Cart cart = cartList.get(position);
        tedad =Integer.parseInt(cart.getCount());
        title=cart.getTitle();
        cart_id=cart.getCartId();
        General.picasso(Req.rootAsli + cart.getPathPhotos().split("&&")[0], holder.imageView);
        holder.tedad.setText(tedad+"");
        holder.text.setText(title);
        holder.kam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Req(context, Req.root + "/cart/edit", new onRequest() {
                    @Override
                    public void isSucess(String response) {
                        Log.e("KamKardan",response);
                        tedad--;
//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("result");
                            if (result.equals("126")){
                                Toast.makeText(context,General.namojod,Toast.LENGTH_SHORT).show();
                            }
                            if (result.equals("1")){
                                holder.tedad.setText(tedad+"");
                                Toast.makeText(context, General.kamShod, Toast.LENGTH_SHORT).show();
                            }
//                            Toast.makeText(context, jsonObject.getString(""), Toast.LENGTH_SHORT).show();

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
                        JSONObject p = new JSONObject();
                        int s = tedad;
                        try {
                            p.put("token", prefManager.getTokenUser());
                            p.put("cart_id", cart_id);
                            p.put("count", (s - 1));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        map.put("data", p.toString());
                        return map;
                    }
                });
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Req(context, Req.root + "/cart/edit", new onRequest() {
                    @Override
                    public void isSucess(String response) {
                        Log.e("addKardan",response);
                        tedad++;
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if (jsonObject.getString("result").equals("1")){
                                Toast.makeText(context, General.afzodeDhod, Toast.LENGTH_SHORT).show();
                                holder.tedad.setText(tedad+"");
                            }else {
                                Toast.makeText(context, General.namojod, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
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
                        JSONObject p = new JSONObject();
                        int s = tedad;
                        try {
                            p.put("token", prefManager.getTokenUser());
                            p.put("cart_id", cart_id);
                            p.put("count", (s + 1));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        map.put("data", p.toString());
                        return map;
                    }
                });
            }
        });


//        holder.textView.setText(category.getTitle());
//        General.picasso(Req.rootAsli+category.getPicUrl(),holder.imageView);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void animation(ViewHolder holder, int position) {

        // animation
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.left_to_right
                        : R.anim.right_to_left);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView text;
        ImageView add;
        ImageView kam;
        TextView tedad;

//        ImageView imageView;
//        AutofitTextView nameProduct;
//        ImageView addToBasket;
//        TextView matn;
//        RoundedImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            text = itemView.findViewById(R.id.title);
            add = itemView.findViewById(R.id.ziad);
            kam = itemView.findViewById(R.id.kam);
            tedad = itemView.findViewById(R.id.tedad);
//            addToBasket=itemView.findViewById(R.id.add);
//            matn=itemView.findViewById(R.id.matn);
//            pic=itemView.findViewById(R.id.pic);
        }
    }
}

