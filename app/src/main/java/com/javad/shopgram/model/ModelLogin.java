package com.javad.shopgram.model;

public  class ModelLogin {
    public String result;
    public String status;
    public String code;
    public String token;
    public int isGuest;

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public int getIsGuest() {
        return isGuest;
    }

    public String getToken() {
        return token;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setIsGuest(int isGuest) {
        this.isGuest = isGuest;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
