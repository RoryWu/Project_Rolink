package com.jcoder.app.folink.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Rory on 2016/10/19.
 */

public class ABList {

    @SerializedName("items")
    ArrayList<AbNewsItem> newsList;
    public ArrayList<AbNewsItem> getAbNewsList() {
        return newsList;
    }
    public void setAbNewsList(ArrayList<AbNewsItem> newsList) {
        this.newsList = newsList;
    }
}
