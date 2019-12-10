package com.javad.shopgram;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.javad.shopgram.model.finilize.ModelFinilize;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FinainzePayment extends FragmentActivity {
    TextView name;
    EditText address;
    EditText postalCode;
    TextView mobile;
    TextView price;
    RadioGroup radioGroup;
    PrefManager prefManager;
    Button btn_payment;
    Button btnCalculatePrice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_cart);
        find();
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FinainzePayment.this,Payment.class);
                startActivity(intent);

            }
        });
        btnCalculatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFinilize();
            }
        });
    }

    private void getFinilize(){
        new Req(this, Req.root + "/cart/finalize", new onRequest() {
            @Override
            public void isSucess(String response) {
                Gson gson=new Gson();
                ModelFinilize modelFinilize=gson.fromJson(response,ModelFinilize.class);
                modelFinilize.getData().getInfo().getSendPrice().toString();
                price.setText(modelFinilize.getData().getInfo().getTotalPrice()+"");

            }

            @Override
            public void isFailed(String error) {

            }

            @Override
            public void OnProgress() {

            }

            @Override
            public Map<String, String> Paramets() {
                String type = "post";
                if (radioGroup.getCheckedRadioButtonId()==R.id.post){
                    type="post";
                }
                if (radioGroup.getCheckedRadioButtonId()==R.id.peyk){
                    type="peyk";
                }
                Map<String,String> stringStringMap=new HashMap<>();
                JSONObject js=new JSONObject();
                try {
                    js.put("token",prefManager.getTokenUser());
                    js.put("type_send",type);
                    js.put("addres_reciver",address.getText().toString());
                    js.put("postal_code",postalCode.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                stringStringMap.put("data",js.toString());
                return stringStringMap;
            }
        });
    }
    private void find() {
        prefManager=new PrefManager(this);
        name=findViewById(R.id.nameFamily);
        address=findViewById(R.id.address);
        postalCode=findViewById(R.id.postalCode);
        mobile=findViewById(R.id.mobile);
        price=findViewById(R.id.price);
        radioGroup=findViewById(R.id.radioGroup);
        btn_payment=findViewById(R.id.btn_payment);
        btnCalculatePrice=findViewById(R.id.btn_cal_price);

        name.setText(prefManager.getNameprofile()+" "+ prefManager.getFAMILY());
        address.setText(prefManager.getAddress());
        postalCode.setText("");
        mobile.setText(prefManager.getMobile());



    }

}
