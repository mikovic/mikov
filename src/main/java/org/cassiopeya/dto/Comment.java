package org.cassiopeya.dto;


import java.util.Date;

public class Comment {
    int commentId;
    int userId;
    int ideaId;
    String text;
    Date createDate;
    private String userLogin;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }
    public String getUserLogin() {

        return userLogin;
    }
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Comment() {

    }

    public Comment(int commentId, int userId, int ideaId, String text, Date createDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.ideaId = ideaId;
        this.text = text;
        this.createDate = createDate;
    }
}
