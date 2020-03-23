package com.group3.bookstore.pojo;

import java.util.Objects;

public class ManagerAdmin {
    private String userAccount;
    private String password;

    public ManagerAdmin() {
    }

    public ManagerAdmin(String userAccount, String password) {
        this.userAccount = userAccount;
        this.password = password;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerAdmin that = (ManagerAdmin) o;
        return Objects.equals(userAccount, that.userAccount) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userAccount, password);
    }

    @Override
    public String toString() {
        return "ManagerAdmin{" +
                "userAccount='" + userAccount + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
