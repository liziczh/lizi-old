package com.group3.bookstore.pojo;

import java.util.Objects;

public class Ad {
    private String adId;
    private String adName;
    private String adImg;

    public Ad() {
    }

    public Ad(String adName, String adImg) {
        this.adName = adName;
        this.adImg = adImg;
    }

    public Ad(String adId, String adName, String adImg) {
        this.adId = adId;
        this.adName = adName;
        this.adImg = adImg;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdImg() {
        return adImg;
    }

    public void setAdImg(String adImg) {
        this.adImg = adImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(adId, ad.adId) &&
                Objects.equals(adName, ad.adName) &&
                Objects.equals(adImg, ad.adImg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(adId, adName, adImg);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "adId='" + adId + '\'' +
                ", adName='" + adName + '\'' +
                ", adImg='" + adImg + '\'' +
                '}';
    }
}
