package com.javad.shopgram.model;

import java.util.List;

public class ModelProductFolow
{
    private int status;
    private int result;
    private List<ModelProduct> modelProducts;
//    GET
    public int getStatus() {
        return status;
    }

    public int getResult() {
        return result;
    }
    public List<ModelProduct> getModelProducts() {
        return modelProducts;
    }

//    SET
    public void setStatus(int status) {
        this.status = status;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setModelProducts(List<ModelProduct> modelProducts) {
        this.modelProducts = modelProducts;
    }


}
