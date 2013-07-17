package org.cassiopeya.dao.mysql;

import org.cassiopeya.dao.ConnectionDataBaseFactory;
import org.cassiopeya.dao.UserDao;
import org.cassiopeya.dto.User;

import java.sql.*;

public class MysqlUserDao implements UserDao {
    @Override

    public boolean isUserByLogin (String login) {

        boolean isUserLogin = false;
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            s = con.createStatement();
            String sql = "SELECT * FROM users " +
                    "WHERE user_login ='" + login + "'";
             rs = s.executeQuery(sql);

            if (rs.next()) {
                isUserLogin = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            try {
                if (rs!=null )rs.close();
                if (s!=null)s.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }

        return isUserLogin;
    }


    public User createUser(String login, String password, String email) {

         User user = null;
         Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            con = ConnectionDataBaseFactory.getConnection();
             s = con.createStatement();
            String sql = "INSERT INTO users"+
                    " (user_login, password, email)" +
                    " VALUES" +
                    "('" + login +
                    "','" + password +"','" +
                    email +"')";
            int i = s.executeUpdate(sql);
            if (i==1){
                sql = "SELECT user_id FROM users " +
                        "WHERE user_login ='" + login + "'" +
                        " AND password ='" + password + "'";
                 rs = s.executeQuery(sql);
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    user = new User(userId, login, password, email);
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            try {
                if (rs!=null )rs.close();
                if (s!=null)s.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }


        return user;
    }
    public User regUser(String login, String password){
       User user = null;
       ResultSet rs = null;
        Statement s = null;
        Connection con = null;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            s =  con.createStatement();
            String sql = "SELECT * FROM users " +
                    "WHERE user_login ='" + login + "'" +
                    " AND password ='" + password + "'";
            rs = s.executeQuery(sql);
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String email = rs.getString("email");
                user = new User(userId,login, password, email );
            }

        }    catch (SQLException e) {
                e.printStackTrace();
            } finally {
            try {
                if (rs!=null )rs.close();
                if (s!=null)s.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }

        return user;
    }
    public String getUserLogin (int userId) {

        String login = null;
        ResultSet rs = null;
        Connection con = null;
        Statement s = null;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            s = con.createStatement();
            String sql = "SELECT user_login FROM users " +
                    "WHERE user_Id ='" + userId + "'";
            rs = s.executeQuery(sql);

            if (rs.next()) {
                login = rs.getString("user_login");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null )rs.close();
                if (s!=null)s.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }

        return login;
    }
}