package com.oohaweb.podcastapplication.model;

public class Podcast {
    private String name;
    private String url;

    public Podcast(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
