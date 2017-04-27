package com.jcoder.app.folink.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by xinghongfei on 16/8/17.
 */
public class NewsList {

    // TODO: 16/8/17  donot forget remove first
    @SerializedName("T1348647909107")
    ArrayList<FNews> newsList;
    public ArrayList<FNews> getNewsList() {
        return newsList;
    }
    public void setNewsList(ArrayList<FNews> newsList) {
        this.newsList = newsList;
    }
}
