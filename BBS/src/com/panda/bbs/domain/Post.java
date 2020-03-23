package com.panda.bbs.domain;

import java.util.Date;
import java.util.Objects;

public class Post {
    private String postId; // 帖子ID
    private String authorId; // 作者ID
    private String dir; // 分类
    private String postTitle; // 帖子标题
    private String postContent; // 帖子内容
    private String createTime; // 帖子创建时间
    private String updateTime; // 帖子更新时间
    private int likeCount; // 点赞数
    private int commentCount; // 评论数
    private int postViewCount; // 阅读量

    public Post() {
    }

    public Post(String postId, String authorId, String dir, String postTitle, String postContent, String createTime, String updateTime, int likeCount, int commentCount, int postViewCount) {
        this.postId = postId;
        this.authorId = authorId;
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
        Post post = (Post) o;
        return likeCount == post.likeCount &&
                commentCount == post.commentCount &&
                postViewCount == post.postViewCount &&
                Objects.equals(postId, post.postId) &&
                Objects.equals(authorId, post.authorId) &&
                Objects.equals(dir, post.dir) &&
                Objects.equals(postTitle, post.postTitle) &&
                Objects.equals(postContent, post.postContent) &&
                Objects.equals(createTime, post.createTime) &&
                Objects.equals(updateTime, post.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, authorId, dir, postTitle, postContent, createTime, updateTime, likeCount, commentCount, postViewCount);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", authorId='" + authorId + '\'' +
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
