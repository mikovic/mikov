package org.cassiopeya.dao.mysql;

import org.cassiopeya.dao.UserDao;
import org.cassiopeya.dto.User;

import java.sql.*;

public class MysqlUserDao implements UserDao {
    @Override

    public boolean isUserByLogin (String login) {

        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try { con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cassiopeya", "root", "") ;
              s = con.createStatement();
            String sql = "SELECT user_login FROM users " +
                    "WHERE user_login ='" + login + "'";
              rs = s.executeQuery(sql);

            if (rs.next()) {
                return true;
            } else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                s.close();
                con.close();

            }   catch (Exception e) {
                e.printStackTrace();
            }
        }

            return false;
    }


    public User createUser(String login, String password, String email) {

        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cassiopeya", "root", "");
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
                    return new User(userId, login, password, email);
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
    public User regUser(String login, String password){
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cassiopeya", "root", "");
            s =  con.createStatement();
            String sql = "SELECT * FROM users " +
                    "WHERE user_login ='" + login + "'" +
                    " AND password ='" + password + "'";
            rs = s.executeQuery(sql);
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String email = rs.getString("email");
                User user = new User(userId,login, password, email );
                return user;
            }  else  return null;
        }    catch (SQLException e) {
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