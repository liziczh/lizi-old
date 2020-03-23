package com.qm.crawler.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "stage_apppackage_inc_filter")
public class PackageEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "day")
    private String day;
    @Column(name = "APPPACKAGE")
    private String packageName;
    @Column(name = "APPNAME_ZH")
    private String appName;
    @Column(name = "DAU")
    private Float dau;
    @Column(name = "APPAU")
    private Integer au;

    public PackageEntity() {
    }

    public PackageEntity(Integer id, String day, String packageName, String appName, Float dau, Integer au) {
        this.id = id;
        this.day = day;
        this.packageName = packageName;
        this.appName = appName;
        this.dau = dau;
        this.au = au;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Float getDau() {
        return dau;
    }

    public void setDau(Float dau) {
        this.dau = dau;
    }

    public Integer getAu() {
        return au;
    }

    public void setAu(Integer au) {
        this.au = au;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageEntity that = (PackageEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(day, that.day) &&
                Objects.equals(packageName, that.packageName) &&
                Objects.equals(appName, that.appName) &&
                Objects.equals(dau, that.dau) &&
                Objects.equals(au, that.au);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, day, packageName, appName, dau, au);
    }

    @Override
    public String toString() {
        return "PackageEntity{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", packageName='" + packageName + '\'' +
                ", appName='" + appName + '\'' +
                ", dau=" + dau +
                ", au=" + au +
                '}';
    }
}
