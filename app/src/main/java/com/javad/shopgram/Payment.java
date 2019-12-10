package com.javad.shopgram;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.javad.shopgram.util.PrefManager;

public class Payment extends FragmentActivity {
    WebView webView;
    PrefManager pref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        webView=findViewById(R.id.webview);
        pref=new PrefManager(this);
        webView.loadUrl(Req.root+"/pay/product/"+pref.getTokenUser());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("/pay/success"))
                {
                    finish();
                    Toast.makeText(getApplicationContext(), "پرداخت با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                }
                if (url.contains("/pay/faild"))
                {
                    finish();
                    Toast.makeText(getApplicationContext(), "پرداخت نا موفق بود", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
