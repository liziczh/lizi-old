package com.lizi.company.pojo;

import java.util.Objects;

public class SubCompany {
    private int subComId;
    private String subComNo;
    private String subComName;
    private int comId;

    public SubCompany() {
    }

    public SubCompany(int subComId, String subComNo, String subComName, int comId) {
        this.subComId = subComId;
        this.subComNo = subComNo;
        this.subComName = subComName;
        this.comId = comId;
    }

    public int getSubComId() {
        return subComId;
    }

    public void setSubComId(int subComId) {
        this.subComId = subComId;
    }

    public String getSubComNo() {
        return subComNo;
    }

    public void setSubComNo(String subComNo) {
        this.subComNo = subComNo;
    }

    public String getSubComName() {
        return subComName;
    }

    public void setSubComName(String subComName) {
        this.subComName = subComName;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCompany that = (SubCompany) o;
        return subComId == that.subComId &&
                comId == that.comId &&
                Objects.equals(subComNo, that.subComNo) &&
                Objects.equals(subComName, that.subComName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subComId, subComNo, subComName, comId);
    }

    @Override
    public String toString() {
        return "SubCompany{" +
                "subComId=" + subComId +
                ", subComNo='" + subComNo + '\'' +
                ", subComName='" + subComName + '\'' +
                ", comId=" + comId +
                '}';
    }
}
