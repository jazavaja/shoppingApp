package com.javad.shopgram.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.javad.shopgram.Login;
import com.javad.shopgram.R;
import com.javad.shopgram.Req;
import com.javad.shopgram.adapter.AdapterAll;
import com.javad.shopgram.model.ModelProfile;
import com.javad.shopgram.model.all.AllProduct;
import com.javad.shopgram.model.all.ModelAllProduct;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.General;
import com.javad.shopgram.util.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;

public class ProfileFragment extends Fragment {
    CardView cardView;
    TextView count_follower_me;
    TextView count_favorite;
    AutofitTextView username_edt;
    EditText family;
    EditText address;
    TextView mobile;
    EditText postalCode;
    AutofitTextView exit;
    AutofitTextView save;
    GridView favProduct;
    PrefManager prefManager;
    CircleImageView profile_pic;
    EditText name;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        count_follower_me = view.findViewById(R.id.count_follower_me);
        cardView=view.findViewById(R.id.card);
        count_favorite = view.findViewById(R.id.count_favorite);
        username_edt = view.findViewById(R.id.username_edt);
        family = view.findViewById(R.id.family);
        name=view.findViewById(R.id.name);
        address = view.findViewById(R.id.address);
        exit = view.findViewById(R.id.btn_exit);
        save = view.findViewById(R.id.btn_saveProfile);
        mobile=view.findViewById(R.id.mobile);
        postalCode=view.findViewById(R.id.postalCode);
        favProduct = view.findViewById(R.id.gridviewFavorite);
        profile_pic = view.findViewById(R.id.profile_pic);
        prefManager = new PrefManager(getContext());
        address.setText(prefManager.getAddress());
        getProfile();
        setExit();
        setSave();
        getFavoite();
        return view;
    }
    public void getFavoite(){
        new Req(getContext(), Req.root + "/favorite", new onRequest() {
            @Override
            public void isSucess(String response) {
                Log.e("javad",response);

                Gson gson=new Gson();
                ModelAllProduct modelFavorite=gson.fromJson(response,ModelAllProduct.class);
                List<AllProduct> allProducts= modelFavorite.getData().getAllProducts();
                Log.e("SWE", String.valueOf(allProducts.size()));
                AdapterAll adapterAll=new AdapterAll(getContext(),allProducts);
                favProduct.setAdapter(adapterAll);
            }

            @Override
            public void isFailed(String error) {

            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                return General.map(prefManager);
            }
        });
    }
    public void setSave(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Req(getContext(),Req.root+"/user/edit", new onRequest() {
                    @Override
                    public void isSucess(String response) {
                        save.setText("ذخیره");
                        prefManager.setNameprofile(name.getText().toString());
                        prefManager.setFamily(family.getText().toString());
                        prefManager.setAddress(address.getText().toString());

                    }

                    @Override
                    public void isFailed(String error) {
                        save.setText("ذخیره");
                        General.failSnackBar(cardView);
                    }

                    @Override
                    public void OnProgress() {
                        save.setText("لطفا صبر کنید");
                    }

                    @Override
                    public Map<String, String> Paramets() {
                        Map<String, String> map = new HashMap<>();
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("token", prefManager.getTokenUser());
                            jsonObject.put("lastname", family.getText().toString());
                            jsonObject.put("firstname", name.getText().toString());
                            jsonObject.put("addres",address.getText().toString());
                            jsonObject.put("postal_code",postalCode.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String js = jsonObject.toString();
                        map.put("data", js);
                        return map;
                    }
//                    @Override
//                    public Map<String, String> Paramets() {
//
//                        JSONObject jsonObject=new JSONObject();
//                        try {
//                            jsonObject.put("token",prefManager.getTokenUser());
//                            jsonObject.put("lastname", family.getText().toString());
//                            jsonObject.put("firstname", name.getText().toString());
//                            jsonObject.put("phone_number",mobile.getText().toString());
//                            jsonObject.put("addres",address.getText().toString());
//                            jsonObject.put("postal_code",postalCode.getText().toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        Map<String,String> stringMap=new HashMap<>();
//                        stringMap.put("data",jsonObject.toString());
//                        return stringMap;
//                    }
                });

            }
        });
    }
    public void setExit(){
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Login.class);
                startActivity(intent);
                prefManager.setTokenUser("");
                prefManager.setDisableLogin();
            }
        });
    }
    public void getProfile() {
        new Req(getContext(), Req.root + "/user/profile", new onRequest() {
            @SuppressLint("SetTextI18n")
            @Override
            public void isSucess(String response) {
                Gson gson = new Gson();

                ModelProfile modelProfile = gson.fromJson(response, ModelProfile.class);
                count_follower_me.setText(General.strNoNull(modelProfile.getFollowingCount()));
                count_favorite.setText(General.strNoNull(modelProfile.getFavaritsCount()));
                username_edt.setText(General.strNoNull(modelProfile.getUsername()));
                address.setText(General.strNoNull(modelProfile.getAddres()));
                postalCode.setText(General.strNoNull(modelProfile.getPostalCode()));
                family.setText(General.strNoNull( modelProfile.getLastname()));
                mobile.setText(General.strNoNull(modelProfile.getPhoneNumber()));
                General.picasso(Req.rootAsli + modelProfile.getPicUrl(), profile_pic);
                name.setText(General.strNoNull(modelProfile.getFirstname()));

                prefManager.setNameprofile(name.getText().toString());
                prefManager.setFamily(family.getText().toString());
            }

            @Override
            public void isFailed(String error) {
                count_follower_me.setText("");
                count_favorite.setText("");
                username_edt.setText("");
                family.setText("");
                mobile.setText("");
                address.setText("");
                postalCode.setText("");
            }

            @Override
            public void OnProgress() {
                count_follower_me.setText(General.progress);
                count_favorite.setText(General.progress);
                username_edt.setText(General.progress);
                family.setText(General.progress);
                mobile.setText(General.progress);
//                General.picasso(Req.rootAsli+modelProfile.getPicUrl(),profile_pic);
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
