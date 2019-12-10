package com.javad.shopgram.model.haftebazzar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("hafteBazar")
    @Expose
    private List<HafteBazar> hafteBazar = null;
    @SerializedName("next_page")
    @Expose
    private String nextPage;

    public List<HafteBazar> getHafteBazar() {
        return hafteBazar;
    }

    public void setHafteBazar(List<HafteBazar> hafteBazar) {
        this.hafteBazar = hafteBazar;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

}
