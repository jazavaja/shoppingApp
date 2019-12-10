package com.javad.shopgram.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PrefManager {
    private static final String FAMILY = "family";
    private static final String MOBILE = "mobile";
    private static final String SELLERLOGIN = "sellerlogin";
    public static SharedPreferences pref;
    public static SharedPreferences.Editor editor;
    public static Context mContext;
    // shared pref mode
    private int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "sabteneshan";
    private String mobile;

    public PrefManager(Context context) {
        mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    // Shared Preferences keys
    private static final String TOKEN_USER = "private_token";
    private static final String PID = "pid";
    private static final String PERMISSIONID = "permission_id";
    private static final String PHOTOPROFILE = "photo_profile";
    private static final String USER_LOGIN_OR_No = "userIsLogin";
    private static final String NAMEPROFILE = "name_profile";

    private static final String IS_GUEST = "1";
    private static final String IS_USER = "0";
    private static final String IS_SELLER = "3";

    public String getUserLogin() {
        return pref.getString(USER_LOGIN_OR_No, "");
    }

    public  String getSellerlogin() {
        return pref.getString(SELLERLOGIN,"0");
    }

    public void setSellerLogin(){
        editor.putString(SELLERLOGIN,"1");
        editor.apply();
    }

    public void setUserLogin() {
        editor.putString(USER_LOGIN_OR_No, "1");
        editor.apply();
    }
    public void setDisableLogin(){
        editor.putString(USER_LOGIN_OR_No,"0");
        editor.apply();
    }

    public String getTokenUser() {
        Log.e("token", pref.getString(TOKEN_USER, ""));
        return pref.getString(TOKEN_USER, "");
    }

    public void setTokenUser(String tokenUser) {
        Log.e("set_token", tokenUser);

        editor.putString(TOKEN_USER, tokenUser);
        editor.apply();
    }

    public String getPermissionid() {
//        Log.e("token", pref.getString(PERMISSIONID, ""));
        return pref.getString(PERMISSIONID, "");
    }

    public void setPermissionid(String permission_id) {
//        Log.e("set_permission_id", permission_id);
        editor.putString(PERMISSIONID, permission_id);
        editor.apply();
    }

    public String getPid() {
//        Log.e("token", pref.getString(PERMISSIONID, ""));
        return pref.getString(PID, "");
    }

    public void setPid(String pid) {
        Log.e("set_pid", pid);
        editor.putString(PID, pid);
        editor.apply();
    }

    public String getPhotoprofile() {

        return pref.getString(PHOTOPROFILE, "");
    }

    public void setPhotoprofile(String photoprofile) {
        Log.e("set_photoprofile", photoprofile);
        editor.putString(PHOTOPROFILE, photoprofile);
        editor.apply();
    }

    public String getNameprofile() {

        return pref.getString(NAMEPROFILE, "");
    }

    public static String getFAMILY() {
        return pref.getString(FAMILY, "");
    }

    public void setNameprofile(String nameprofile) {
//        Log.e("set_photoprofile", nameprofile);
        editor.putString(NAMEPROFILE, nameprofile);
        editor.apply();
    }
    public void setFamily(String nameprofile) {
//        Log.e("set_photoprofile", nameprofile);
        editor.putString(FAMILY, nameprofile);
        editor.apply();
    }

    public void setAddress(String text) {
        editor.putString("address",text);
        editor.apply();
    }
    public String getAddress(){
        return pref.getString("address","ایران");
    }

    public void setMobile(String mobile){
        editor.putString(MOBILE,mobile);
        editor.apply();
    }
    public String getMobile() {
        return pref.getString(MOBILE,"");
    }
}
