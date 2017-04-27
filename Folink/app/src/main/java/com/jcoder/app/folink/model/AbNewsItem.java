package com.jcoder.app.folink.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Rory on 2016/10/19.
 */

public class AbNewsItem implements Serializable {


    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("guid")
    private String guid;
    @SerializedName("pubDate")
    private String pubDate;
    @SerializedName("categories")
    private String[] categories;
    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
