package com.jcoder.app.folink.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Rory on 2016/10/19.
 */

public class NGList {

    @SerializedName("T1348647909107")
    ArrayList<NG> newsList;
    public ArrayList<NG> getNewsList() {
        return newsList;
    }
    public void setNewsList(ArrayList<NG> newsList) {
        this.newsList = newsList;
    }
}
