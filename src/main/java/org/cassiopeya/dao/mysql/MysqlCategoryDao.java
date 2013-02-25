package org.cassiopeya.dao.mysql;

import org.cassiopeya.dao.CategoryDao;
import org.cassiopeya.dto.Category;
import org.cassiopeya.dto.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;


public class MysqlCategoryDao implements CategoryDao {
    @Override
    public Map categories() {
        Connection con =
                null;
        Statement s = null;
        ResultSet rs = null;
        HashMap categories = new HashMap();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cassiopeya", "root", "");
            s = con.createStatement();
            String sql = "SELECT category_Id, category FROM categories " +" ";
            rs = s.executeQuery(sql);
            while (rs.next()) {
                categories.put(rs.getString(1), rs.getString(2));
            }
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
        return categories;
 }
    public Category createCategory(String category) {
        Connection con =
                null;
        Statement s = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cassiopeya", "root", "");
            s = con.createStatement();
            String sql = "INSERT INTO categories"+
                    " (category)" +
                    " VALUES" +
                    "('" + category + "')";
            int i = s.executeUpdate(sql);
            if (i==1){
                sql = "SELECT category_id FROM categories " +
                        "WHERE category ='" + category + "'";
                rs = s.executeQuery(sql);
                if (rs.next()) {
                    int categoryId = rs.getInt("category_id");
                    return new Category(categoryId, category);
                }else {
                    return null;
                }
            }
    }   catch (SQLException e) {
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