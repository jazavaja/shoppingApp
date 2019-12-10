package com.javad.shopgram.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("seller_id")
    @Expose
    private String sellerId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desciption")
    @Expose
    private String desciption;
    @SerializedName("week_market_price")
    @Expose
    private String weekMarketPrice;
    @SerializedName("shop_price")
    @Expose
    private String shopPrice;
    @SerializedName("vitrin_price")
    @Expose
    private String vitrinPrice;
    @SerializedName("path_photos")
    @Expose
    private String pathPhotos;
    @SerializedName("week_market")
    @Expose
    private String weekMarket;
    @SerializedName("count_product")
    @Expose
    private String countProduct;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("liked")
    @Expose
    private String liked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getWeekMarketPrice() {
        return weekMarketPrice;
    }

    public void setWeekMarketPrice(String weekMarketPrice) {
        this.weekMarketPrice = weekMarketPrice;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getVitrinPrice() {
        return vitrinPrice;
    }

    public void setVitrinPrice(String vitrinPrice) {
        this.vitrinPrice = vitrinPrice;
    }

    public String getPathPhotos() {
        return pathPhotos;
    }

    public void setPathPhotos(String pathPhotos) {
        this.pathPhotos = pathPhotos;
    }

    public String getWeekMarket() {
        return weekMarket;
    }

    public void setWeekMarket(String weekMarket) {
        this.weekMarket = weekMarket;
    }

    public String getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(String countProduct) {
        this.countProduct = countProduct;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

}
