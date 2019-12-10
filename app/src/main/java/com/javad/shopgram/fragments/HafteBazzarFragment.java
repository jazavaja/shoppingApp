package com.javad.shopgram.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.adapter.AdapterHafteBazzar;
import com.javad.shopgram.model.haftebazzar.HafteBazar;
import com.javad.shopgram.model.haftebazzar.ModelHafte;
import com.javad.shopgram.service.onRequest;

import java.util.List;
import java.util.Map;

public class HafteBazzarFragment extends Fragment {
    GridView gridView;
    View footer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//      result  203     yani bayad 10 nafar Ozv koni

        View view=inflater.inflate(R.layout.product_layout,container,false);
        gridView=view.findViewById(R.id.gridview);
        footer=view.findViewById(R.id.footer);
        getHafteBazzar();


        return view;
    }
    public void getHafteBazzar(){
        new Req(getContext(), Req.root + "/product/hafte_bazar", new onRequest() {
            @Override
            public void isSucess(String response) {
                Gson gson=new Gson();
                ModelHafte modelHafte=gson.fromJson(response,ModelHafte.class);
                if (modelHafte.getResult()==203){
                    Toast.makeText(getContext(), "شما باید 10 نفر را عضو کنید تا در هفته بازار باشید", Toast.LENGTH_SHORT).show();
                }
                if (modelHafte.getResult()==1){
                    List<HafteBazar> list = modelHafte.getData().getHafteBazar();
                    AdapterHafteBazzar hafteBazzar=new AdapterHafteBazzar(getContext(),list);
                    gridView.setAdapter(hafteBazzar);
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
                return null;
            }
        });
    }
}
