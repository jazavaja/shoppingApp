package com.javad.shopgram.model;

public class ModelGetSellerStory {
    int id;
    String matn;
    String pic_url;

    public String getMatn() {
        return matn;
    }

    public void setMatn(String matn) {
        this.matn = matn;
    }

    public int getId() {
        return id;
    }
    public String getPic_url() {
        return pic_url;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}

