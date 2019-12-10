package com.javad.shopgram.model.Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoSeller {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name_shop")
    @Expose
    private String nameShop;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("phone_shop")
    @Expose
    private Object phoneShop;
    @SerializedName("postal_code")
    @Expose
    private Object postalCode;
    @SerializedName("reagent_code")
    @Expose
    private Object reagentCode;
    @SerializedName("national_code")
    @Expose
    private Object nationalCode;
    @SerializedName("addres")
    @Expose
    private Object addres;
    @SerializedName("biography")
    @Expose
    private Object biography;
    @SerializedName("self_reagent")
    @Expose
    private Object selfReagent;
    @SerializedName("register_code")
    @Expose
    private Object registerCode;
    @SerializedName("pic_url")
    @Expose
    private String picUrl;
    @SerializedName("logo_url")
    @Expose
    private Object logoUrl;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("followers_count")
    @Expose
    private String followersCount;
    @SerializedName("products_count")
    @Expose
    private String productsCount;
    @SerializedName("followers")
    @Expose
    private List<Follower> followers = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
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

    public Object getPhoneShop() {
        return phoneShop;
    }

    public void setPhoneShop(Object phoneShop) {
        this.phoneShop = phoneShop;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public Object getReagentCode() {
        return reagentCode;
    }

    public void setReagentCode(Object reagentCode) {
        this.reagentCode = reagentCode;
    }

    public Object getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Object nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Object getAddres() {
        return addres;
    }

    public void setAddres(Object addres) {
        this.addres = addres;
    }

    public Object getBiography() {
        return biography;
    }

    public void setBiography(Object biography) {
        this.biography = biography;
    }

    public Object getSelfReagent() {
        return selfReagent;
    }

    public void setSelfReagent(Object selfReagent) {
        this.selfReagent = selfReagent;
    }

    public Object getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(Object registerCode) {
        this.registerCode = registerCode;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Object getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(Object logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    public String getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(String productsCount) {
        this.productsCount = productsCount;
    }

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

}
