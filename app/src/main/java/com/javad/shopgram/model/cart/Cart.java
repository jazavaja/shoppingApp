package com.javad.shopgram.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart {

    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("name_shop")
    @Expose
    private String nameShop;
    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

}
