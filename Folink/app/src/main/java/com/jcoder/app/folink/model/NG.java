package com.jcoder.app.folink.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rory on 2016/10/9.
 */

public class NG {

    public NG(String title , String link  , String description , String content){
        this.title = title;
        this.link = link ;
        this.description = description;
        this.content = content;
    }

    @SerializedName("link")
    public String link;

    @SerializedName("description")
    public String description;

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
