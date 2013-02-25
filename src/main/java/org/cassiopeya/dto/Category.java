package org.cassiopeya.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 18.02.13
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */
public class  Category {
    int categoryId;
    String category;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Category(int categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }
}
