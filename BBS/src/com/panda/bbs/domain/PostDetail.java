package com.panda.bbs.domain;

import java.util.Objects;

public class PostDetail {
    private String postId; // 帖子ID
    private String authorId; // 作者ID
    private String authorName; // 作者
    private String authorAvatar; // 作者头像
    private String dir; // 分类
    private String postTitle; // 帖子标题
    private String postContent; // 帖子内容
    private String createTime; // 帖子创建时间
    private String updateTime; // 帖子更新时间
    private int likeCount; // 点赞数
    private int commentCount; // 评论数
    private int postViewCount; // 阅读量

    public PostDetail() {

    }

    public PostDetail(Post post, User author) {
        this.postId = post.getPostId();
        this.authorId = post.getAuthorId();
        this.authorName = author.getUsername();
        this.authorAvatar = author.getAvatar();
        this.dir = post.getDir();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.createTime = post.getCreateTime();
        this.updateTime = post.getUpdateTime();
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.postViewCount = post.getPostViewCount();
    }

    public PostDetail(String postId, String authorId, String authorName, String authorAvatar, String dir, String postTitle, String postContent, String createTime, String updateTime, int likeCount, int commentCount, int postViewCount) {
        this.postId = postId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorAvatar = authorAvatar;
        this.dir = dir;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.postViewCount = postViewCount;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getPostViewCount() {
        return postViewCount;
    }

    public void setPostViewCount(int postViewCount) {
        this.postViewCount = postViewCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDetail that = (PostDetail) o;
        return likeCount == that.likeCount &&
                commentCount == that.commentCount &&
                postViewCount == that.postViewCount &&
                Objects.equals(postId, that.postId) &&
                Objects.equals(authorId, that.authorId) &&
                Objects.equals(authorName, that.authorName) &&
                Objects.equals(authorAvatar, that.authorAvatar) &&
                Objects.equals(dir, that.dir) &&
                Objects.equals(postTitle, that.postTitle) &&
                Objects.equals(postContent, that.postContent) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, authorId, authorName, authorAvatar, dir, postTitle, postContent, createTime, updateTime, likeCount, commentCount, postViewCount);
    }

    @Override
    public String toString() {
        return "PostDetail{" +
                "postId='" + postId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorAvatar='" + authorAvatar + '\'' +
                ", dir='" + dir + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", postViewCount=" + postViewCount +
                '}';
    }
}
