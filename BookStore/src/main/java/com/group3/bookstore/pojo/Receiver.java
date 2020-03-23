package com.group3.bookstore.pojo;

import java.util.Objects;

public class Receiver {
    private String receiverId;
    private String userId;
    private String receiverName;
    private String receiverPhoneNumber;
    private String province;
    private String city;
    private String county;
    private String street;

    public Receiver() {
    }

    public Receiver(String receiverId, String userId, String receiverName, String receiverPhoneNumber, String province, String city, String county, String street) {
        this.receiverId = receiverId;
        this.userId = userId;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.province = province;
        this.city = city;
        this.county = county;
        this.street = street;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receiver receiver = (Receiver) o;
        return Objects.equals(receiverId, receiver.receiverId) &&
                Objects.equals(userId, receiver.userId) &&
                Objects.equals(receiverName, receiver.receiverName) &&
                Objects.equals(receiverPhoneNumber, receiver.receiverPhoneNumber) &&
                Objects.equals(province, receiver.province) &&
                Objects.equals(city, receiver.city) &&
                Objects.equals(county, receiver.county) &&
                Objects.equals(street, receiver.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiverId, userId, receiverName, receiverPhoneNumber, province, city, county, street);
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "receiverId='" + receiverId + '\'' +
                ", userId='" + userId + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
