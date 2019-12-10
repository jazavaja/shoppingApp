package com.javad.shopgram.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProfile {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("self_reagent")
    @Expose
    private Object selfReagent;
    @SerializedName("pic_url")
    @Expose
    private String picUrl;
    @SerializedName("postal_code")
    @Expose
    private Object postalCode;
    @SerializedName("addres")
    @Expose
    private Object addres;
    @SerializedName("favarits_count")
    @Expose
    private String favaritsCount;
    @SerializedName("following_count")
    @Expose
    private String followingCount;
    @SerializedName("cart_count")
    @Expose
    private String cartCount;
    @SerializedName("presented_count")
    @Expose
    private String presentedCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getSelfReagent() {
        return selfReagent;
    }

    public void setSelfReagent(Object selfReagent) {
        this.selfReagent = selfReagent;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public Object getAddres() {
        return addres;
    }

    public void setAddres(Object addres) {
        this.addres = addres;
    }

    public String getFavaritsCount() {
        return favaritsCount;
    }

    public void setFavaritsCount(String favaritsCount) {
        this.favaritsCount = favaritsCount;
    }

    public String getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(String followingCount) {
        this.followingCount = followingCount;
    }

    public String getCartCount() {
        return cartCount;
    }

    public void setCartCount(String cartCount) {
        this.cartCount = cartCount;
    }

    public String getPresentedCount() {
        return presentedCount;
    }

    public void setPresentedCount(String presentedCount) {
        this.presentedCount = presentedCount;
    }

}