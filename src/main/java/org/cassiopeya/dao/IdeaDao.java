package org.cassiopeya.dao;

import org.cassiopeya.dto.Idea;

import java.util.ArrayList;
import java.util.Date;

public interface IdeaDao {
    Idea createIdea ( int userId, int categoryId, String topicIdea, String descIdea, Date createDate,
                       int budget);
     ArrayList getIdeasInCategory(int categoryId, int page, int countIdeasOnPage);
     ArrayList getIdeasInUserId(int userId);
     Idea getIdeaInId(int ideaId);
     int getPagesInCategory(int category);
    Idea getInfoInvestInIdea(int ideaId);
    Idea getIdeaUserOnPage(int userId, int page);
    int getCountIdeasUser(int userId);
}
