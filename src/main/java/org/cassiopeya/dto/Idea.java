package org.cassiopeya.dto;

import java.util.Date;

public class Idea {
    private int ideaId;
    private int userId;
    private int categoryId;
    private String topicIdea;
    private String descIdea;
    private Date createDate;
    private Date updateDate;
    private Date closeDate;
    private int budget;
    private String userLogin;
    private int sumInvestInIdea;
    private int maxInvestInIdea;
    private int avgInvestInIdea;

    public int getSumInvestInIdea() {
        return sumInvestInIdea;
    }

    public void setSumInvestInIdea(int sumInvestInIdea) {
        this.sumInvestInIdea = sumInvestInIdea;
    }

    public int getMaxInvestInIdea() {

        return maxInvestInIdea;
    }

    public void setMaxInvestInIdea(int maxInvestInIdea) {
        this.maxInvestInIdea = maxInvestInIdea;
    }

    public int getAvgInvestInIdea() {
        return avgInvestInIdea;
    }

    public void setAvgInvestInIdea(int avgInvestInIdea) {
        this.avgInvestInIdea = avgInvestInIdea;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public String getTopicIdea() {
        return topicIdea;
    }

    public void setTopicIdea(String topicIdea) {
        this.topicIdea = topicIdea;
    }

    public String getDescIdea() {
        return descIdea;
    }

    public void setDescIdea(String descIdea) {
        this.descIdea = descIdea;
    }

    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
    public String getUserLogin (){
        return userLogin;
    }
    public void setUserLogin(String userLogin){
        this.userLogin = userLogin;
    }
    public Idea() {
    }

    public Idea(int ideaId, int userId, int categoryId,String topicIdea, String descIdea,
                Date createDate, int budget) {
        this.ideaId = ideaId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.topicIdea = topicIdea;
        this.descIdea = descIdea;
        this.createDate = createDate;
        this.budget = budget;

    }
}
