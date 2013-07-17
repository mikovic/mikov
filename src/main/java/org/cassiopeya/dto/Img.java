package org.cassiopeya.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 27.05.13
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class Img {
    private int imgId;
    private int userId;
    private int ideaId;

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public int getImgId() {
        return imgId;
    }

    public int getUserId() {
        return userId;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public Img() {
    }
}
