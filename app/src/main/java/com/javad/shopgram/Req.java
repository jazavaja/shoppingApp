package com.javad.shopgram;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.javad.shopgram.model.ModelCheckCode;
import com.javad.shopgram.model.ModelLogin;
import com.javad.shopgram.service.DoRequest;
import com.javad.shopgram.service.onRequest;
import com.javad.shopgram.util.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Req {
    public static PrefManager prefManager;
    public static String rootAsli="http://jafari.xyz";
    public static String root = "http://jafari.xyz/api/v1";
    public static String faildMessage="";
    public Req(Context context, String url, final onRequest onRequest) {
        onRequest.OnProgress();
        prefManager=new PrefManager(context);
        final StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onRequest.isSucess(response);
                        Log.e("Response", response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onRequest.isFailed(error.toString());
                        Log.e("ErrorResponse", error.toString());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return onRequest.Paramets();
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public static void checkCode_Login(Context context, final String code, final View view, final DoRequest request) {
        final ModelCheckCode model = new ModelCheckCode();

        new Req(context, root + "/checkcode", new onRequest() {
            @Override
            public void isSucess(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    model.setStatus(object.getInt("status"));
                    model.setResult(object.getInt("result"));
                    JSONObject object1=new JSONObject(object.getString("data"));
                    model.setMessage(object1.getString("message"));
                    model.setToken(object1.getString("token"));
                    model.setGuest(object1.getString("guest"));
                    request.onPost(model);
                } catch (JSONException e) {
                    model.setStatus(200);
                    model.setResult(-1);
                    request.onPost(model);
                }

            }


            @Override
            public void isFailed(String s) {
                Snackbar.make(view,faildMessage,Snackbar.LENGTH_SHORT).show();
                model.setResult(0);
                model.setStatus(0);
                request.onPost(model);

            }

            @Override
            public void OnProgress() {
                request.onProgress();
            }

            @Override
            public Map<String, String> Paramets() {
                Map<String,String> map=new HashMap<>();
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("token", prefManager.getTokenUser());
                    jsonObject.put("code",code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                map.put("data",jsonObject.toString());
                return map;
            }
        });
    }

    public static void LoginResponse(Context context, final View view, final DoRequest progress, final String mobile) {
        String urlLogin = root + "/login";
        final ModelLogin login = new ModelLogin();
        onRequest onRequest = new onRequest() {
            @Override
            public void isSucess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject data = new JSONObject(jsonObject.getString("data"));
                    login.setResult(jsonObject.getString("result"));
                    login.setStatus(jsonObject.getString("status"));
                    if (jsonObject.getString("result").equals("1")) {
                        login.setCode(data.getString("code"));
                        login.setToken(data.getString("token"));
                        login.setIsGuest(data.getInt("is_guest"));
                        progress.onPost(login);

                    }


//                    jsonObject.getString("result");

                } catch (JSONException e) {
                    login.setStatus("-2");
                    login.setResult("-2");
                    progress.onPost(login);

                    e.printStackTrace();
                }

            }

            @Override
            public void isFailed(String s) {
                login.setStatus("-1");
                login.setResult("-1");
                Req.onFailed(view);
                progress.onPost(login);

            }

            @Override
            public void OnProgress() {
                progress.onProgress();
            }

            @Override
            public Map<String, String> Paramets() {
                HashMap<String, String> map = new HashMap<>();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone_number", mobile);
                    map.put("data", jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                map.put("data","{'phone_number':'"+mobile+"'}");
                return map;
            }
        };
        Req req = new Req(context, urlLogin, onRequest);
//        return login;
    }

    public static void onFailed(View view) {
        Snackbar.make(view, "عملیات ناموفق بود دوباره تلاش کنید", Snackbar.LENGTH_SHORT).show();
    }
    public static void onFailedCustom(View view,String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public static void failedSetView(View progress,View fail,View mainOrSuccess){
        progress.setVisibility(View.GONE);
        fail.setVisibility(View.VISIBLE);
        mainOrSuccess.setVisibility(View.GONE);
    }
    public static void sucessSetView(View progress,View fail,View mainOrSuccess){
        progress.setVisibility(View.GONE);
        fail.setVisibility(View.GONE);
        mainOrSuccess.setVisibility(View.VISIBLE);
    }

}
