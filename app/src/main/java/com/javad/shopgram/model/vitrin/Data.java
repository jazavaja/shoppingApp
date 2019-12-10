package com.javad.shopgram.model.vitrin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("vitrin")
    @Expose
    private List<Vitrin> vitrin = null;
    @SerializedName("next_page")
    @Expose
    private String nextPage;

    public List<Vitrin> getVitrin() {
        return vitrin;
    }

    public void setVitrin(List<Vitrin> vitrin) {
        this.vitrin = vitrin;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

}
