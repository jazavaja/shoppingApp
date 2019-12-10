package com.javad.shopgram.model;

import java.util.List;

public class ModelGetStory {
    int status;
    int result;
    List<ModelGetSellerStory> modelGetSellerStories;

    public int getStatus() {
        return status;
    }

    public int getResult() {
        return result;
    }

    public List<ModelGetSellerStory> getModelGetSellerStories() {
        return modelGetSellerStories;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setModelGetSellerStories(List<ModelGetSellerStory> modelGetSellerStories) {
        this.modelGetSellerStories = modelGetSellerStories;
    }
}
