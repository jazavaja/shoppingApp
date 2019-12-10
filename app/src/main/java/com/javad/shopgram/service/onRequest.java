package com.javad.shopgram.service;

import java.util.Map;

public  interface onRequest {
    public void isSucess(String response);
    public void isFailed(String error);
    public  void OnProgress();
    public Map<String,String> Paramets();
}
