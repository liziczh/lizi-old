package com.group3.bookstore.pojo;

import java.util.Objects;

public class Commodity {
    private String commodityId;
    private String commodityName;
    private String commodityDescription;
    private String commodityImg;
    private String categoryId;
    private String commodityIntroduction;
    private String commodityContents;
    private double commodityPrice;
    private double commodityPurchasePrice;
    private String authorName;
    private String publishHouse;
    private String publishDate;
    private int commodityCount;
    private int salesCount;
    private int collectCount;
    private int commentCount;

    public Commodity() {
    }

    public Commodity(String commodityId, String commodityName, String commodityDescription, String commodityImg, String categoryId, String commodityIntroduction, String commodityContents, double commodityPrice, double commodityPurchasePrice, String authorName, String publishHouse, String publishDate, int commodityCount, int salesCount, int collectCount, int commentCount) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityDescription = commodityDescription;
        this.commodityImg = commodityImg;
        this.categoryId = categoryId;
        this.commodityIntroduction = commodityIntroduction;
        this.commodityContents = commodityContents;
        this.commodityPrice = commodityPrice;
        this.commodityPurchasePrice = commodityPurchasePrice;
        this.authorName = authorName;
        this.publishHouse = publishHouse;
        this.publishDate = publishDate;
        this.commodityCount = commodityCount;
        this.salesCount = salesCount;
        this.collectCount = collectCount;
        this.commentCount = commentCount;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public String getCommodityImg() {
        return commodityImg;
    }

    public void setCommodityImg(String commodityImg) {
        this.commodityImg = commodityImg;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCommodityIntroduction() {
        return commodityIntroduction;
    }

    public void setCommodityIntroduction(String commodityIntroduction) {
        this.commodityIntroduction = commodityIntroduction;
    }

    public String getCommodityContents() {
        return commodityContents;
    }

    public void setCommodityContents(String commodityContents) {
        this.commodityContents = commodityContents;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public double getCommodityPurchasePrice() {
        return commodityPurchasePrice;
    }

    public void setCommodityPurchasePrice(double commodityPurchasePrice) {
        this.commodityPurchasePrice = commodityPurchasePrice;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublishHouse() {
        return publishHouse;
    }

    public void setPublishHouse(String publishHouse) {
        this.publishHouse = publishHouse;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(int commodityCount) {
        this.commodityCount = commodityCount;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return Double.compare(commodity.commodityPrice, commodityPrice) == 0 &&
                Double.compare(commodity.commodityPurchasePrice, commodityPurchasePrice) == 0 &&
                commodityCount == commodity.commodityCount &&
                salesCount == commodity.salesCount &&
                collectCount == commodity.collectCount &&
                commentCount == commodity.commentCount &&
                Objects.equals(commodityId, commodity.commodityId) &&
                Objects.equals(commodityName, commodity.commodityName) &&
                Objects.equals(commodityDescription, commodity.commodityDescription) &&
                Objects.equals(commodityImg, commodity.commodityImg) &&
                Objects.equals(categoryId, commodity.categoryId) &&
                Objects.equals(commodityIntroduction, commodity.commodityIntroduction) &&
                Objects.equals(commodityContents, commodity.commodityContents) &&
                Objects.equals(authorName, commodity.authorName) &&
                Objects.equals(publishHouse, commodity.publishHouse) &&
                Objects.equals(publishDate, commodity.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityId, commodityName, commodityDescription, commodityImg, categoryId, commodityIntroduction, commodityContents, commodityPrice, commodityPurchasePrice, authorName, publishHouse, publishDate, commodityCount, salesCount, collectCount, commentCount);
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "commodityId='" + commodityId + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", commodityDescription='" + commodityDescription + '\'' +
                ", commodityImg='" + commodityImg + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", commodityIntroduction='" + commodityIntroduction + '\'' +
                ", commodityContents='" + commodityContents + '\'' +
                ", commodityPrice=" + commodityPrice +
                ", commodityPurchasePrice=" + commodityPurchasePrice +
                ", authorName='" + authorName + '\'' +
                ", publishHouse='" + publishHouse + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", commodityCount=" + commodityCount +
                ", salesCount=" + salesCount +
                ", collectCount=" + collectCount +
                ", commentCount=" + commentCount +
                '}';
    }
}