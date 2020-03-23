package com.panda.bbs.domain;

import java.util.Objects;

public class Comment {
    private String commentId; // 评论ID
    private String commentUserId; // 用户ID
    private String postId; // 帖子ID
    private String commentContent; // 评论内容
    private String commentTime; // 评论时间
    private int thumbUpNum;//评论点赞数

    public Comment() {
    }

    public Comment(String commentId, String commentUserId, String postId, String commentContent, String commentTime, int thumbUpNum) {
        this.commentId = commentId;
        this.commentUserId = commentUserId;
        this.postId = postId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.thumbUpNum = thumbUpNum;

    }

    public Comment(String commentUserId, String postId, String commentContent, String commentTime, int thumbUpNum) {
        this.commentUserId = commentUserId;
        this.postId = postId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.thumbUpNum = thumbUpNum;

    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public int getThumbUpNum() { return thumbUpNum; }

    public void setThumbUpNum(int thumbUpNum) { this.thumbUpNum = thumbUpNum; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId &&
                commentUserId == comment.commentUserId &&
                postId == comment.postId &&
                thumbUpNum == comment.thumbUpNum &&

                Objects.equals(commentContent, comment.commentContent) &&
                Objects.equals(commentTime, comment.commentTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commentId, commentUserId, postId, commentContent, commentTime, thumbUpNum);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + commentUserId +
                ", postId=" + postId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", thumbUpNum=" + thumbUpNum +
                '}';
    }
}
