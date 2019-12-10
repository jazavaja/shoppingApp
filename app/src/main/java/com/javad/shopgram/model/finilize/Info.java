package com.javad.shopgram.model.finilize;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Info {

    @SerializedName("title_products")
    @Expose
    private List<String> titleProducts = null;
    @SerializedName("count_products")
    @Expose
    private List<String> countProducts = null;
    @SerializedName("price_products")
    @Expose
    private List<String> priceProducts = null;
    @SerializedName("total_Weight_to_KG")
    @Expose
    private Integer totalWeightToKG;
    @SerializedName("base_price_for_One_kg")
    @Expose
    private Object basePriceForOneKg;
    @SerializedName("send_price")
    @Expose
    private Object sendPrice;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;

    public List<String> getTitleProducts() {
        return titleProducts;
    }

    public void setTitleProducts(List<String> titleProducts) {
        this.titleProducts = titleProducts;
    }

    public List<String> getCountProducts() {
        return countProducts;
    }

    public void setCountProducts(List<String> countProducts) {
        this.countProducts = countProducts;
    }

    public List<String> getPriceProducts() {
        return priceProducts;
    }

    public void setPriceProducts(List<String> priceProducts) {
        this.priceProducts = priceProducts;
    }

    public Integer getTotalWeightToKG() {
        return totalWeightToKG;
    }

    public void setTotalWeightToKG(Integer totalWeightToKG) {
        this.totalWeightToKG = totalWeightToKG;
    }

    public Object getBasePriceForOneKg() {
        return basePriceForOneKg;
    }

    public void setBasePriceForOneKg(Object basePriceForOneKg) {
        this.basePriceForOneKg = basePriceForOneKg;
    }

    public Object getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(Object sendPrice) {
        this.sendPrice = sendPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

}
