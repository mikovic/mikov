package org.cassiopeya.dto;

public class Idea {
    int ideaId;
    int userId;
    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    String categoryId;
    String topicIdea;
    String descIdea;



    String createDate;
    String updateDate;
    String closeDate;
    String budget;


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
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public Idea() {
    }

    public Idea(int ideaId, int userId, String categoryId,String topicIdea, String descIdea, String createDate, String updateDate,
                String closeDate, String budget) {
        this.ideaId = ideaId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.topicIdea = topicIdea;
        this.descIdea = descIdea;
        this.createDate = createDate;

        this.updateDate = updateDate;
        this.closeDate = closeDate;
        this.budget = budget;


    }
}
