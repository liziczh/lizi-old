package com.qm.crawler.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "stage_apppackage_inc_grab")
public class AppEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "source")
    private String source;
    @Id
    @Column(name = "apppackage")
    private String packageName;
    @Column(name = "appname_grab")
    private String appNameGrab;
    @Column(name = "appname")
    private String appName;
    @Column(name = "category")
    private String category;
    @Column(name = "company")
    private String company;
    @Column(name = "ICON")
    private String icon;

    public AppEntity() {
    }

    public AppEntity(String source, String packageName, String appNameGrab, String appName, String category, String company, String icon) {
        this.source = source;
        this.packageName = packageName;
        this.appNameGrab = appNameGrab;
        this.appName = appName;
        this.category = category;
        this.company = company;
        this.icon = icon;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppNameGrab() {
        return appNameGrab;
    }

    public void setAppNameGrab(String appNameGrab) {
        this.appNameGrab = appNameGrab;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppEntity appEntity = (AppEntity) o;
        return Objects.equals(source, appEntity.source) &&
                Objects.equals(packageName, appEntity.packageName) &&
                Objects.equals(appNameGrab, appEntity.appNameGrab) &&
                Objects.equals(appName, appEntity.appName) &&
                Objects.equals(category, appEntity.category) &&
                Objects.equals(company, appEntity.company) &&
                Objects.equals(icon, appEntity.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, packageName, appNameGrab, appName, category, company, icon);
    }

    @Override
    public String toString() {
        return "AppEntity{" +
                "source='" + source + '\'' +
                ", packageName='" + packageName + '\'' +
                ", appNameGrab='" + appNameGrab + '\'' +
                ", appName='" + appName + '\'' +
                ", category='" + category + '\'' +
                ", company='" + company + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
