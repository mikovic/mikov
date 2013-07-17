package org.cassiopeya.dao;

import org.cassiopeya.dto.Comment;

import java.util.ArrayList;
import java.util.Date;


public interface CommentDao {
    Comment createComment ( int userId, int ideaId, String text, Date createDate );
    ArrayList getCommentsInIdeId(int ideaId);
}
