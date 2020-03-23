package com.panda.bbs.domain;

import java.util.Objects;

public class User {
    private String userId; // 用户ID
    private String username; // 用户名
    private String password; // 密码
    private String email; // 邮箱
    private String phoneNumber; // 手机号
    private String avatar; // 头像
    private String gender; // 性别
    private String joinTime; // 加入时间
    private String location; // 位置
    private String bio; // 个性签名
    private String reward; // 打赏二维码
    private String background; // 背景图

    public User() {
    }

    public User(String userId, String username, String password, String email, String phoneNumber, String avatar, String gender, String joinTime, String location, String bio, String reward, String background) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.gender = gender;
        this.joinTime = joinTime;
        this.location = location;
        this.bio = bio;
        this.reward = reward;
        this.background = background;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
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
                Objects.equals(location, user.location) &&
                Objects.equals(bio, user.bio) &&
                Objects.equals(reward, user.reward) &&
                Objects.equals(background, user.background);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, email, phoneNumber, avatar, gender, joinTime, location, bio, reward, background);
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
                ", location='" + location + '\'' +
                ", bio='" + bio + '\'' +
                ", reward='" + reward + '\'' +
                ", background='" + background + '\'' +
                '}';
    }
}
