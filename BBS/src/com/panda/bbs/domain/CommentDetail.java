package com.panda.bbs.domain;

import java.util.Objects;

public class CommentDetail {
    private String postId;//评论得文章ID
    private String commentUserId;//评论者ID
    private String commentUserName;//评论者名称
    private String commentUserAvatar;//评论者头像
    private String commmentId;//评论ID
    private String commentTime;//评论时间
    private String commentContent;//评论内容
    private int thumbUpNum;//评论点赞数

    public CommentDetail() {
    }

    public CommentDetail(String postId, String commentUserId, String commentUserName, String commentUserAvatar, String commmentId, String commentTime, String commentContent, int thumbUpNum) {
        this.postId = postId;
        this.commentUserId = commentUserId;
        this.commentUserName = commentUserName;
        this.commentUserAvatar = commentUserAvatar;
        this.commmentId = commmentId;
        this.commentTime = commentTime;
        this.commentContent = commentContent;
        this.thumbUpNum = thumbUpNum;
    }

    public CommentDetail(Comment comment, User commentUser, String postId) {
        this.commentUserId = comment.getCommentUserId();
        this.commentUserName = commentUser.getUsername();
        this.commentUserAvatar = commentUser.getAvatar();
        this.commmentId = comment.getCommentId();
        this.commentTime = comment.getCommentTime();
        this.commentContent = comment.getCommentContent();
        this.thumbUpNum = comment.getThumbUpNum();
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentUserAvatar() {
        return commentUserAvatar;
    }

    public void setCommentUserAvatar(String commentUserAvatar) {
        this.commentUserAvatar = commentUserAvatar;
    }

    public String getCommmentId() {
        return commmentId;
    }

    public void setCommmentId(String commmentId) {
        this.commmentId = commmentId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getThumbUpNum() {
        return thumbUpNum;
    }

    public void setThumbUpNum(int thumbUpNum) {
        this.thumbUpNum = thumbUpNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDetail that = (CommentDetail) o;
        return thumbUpNum == that.thumbUpNum &&
                Objects.equals(postId, that.postId) &&
                Objects.equals(commentUserId, that.commentUserId) &&
                Objects.equals(commentUserName, that.commentUserName) &&
                Objects.equals(commentUserAvatar, that.commentUserAvatar) &&
                Objects.equals(commmentId, that.commmentId) &&
                Objects.equals(commentTime, that.commentTime) &&
                Objects.equals(commentContent, that.commentContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postId, commentUserId, commentUserName, commentUserAvatar, commmentId, commentTime, commentContent, thumbUpNum);
    }

    @Override
    public String toString() {
        return "CommentDetail{" +
                "postId='" + postId + '\'' +
                ", commentUserId='" + commentUserId + '\'' +
                ", commentUserName='" + commentUserName + '\'' +
                ", commentUserAvatar='" + commentUserAvatar + '\'' +
                ", commmentId='" + commmentId + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", thumbUpNum=" + thumbUpNum +
                '}';
    }
}
