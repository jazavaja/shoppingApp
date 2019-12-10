package com.javad.shopgram.model.Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("info_seller")
    @Expose
    private List<InfoSeller> infoSeller = null;

    public List<InfoSeller> getInfoSeller() {
        return infoSeller;
    }

    public void setInfoSeller(List<InfoSeller> infoSeller) {
        this.infoSeller = infoSeller;
    }

}
