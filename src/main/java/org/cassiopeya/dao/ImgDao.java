package org.cassiopeya.dao;

import org.cassiopeya.dto.Img;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public interface ImgDao {
    int addImgToIdea(int userId, String fileName, String filePath) throws SQLException;
    byte[] getImgFromIdea(int imgId);
    LinkedList<Integer> createIdImgUser(int ideaId,int userId ,ArrayList<String> colRelativePathImg);
    String getPathImgInId (int imgId);
    LinkedList<Integer> getIdImgUser(int ideaId);
    boolean isPathImg(String pathImg);
}
