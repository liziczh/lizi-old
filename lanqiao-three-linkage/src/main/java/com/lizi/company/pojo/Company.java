package com.lizi.company.pojo;

import java.util.Objects;

public class Company {
    // 公司ID
    private int comId;
    // 公司编号
    private String comNo;
    // 公司名
    private String comName;

    public Company() {
    }

    public Company(int comId, String comNo, String comName) {
        this.comId = comId;
        this.comNo = comNo;
        this.comName = comName;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getComNo() {
        return comNo;
    }

    public void setComNo(String comNo) {
        this.comNo = comNo;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return comId == company.comId &&
                Objects.equals(comNo, company.comNo) &&
                Objects.equals(comName, company.comName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comId, comNo, comName);
    }

    @Override
    public String toString() {
        return "Company{" +
                "comId=" + comId +
                ", comNo='" + comNo + '\'' +
                ", comName='" + comName + '\'' +
                '}';
    }
}
