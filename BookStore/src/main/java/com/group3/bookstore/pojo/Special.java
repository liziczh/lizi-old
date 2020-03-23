package com.group3.bookstore.pojo;

import java.util.Objects;

public class Special {
    private String specialId;
    private String specialSort;
    private String sowingMap;

    public Special() {
    }

    public Special(String specialSort, String sowingMap) {
        this.specialSort = specialSort;
        this.sowingMap = sowingMap;
    }

    public Special(String specialId, String specialSort, String sowingMap) {
        this.specialId = specialId;
        this.specialSort = specialSort;
        this.sowingMap = sowingMap;
    }

    public String getSpecialId() {
        return specialId;
    }

    public void setSpecialId(String specialId) {
        this.specialId = specialId;
    }

    public String getSpecialSort() {
        return specialSort;
    }

    public void setSpecialSort(String specialSort) {
        this.specialSort = specialSort;
    }

    public String getSowingMap() {
        return sowingMap;
    }

    public void setSowingMap(String sowingMap) {
        this.sowingMap = sowingMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Special special = (Special) o;
        return Objects.equals(specialId, special.specialId) &&
                Objects.equals(specialSort, special.specialSort) &&
                Objects.equals(sowingMap, special.sowingMap);
    }

    @Override
    public int hashCode() {

        return Objects.hash(specialId, specialSort, sowingMap);
    }

    @Override
    public String toString() {
        return "Special{" +
                "specialId='" + specialId + '\'' +
                ", specialSort='" + specialSort + '\'' +
                ", sowingMap='" + sowingMap + '\'' +
                '}';
    }
}
