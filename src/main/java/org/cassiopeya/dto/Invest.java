package org.cassiopeya.dto;


import java.util.Date;

public class Invest {
    private int investId;
    private int userId;
    private int ideaId;
    private int investment;
    private Date investDate;
    private String userLogin;


    public int getInvestId() {
        return investId;
    }

    public void setInvest_Id(int investId) {
        this.investId = investId;
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

    public Date getInvestDate() {
        return investDate;
    }

    public int getInvestment() {
        return investment;
    }

    public void setInvestment(int investment) {
        this.investment = investment;
    }

    public void setInvestDate(Date investDate) {
        this.investDate = investDate;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Invest() {
    }
    public Invest(int investId, int userId, int ideaId, int investment, Date investDate){
        this.investId = investId;
        this.userId = userId;
        this.ideaId = ideaId;
        this.investment = investment;
        this.investDate = investDate;

    }
}
