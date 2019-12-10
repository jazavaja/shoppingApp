package com.javad.shopgram.model;


public class ModelProduct {

    private int id;
    private int seller_id;
    private String title;
    private String desciption;
    private int shop_price;
    private int virtin_price;
    private String path_photos;
    private int week_market_price;


    public void setId(int id) {
        this.id = id;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public void setShop_price(int shop_price) {
        this.shop_price = shop_price;
    }

    public void setPath_photos(String path_photos) {
        this.path_photos = path_photos;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public void setVirtin_price(int virtin_price) {
        this.virtin_price = virtin_price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWeek_market_price(int week_market_price) {
        this.week_market_price = week_market_price;
    }

    public int getId() {
        return id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesciption() {
        return desciption;
    }

    public int getShop_price() {
        return shop_price;
    }

    public int getVirtin_price() {
        return virtin_price;
    }

    public String getPath_photos() {
        return path_photos;
    }

    public int getWeek_market_price() {
        return week_market_price;
    }

}
