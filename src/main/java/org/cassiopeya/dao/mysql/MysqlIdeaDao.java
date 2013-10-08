package org.cassiopeya.dao.mysql;

import org.cassiopeya.dao.ConnectionDataBaseFactory;
import org.cassiopeya.dao.IdeaDao;
import org.cassiopeya.dto.Idea;
import org.cassiopeya.dto.Invest;
import org.cassiopeya.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;


public class MysqlIdeaDao implements IdeaDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlIdeaDao.class);

    public Idea createIdea (int userId, int categoryId, String topicIdea, String descIdea, Date createDate,
                            int budget) {

        Idea idea = null;
        ResultSet rs = null;
        Statement s = null;
        Connection con = null;
        java.sql.Date sqlCreateDate=new java.sql.Date(createDate.getTime());

        try {
            con = ConnectionDataBaseFactory.getConnection();
            s = con.createStatement();
            String sql = "INSERT INTO ideas (user_id, category_id, topic_idea, desc_idea, create_date, budget) " +
                    "VALUES ('" +userId  + "','" + categoryId + "','" + topicIdea +"','" + descIdea + "','" +
                    sqlCreateDate  + "','" + budget +"')";
            int i = s.executeUpdate(sql);
            if (i==1){
                sql = "SELECT idea_id FROM ideas WHERE user_id ='" + userId +
                        "' ORDER BY idea_id DESC LIMIT 0,1";
                rs = s.executeQuery(sql);
                if (rs.next()) {
                    int ideaId = rs.getInt(1);
                    idea = new Idea(ideaId, userId, categoryId, topicIdea, descIdea, createDate, budget);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs!=null )rs.close();
                if (s!=null)s.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }
        return idea;
    }


    public Idea getIdeaInId(int ideaId) {
        Idea idea = null;
        ResultSet rs = null;
        Statement s = null;
        Connection con = null;

        try {
            con = ConnectionDataBaseFactory.getConnection();
            s = con.createStatement();
            String sql = "SELECT ideas.*,users.user_login FROM ideas INNER JOIN users ON  ideas.user_id = users.user_id " +
                    "WHERE idea_id ='" + ideaId + "'";
            rs = s.executeQuery(sql);
            if (rs.next()) {
                idea = new Idea();
                idea.setIdeaId(rs.getInt("idea_id"));
                idea.setUserId(rs.getInt("user_id"));
                idea.setCategoryId(rs.getInt("category_id"));
                idea.setTopicIdea(rs.getString("topic_idea"));
                idea.setDescIdea(rs.getString("desc_idea"));
                java.util.Date createDate = new java.util.Date(rs.getDate("create_date").getTime());
                idea.setCreateDate(createDate);
                java.sql.Date sqlUpdateDate = rs.getDate("update_date");
                if (sqlUpdateDate!= null){
                    java.util.Date updateDate = new java.util.Date(sqlUpdateDate.getTime());
                    idea.setUpdateDate(updateDate);
                } else {
                    idea.setUpdateDate(null);
                }
                java.sql.Date sqlCloseDate = rs.getDate("close_date");
                if (sqlCloseDate!= null){
                    java.util.Date closeDate = new java.util.Date(sqlCloseDate.getTime());
                }else {
                    idea.setCloseDate(null);
                }
                idea.setBudget(rs.getInt("budget"));
                idea.setUserLogin(rs.getString("user_login"));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs!=null )rs.close();
                if (s!=null)s.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }

        return idea;
    }


    public ArrayList getIdeasInCategory(int categoryId, int page, int countIdeasOnPage) {
        ArrayList ideas = new ArrayList();
        int start = (page-1) * countIdeasOnPage;
        try {
            Connection con = ConnectionDataBaseFactory.getConnection();
            Statement s = con.createStatement();
            String sql = "SELECT ideas.*,users.user_login FROM ideas INNER JOIN users ON ideas.user_id = users.user_id " +
                    "WHERE category_id ='" + categoryId +"'  ORDER BY idea_id DESC"+" LIMIT "+ start +","+ countIdeasOnPage;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Idea idea = new Idea();
                idea.setIdeaId(rs.getInt("idea_id"));
                idea.setUserId(rs.getInt("user_id"));
                idea.setCategoryId(rs.getInt("category_id"));
                idea.setTopicIdea(rs.getString("topic_idea"));
                idea.setDescIdea(rs.getString("desc_idea"));
                java.util.Date createDate = new java.util.Date(rs.getDate("create_date").getTime());
                idea.setCreateDate(createDate);
                java.sql.Date sqlUpdateDate = rs.getDate("update_date");
                if (sqlUpdateDate!= null){
                    java.util.Date updateDate = new java.util.Date(sqlUpdateDate.getTime());
                    idea.setUpdateDate(updateDate);
                } else {
                    idea.setUpdateDate(null);
                }
                java.sql.Date sqlCloseDate = rs.getDate("close_date");
                if (sqlCloseDate!= null){
                    java.util.Date closeDate = new java.util.Date(sqlCloseDate.getTime());
                }else {
                    idea.setCloseDate(null);
                }

                idea.setBudget(rs.getInt("budget"));
                idea.setUserLogin(rs.getString("user_login"));
                ideas.add(idea);


            }
            rs.close();
            s.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ideas;
    }

    public ArrayList getIdeasInUserId(int userId) {
        ArrayList ideasInUserId = new ArrayList();


        try {
            Connection con = ConnectionDataBaseFactory.getConnection();
            Statement s = con.createStatement();
            String sql =  "SELECT * FROM ideas " +
                    "WHERE user_id ='" + userId +"'";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Idea idea = new Idea();
                idea.setIdeaId(rs.getInt("idea_id"));
                idea.setUserId(rs.getInt("user_id"));
                idea.setCategoryId(rs.getInt("category_id"));
                idea.setTopicIdea(rs.getString("topic_idea"));
                idea.setDescIdea(rs.getString("desc_idea"));
                java.util.Date createDate = new java.util.Date(rs.getDate("create_date").getTime());
                idea.setCreateDate(createDate);
                java.sql.Date sqlUpdateDate = rs.getDate("update_date");
                if (sqlUpdateDate!= null){
                    java.util.Date updateDate = new java.util.Date(sqlUpdateDate.getTime());
                    idea.setUpdateDate(updateDate);
                } else {
                    idea.setUpdateDate(null);
                }
                java.sql.Date sqlCloseDate = rs.getDate("close_date");
                if (sqlCloseDate!= null){
                    java.util.Date closeDate = new java.util.Date(sqlCloseDate.getTime());
                }else {
                    idea.setCloseDate(null);
                }

                idea.setBudget(rs.getInt("budget"));
                ideasInUserId.add(idea);
            }
            rs.close();
            s.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ideasInUserId;
    }
    public int getPagesInCategory(int categoryId) {
        int countsPagesInCategory = 0;

        ResultSet rs = null;
        Statement s = null;
        Connection con = null;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            s = con.createStatement();
            String sql = "SELECT  COUNT(category_id) FROM ideas WHERE category_id = '" + categoryId+"'";
            rs = s.executeQuery(sql);
            if (rs.next()){
                int countsIdeasInCategory = rs.getInt(1);
                countsPagesInCategory = (countsIdeasInCategory%3.==0)?countsIdeasInCategory /3:countsIdeasInCategory/3+1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs!=null )rs.close();
                if (s!=null)s.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }
        return countsPagesInCategory;
    }

    @Override
    public Idea getInfoInvestInIdea(int ideaId) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Idea infoInvestInIdea = new Idea();
        try {
            con = ConnectionDataBaseFactory.getConnection();
            String sql = "SELECT SUM(investment),AVG(investment),MAX(investment) FROM invests WHERE idea_id ='" + ideaId+"'";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                infoInvestInIdea.setSumInvestInIdea(rs.getInt(1));
                int avg = Math.round(rs.getFloat(2));
                infoInvestInIdea.setAvgInvestInIdea(avg);
                infoInvestInIdea.setMaxInvestInIdea(rs.getInt(3));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null )rs.close();
                if (pst!=null)pst.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }
        return infoInvestInIdea ;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Idea getIdeaUserOnPage(int userId, int page) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Idea ideaUserOnPage = new Idea();
        int start = page - 1;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            String sql = "SELECT * FROM ideas WHERE user_id='" + userId +"' ORDER BY idea_id DESC"+" LIMIT "+ start +", 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()){
                ideaUserOnPage.setIdeaId(rs.getInt("idea_id"));
                ideaUserOnPage.setUserId(rs.getInt("user_id"));
                ideaUserOnPage.setCategoryId(rs.getInt("category_id"));
                ideaUserOnPage.setTopicIdea(rs.getString("topic_idea"));
                ideaUserOnPage.setDescIdea(rs.getString("desc_idea"));
                java.util.Date createDate = new java.util.Date(rs.getDate("create_date").getTime());
                ideaUserOnPage.setCreateDate(createDate);
                java.sql.Date sqlUpdateDate = rs.getDate("update_date");
                if (sqlUpdateDate!= null){
                    java.util.Date updateDate = new java.util.Date(sqlUpdateDate.getTime());
                    ideaUserOnPage.setUpdateDate(updateDate);
                } else {
                    ideaUserOnPage.setUpdateDate(null);
                }
                java.sql.Date sqlCloseDate = rs.getDate("close_date");
                if (sqlCloseDate!= null){
                    java.util.Date closeDate = new java.util.Date(sqlCloseDate.getTime());
                }else {
                    ideaUserOnPage.setCloseDate(null);
                }
                ideaUserOnPage.setBudget(rs.getInt("budget"));

            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null )rs.close();
                if (pst!=null)pst.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }
        return ideaUserOnPage;
    }

    @Override
    public int getCountIdeasUser(int userId) {
        ResultSet rs = null;
        Statement s = null;
        Connection con = null;
        int countIdeasUser = 0;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            s = con.createStatement();
            String sql = "SELECT  COUNT(user_id) FROM ideas WHERE user_id = '" + userId+"'";
            rs = s.executeQuery(sql);
            if (rs.next()){
                countIdeasUser = rs.getInt(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs!=null )rs.close();
                if (s!=null)s.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }
        return countIdeasUser;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Idea getEditTextIdea(int ideaId, int categoryId, String topicIdea, String descIdea, int budget) {
        Idea ideaEdit = null;
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionDataBaseFactory.getConnection();
            String query = "UPDATE ideas SET category_id=?,topic_idea=?,desc_idea=?,budget=? WHERE idea_id="+ideaId;
            pst = con.prepareStatement(query);
            pst.setInt(1, categoryId);
            pst.setString(2, topicIdea);
            pst.setString(3, descIdea);
            pst.setInt(4, budget);
            int i = pst.executeUpdate();
            if (i==1) {
                ideaEdit = new Idea();
                ideaEdit.setIdeaId(ideaId);
                ideaEdit.setCategoryId(categoryId);
                ideaEdit.setTopicIdea(topicIdea);
                ideaEdit.setDescIdea(descIdea);
                ideaEdit.setBudget(budget);

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ideaEdit;
    }
}
