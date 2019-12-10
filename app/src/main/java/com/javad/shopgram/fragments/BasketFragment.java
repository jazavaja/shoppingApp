package com.javad.shopgram.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.javad.shopgram.FinainzePayment;
import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.adapter.AdapterCart;
import com.javad.shopgram.model.cart.Cart;
import com.javad.shopgram.model.cart.ModelCart;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.General;
import com.javad.shopgram.util.PrefManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketFragment extends Fragment {
    RecyclerView recyclerView;
    View empty;
    View fail;
    PrefManager map;
    Button nextPayment;
//    ImageView kam,ziad;
//    TextView tedad;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.basket,container,false);
        map=new PrefManager(getContext());
        recyclerView=view.findViewById(R.id.recycleBasket);
        empty=view.findViewById(R.id.empty);
//        kam=view.findViewById(R.id.kam);
//        ziad=view.findViewById(R.id.ziad);
//        tedad=view.findViewById(R.id.tedad);
        fail=view.findViewById(R.id.fail);
        nextPayment=view.findViewById(R.id.nextPayment);
        nextPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intext=new Intent(getActivity(), FinainzePayment.class);
                startActivity(intext);
            }
        });
        getBasket();
//        setKam();
//        setZiad();
        return view;
    }


//    private void setZiad() {
//        ziad.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                new Req(getContext(), Req.root + "/cart/remove", new onRequest() {
//                    @Override
//                    public void isSucess(String response) {
//
//                    }
//
//                    @Override
//                    public void isFailed(String error) {
//
//                    }
//
//                    @Override
//                    public void OnProgress() {
//
//                    }
//
//                    @Override
//                    public Map<String, String> Paramets() {
//                        Map<String,String> mp=new HashMap<>();
//                        mp.put("cart_id","");
//                        return null;
//                    }
//                });
//            }
//        });
//    }

    public void getBasket(){
        new Req(getContext(), Req.root + "/cart/list", new onRequest() {
            @Override
            public void isSucess(String response) {
                Gson gson=new Gson();
                ModelCart modelCart=gson.fromJson(response,ModelCart.class);
                if (modelCart.getResult()==1){
                    List<Cart> carts=modelCart.getData().getCarts();
                    if (carts.size()==0){
                        recyclerView.setVisibility(View.GONE);
                        fail.setVisibility(View.GONE);
                        empty.setVisibility(View.VISIBLE);
                    }
                    else {
                        recyclerView.setVisibility(View.VISIBLE);
                        fail.setVisibility(View.GONE);
                        empty.setVisibility(View.GONE);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        AdapterCart adapterCart=new AdapterCart(getContext(),carts);
                        recyclerView.setAdapter(adapterCart);
                    }
                }
            }

            @Override
            public void isFailed(String error) {
                fail.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);
            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                return General.map(map);
            }
        });
    }



//    public void setKam(){
//        kam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }
}
