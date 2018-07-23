package com.dryfruithub.ecommerceadmin.modelclass;

public class BannerDetails {

    String url;
    String name;
    int clicks;

    public BannerDetails(){}

    public BannerDetails(String url, int clicks, String name) {
        this.url = url;
        this.clicks = clicks;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public int getClicks() {
        return clicks;
    }

    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public void setName(String name) {
        this.name = name;
    }



}
