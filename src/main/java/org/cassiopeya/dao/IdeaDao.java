package org.cassiopeya.dao;

import org.cassiopeya.dto.Idea;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 18.02.13
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public interface IdeaDao {
    Idea createIdea ( int userId, String categoryId, String topicIdea, String descIdea, String createDate,
                      String updateDate, String closeDate, String budget);
     ArrayList getIdeasInCategory(String categoryId);
}
