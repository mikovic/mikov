package org.cassiopeya.dao.mysql;

import org.cassiopeya.dao.IdeaDao;
import org.cassiopeya.dto.Idea;
import org.cassiopeya.dto.User;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;


public class MysqlIdeaDao implements IdeaDao {

    Connection con = null;
    Statement s = null;
    ResultSet rs = null;
    ServletContext context;
    public Idea createIdea(int userId,String categoryId, String topicIdea, String descIdea, String createDate, String updateDate,
                           String closeDate, String budget) {


        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cassiopeya", "root", "");
            s = con.createStatement();
            String sql = "INSERT INTO idea"+
                    " (user_id, categoryId, topic_idea, desc_idea, create_date, update_date, close_date, budget)" +
                    " VALUES" +
                    "('" +userId  + "','" + categoryId +
                    "','" + topicIdea +"','" + descIdea + "','" + createDate  + "','" +
                    updateDate  +"','" + closeDate  + "','" + budget +"')";
            int i = s.executeUpdate(sql);
            if (i==1){
                sql = "SELECT idea_id FROM users " +
                        "WHERE user_id ='" + userId + "'" +
                        " AND create_date ='" + createDate + "'";
                rs = s.executeQuery(sql);
                if (rs.next()) {
                    int ideaId = rs.getInt("idea_id");
                    return new Idea(ideaId, userId, categoryId, topicIdea, descIdea, createDate, updateDate, closeDate, budget);
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            try {
                rs.close();
                s.close();
                con.close();

            }   catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

     public ArrayList getIdeasInCategory(String categoryId) {
        ArrayList ideas = new ArrayList();

         try {
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cassiopeya", "root", "");
             s = con.createStatement();
             String sql =  "SELECT * FROM users " +
                     "WHERE category_id ='" + categoryId;
             ResultSet rs = s.executeQuery(sql);
             while (rs.next()) {
                 Idea idea = new Idea();
                 idea.setIdeaId(rs.getInt(1));
                 idea.setUserId(rs.getInt(2));
                 idea.setCategoryId(rs.getString(3));
                 idea.setTopicIdea(rs.getString(4));
                 idea.setDescIdea(rs.getString(5));
                 idea.setCreateDate(rs.getString(6));
                 idea.setUpdateDate(rs.getString(7));
                 idea.setCloseDate(rs.getString(8));
                 idea.setBudget(rs.getString(9));
                 ideas.add(idea);
             }
             return ideas;
     } catch (SQLException e) {
             e.printStackTrace();
         }  finally {
             try {
                 rs.close();
                 s.close();
                 con.close();

             }   catch (Exception e) {
                 e.printStackTrace();
             }
         }
         return null;
     }
}