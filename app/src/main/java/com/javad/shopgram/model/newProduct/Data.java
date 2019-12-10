package com.javad.shopgram.model.newProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("newProducts")
    @Expose
    private List<NewProduct> newProducts = null;

    public List<NewProduct> getNewProducts() {
        return newProducts;
    }

    public void setNewProducts(List<NewProduct> newProducts) {
        this.newProducts = newProducts;
    }

}
