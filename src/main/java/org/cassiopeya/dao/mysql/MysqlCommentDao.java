package org.cassiopeya.dao.mysql;

import org.cassiopeya.dao.CommentDao;
import org.cassiopeya.dao.ConnectionDataBaseFactory;
import org.cassiopeya.dto.Comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


public class MysqlCommentDao implements CommentDao {
    @Override
    public Comment createComment( int userId, int ideaId, String text, Date createDate) {

            Comment comment = null;
            ResultSet rs = null;
            Statement s = null;
            Connection con = null;
        java.sql.Date sqlCreateDate=new java.sql.Date(createDate.getTime());

            try {
                con = ConnectionDataBaseFactory.getConnection();
                s = con.createStatement();
                String sql = "INSERT INTO comments (user_id, idea_id, text, create_date) " +
                        "VALUES ('" +userId  + "','" + ideaId + "','" + text+"','" + sqlCreateDate +"')";
                int i = s.executeUpdate(sql);
                if (i==1){
                    sql = "SELECT comment_id FROM comments WHERE user_id='" + userId + "'" +
                            " ORDER BY comment_id DESC LIMIT 0,1";
                    rs = s.executeQuery(sql);
                    if (rs.next()) {
                        int commentId = rs.getInt(1);
                        comment = new Comment(commentId,userId, ideaId, text, createDate );
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
        return comment;
    }

    public ArrayList getCommentsInIdeId(int ideaId) {
        ArrayList comments = new ArrayList();
        Comment comment = null;
        ResultSet rs = null;
        Statement s = null;
        Connection con = null;

        try {
            con = ConnectionDataBaseFactory.getConnection();
            s = con.createStatement();
            String sql = "SELECT comments.*,users.user_login FROM comments INNER JOIN users ON  comments.user_id = users.user_id " +
                    "WHERE idea_id ='" + ideaId + "'";
            rs = s.executeQuery(sql);
            while (rs.next()) {
                comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setIdeaId(rs.getInt("idea_id"));
                comment.setText(rs.getString("text"));
                java.sql.Date sqlCreateDate = rs.getDate("create_date");
                java.util.Date createDate = new java.util.Date(sqlCreateDate.getTime());
                comment.setCreateDate(createDate);
                comment.setUserLogin(rs.getString("user_login"));
                comments.add(comment);
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

        return comments;
    }
}
