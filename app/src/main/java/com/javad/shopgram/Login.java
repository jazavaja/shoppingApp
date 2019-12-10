package com.javad.shopgram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.javad.shopgram.model.ModelCheckCode;
import com.javad.shopgram.model.ModelLogin;
import com.javad.shopgram.sellerApp.MainSeller;
import com.javad.shopgram.service.DoRequest;
import com.javad.shopgram.util.PrefManager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    TextView btnSendCode, btn_login, btn_seller;
    public static TextInputEditText mobile, code;
    FrameLayout main;
    int timeToNext = 0;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Req.LoginResponse(this);
        prefManager = new PrefManager(Login.this);
        btnSendCode = findViewById(R.id.btn_sendCode);
        btn_login = findViewById(R.id.btn_login);
        btn_seller = findViewById(R.id.btn_seller);
        main = findViewById(R.id.main);
        mobile = findViewById(R.id.et_mobile);
        code = findViewById(R.id.et_code);

        btnSendCode.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_seller.setOnClickListener(this);

        if (prefManager.getSellerlogin().equals("1")){
            startActivity(new Intent(Login.this, MainSeller.class));
            finish();
        }

        if (prefManager.getUserLogin().equals("1")) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_sendCode:
                setBtnSendCode();
                break;
            case R.id.btn_login:
                setBtn_Check_login();
//                startActivity(new Intent(this, SignIn.class));
                break;

            case R.id.btn_seller:
                startActivity(new Intent(this, SignIn.class));
                break;

        }


    }

    public void setBtn_Check_login() {
        Req.checkCode_Login(this, code.getText().toString(), main, new DoRequest() {
            @Override
            public void onProgress() {
                btn_login.setText("لطفا کمی صبر کنید..");
            }

            @Override
            public void onPost(Object o) {
                btn_login.setText("ورود");
                ModelCheckCode model = (ModelCheckCode) o;
                int result = model.getResult();
                if (result == 0) {
                    Req.onFailed(main);
                }
                if (result == 89)
                    Req.onFailedCustom(main, "کد اشتباه است");
                if (result == 1) {
                    prefManager.setUserLogin();
                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    public void setTimeToNext() {
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
//                                int time2= (int) millisUntilFinished;
//                                time2=time;
                timeToNext = (int) (millisUntilFinished / 1000);
                btnSendCode.setText("دریافت مجدد کد" + timeToNext + " ثانیه دیگر ");
            }

            @Override
            public void onFinish() {
                btnSendCode.setText("ارسال کد تایید");
                timeToNext = 0;
            }
        }.start();

    }

    public void setBtnSendCode() {
        Log.e("Timer", String.valueOf(timeToNext));

        if (!mobile.getText().toString().equals("") && mobile.getText().length() == 11) {
            if (timeToNext == 0) {
                Req.LoginResponse(this, main, new DoRequest() {
                    @Override
                    public void onProgress() {
                        btnSendCode.setText("لطفا صبر کنید");
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPost(Object o) {
                        setTimeToNext();
                        ModelLogin login = (ModelLogin) o;
                        if (login.getResult().equals("11"))
                            Req.onFailedCustom(main, "شما ثبت نام نکرده اید!!");
                        if (login.getResult().equals("0"))
                            Req.onFailedCustom(main, "شماره موبایل اشتباه است!");
                        if (login.getResult().equals("1")) {
                            prefManager.setTokenUser(login.getToken());
                        }
                    }
                }, mobile.getText().toString());

            }
        } else
            Req.onFailedCustom(main, "شماره موبایل اشتباه است");
    }
}
