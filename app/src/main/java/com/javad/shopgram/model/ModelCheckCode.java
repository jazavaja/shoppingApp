package com.javad.shopgram.model;

public class ModelCheckCode {
    public int status;
    public int result;
    public String message;
    public String token;
    public String guest;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getToken() {
        return token;
    }

    public int getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public String getGuest() {
        return guest;
    }

    public String getMessage() {
        return message;
    }
}
