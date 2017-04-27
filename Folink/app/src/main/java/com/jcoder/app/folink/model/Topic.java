package com.jcoder.app.folink.model;

/**
 * Created by Rory on 2016/10/10.
 */

public class Topic {

    private String title;
    private String url;
    private String category;
    private String date;

    /**
     * Constructor
     */
    public Topic(String title, String url, String category, String date) {
        this.title = title;
        this.url = url;
        this.category = category;
        this.date = date;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format(" Title   : %s%n", this.title));
        s.append(String.format(" Category: %s%n", this.category));
        s.append(String.format(" Date    : %s%n", this.date));
        s.append(String.format(" URL     : %s%n", this.url));
        return s.toString();
    }

    public String toJSON() {
        String keyValFormat = "\"%s\": \"%s\"";
        StringBuilder s = new StringBuilder();
        s.append("{");
        s.append(String.format(keyValFormat, "title"   , this.title   )).append(", ");
        s.append(String.format(keyValFormat, "url"     , this.url     )).append(", ");
        s.append(String.format(keyValFormat, "category", this.category)).append(", ");
        s.append(String.format(keyValFormat, "date",     this.date    ));
        s.append("}");

        return s.toString();
    }
}
