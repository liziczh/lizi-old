package com.group3.bookstore.pojo;

import java.util.Objects;

public class User {
    private String userId; // 用户ID#
//    @NotNull(message = "用户名不能为空")
    private String username; // 用户名
//    @NotNull(message = "密码不能为空")
    private String password; // 密码
//    @NotNull(message = "邮箱不能为空")
//    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")
    private String email; // 邮箱
//    @NotNull(message = "手机号不能为空")
//    @Pattern(regexp = "^1([3568][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$")
    private String phoneNumber; // 手机号
    private String avatar; // 头像
    private String gender; // 性别
    private String joinTime; // 加入时间
    private String bio; // 个人介绍
    private String token; // Token

    public User() {
    }

    public User(String userId, String username, String password, String email, String phoneNumber, String avatar, String gender, String joinTime, String bio, String token) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.gender = gender;
        this.joinTime = joinTime;
        this.bio = bio;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(joinTime, user.joinTime) &&
                Objects.equals(bio, user.bio) &&
                Objects.equals(token, user.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, email, phoneNumber, avatar, gender, joinTime, bio, token);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender='" + gender + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", bio='" + bio + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
