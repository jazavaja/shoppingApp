package com.javad.shopgram.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.javad.shopgram.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by emad on 6/29/2016.
 */
public class General {

    public static String PREF_NAME = "telescopeSetting";
    public static String PREF_FONT_KEY = "TXTfont";
    private static ProgressDialog loading;
    private static Dialog loadingDialog;
    static Dialog di;
    public static int fail_Image=R.drawable.fail;
    public static int image_placeHolder=R.mipmap.ic_launcher;
    public static int image_noPicure=R.drawable.no_image_available;
    private static General instance;
    public static String keyIdForIntentDetail="id";
    public static String progress="لطفا کمی صبر کنید";
    public static String failMssg="نا موفق بود";
    public static String keyTitle="title";
    public static String keyPhotos="photos";
    public static String keyPriceOrg="priceORG";
    public static String keyPriceSHOP="priceSHOP";
    public static String keyDesc="desc";
    public static String keyIdSeller="id_seller";
    public static String namojod="محصول موجود نیست";
    public static String addedToCart="به سبد خرید افزوده شد";
    public static String kamShod="از سبد خرید کم شد";
    public static String afzodeDhod="به مقدار کالا اضافه شد";
    public static String empty="جستجویی یافت نشد";
    Context context;
    private static Typeface typeface;
    private static Typeface carNumberTypeface;

    public General(Context context) {
        this.context = context;
    }

    public static General getInstance(Context context) {
        if (instance == null) {
            instance = new General(context);
        }
        return instance;
    }
    public static String strNoNull(Object s){
        if (s==null){
            return "";
        }else {
            return s.toString();
        }
    }
    public static Map<String,String> map(PrefManager prefManager){
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
    public static Map<String,String> map(PrefManager prefManager,JSONObject jsonObject){
        Map<String, String> map = new HashMap<>();
//        jsonObject = new JSONObject();
        try {
            jsonObject.put("token", prefManager.getTokenUser());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String js = jsonObject.toString();
        map.put("data", js);
        return map;
    }

    public static void picasso(String url,ImageView imageView){
        Picasso.get().load(url).error(General.fail_Image).placeholder(General.image_placeHolder).into(imageView);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static AlertDialog dialog(Context c, String title, String message, String posTxt, DialogInterface.OnClickListener posListener
            , String negTxt, DialogInterface.OnClickListener negListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(posTxt, posListener);
        builder.setPositiveButton(negTxt, negListener);
        builder.create();
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public static String getAppVersion(Context c) {
        try {
            PackageInfo packageInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    public static int getScrWidth(Activity c) {
        Display display = c.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getScrHeight(Activity c) {
        Display display = c.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static boolean DataOnOff(boolean status, Context context) {
        int bv = 0;
        try {
            if (bv == Build.VERSION_CODES.FROYO) {
                Log.e("internet_data", "first block");
                //android 2.2 versiyonu için
                Method dataConnSwitchmethod;
                Class<?> telephonyManagerClass;
                Object ITelephonyStub;
                Class<?> ITelephonyClass;

                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);

                telephonyManagerClass = Class.forName(telephonyManager
                        .getClass().getName());
                Method getITelephonyMethod = telephonyManagerClass
                        .getDeclaredMethod("getITelephony");
                getITelephonyMethod.setAccessible(true);
                ITelephonyStub = getITelephonyMethod.invoke(telephonyManager);
                ITelephonyClass = Class.forName(ITelephonyStub.getClass()
                        .getName());

                if (status) {
                    dataConnSwitchmethod = ITelephonyClass
                            .getDeclaredMethod("enableDataConnectivity");
                } else {
                    dataConnSwitchmethod = ITelephonyClass
                            .getDeclaredMethod("disableDataConnectivity");
                }
                dataConnSwitchmethod.setAccessible(true);
                dataConnSwitchmethod.invoke(ITelephonyStub);

            } else {
                // android 2.2 üstü versiyonlar için
                final ConnectivityManager conman = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                final Class<?> conmanClass = Class.forName(conman.getClass()
                        .getName());
                final Field iConnectivityManagerField = conmanClass
                        .getDeclaredField("mService");
                iConnectivityManagerField.setAccessible(true);
                final Object iConnectivityManager = iConnectivityManagerField
                        .get(conman);
                final Class<?> iConnectivityManagerClass = Class
                        .forName(iConnectivityManager.getClass().getName());
                final Method setMobileDataEnabledMethod = iConnectivityManagerClass
                        .getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
                setMobileDataEnabledMethod.setAccessible(true);
                setMobileDataEnabledMethod.invoke(iConnectivityManager, status);
            }

            return true;

        } catch (Exception e) {
            Log.e("Mobile Data", "error turning on/off data");
            e.printStackTrace();
            return false;
        }
    }

    public static void SetMobileDataEnabled(Context paramContext, boolean on) {
        try
        {

            ConnectivityManager connectivityManager = (ConnectivityManager)paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            Method method = connectivityManager.getClass().getMethod("setMobileDataEnabled", Boolean.class);

            method.invoke(connectivityManager, on);
        }
        catch (NoSuchMethodException e)

        {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean GetMobileDataEnabled(Context paramContext) {
        try
        {
            ConnectivityManager connectivityManager = (ConnectivityManager)paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            Method method = connectivityManager.getClass().getMethod("getMobileDataEnabled");
            return (boolean)method.invoke(connectivityManager);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public static void turnGPSOn(Context c){
        String provider = Settings.Secure.getString(c.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            c.sendBroadcast(poke);
        }
    }

    public static boolean canToggleGPS(Context c) {
        PackageManager pacman =c. getPackageManager();
        PackageInfo pacInfo = null;

        try {
            pacInfo = pacman.getPackageInfo("com.android.settings", PackageManager.GET_RECEIVERS);
        } catch (PackageManager.NameNotFoundException e) {
            return false; //package not found
        }

        if(pacInfo != null){
            for(ActivityInfo actInfo : pacInfo.receivers){
                //test if recevier is exported. if so, we can toggle GPS.
                if(actInfo.name.equals("com.android.settings.widget.SettingsAppWidgetProvider") && actInfo.exported){
                    return true;
                }
            }
        }

        return false; //default
    }

    public static void ShowLoading(Context context){


        try {

            if (di != null && di.isShowing())
                di.dismiss();

            di = new Dialog(context, R.style.AppTheme_NoActionBar_FullScreen);
            di.setContentView(R.layout.loading_dialog);
            di.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            TextView txt = di.findViewById(R.id.txt);

            Typeface face=Typeface.createFromAsset(context.getAssets(),"fonts/IRANSans_FaNum.ttf");
            txt.setTypeface(face);

            di.setCancelable(false);
            di.setCanceledOnTouchOutside(false);
            di.show();

        }catch (Exception e){
            Log.e("exception","آروم باش خخخخ");
        }

    }

    public static void DismisLoading(Context context){

        try {

                if (di == null) {
                    di = new Dialog(context, R.style.AppTheme_NoActionBar_FullScreen);
                    di.setContentView(R.layout.loading_dialog);
                    di.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                }

                di.dismiss();

        }catch (Exception e){
            Log.e("exception","آروم باش خخخخ");
        }
    }

    public static void failSnackBar(View view) {
        Snackbar.make(view,"دریافت ناموفق بود",Snackbar.LENGTH_SHORT).show();
    }
    public static void customSnack(View view,String y) {
        Snackbar.make(view,y,Snackbar.LENGTH_SHORT).show();
    }

/*
    public static String encrypt(String txt) {
        try {
            byte[] b = txt.getBytes("UTF-8");
            String base64 = Base64.encodeToString(b, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return txt;
    }
    */
}
