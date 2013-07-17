package org.cassiopeya.dao.mysql;

import org.cassiopeya.dao.CategoryDao;
import org.cassiopeya.dao.ConnectionDataBaseFactory;
import org.cassiopeya.dto.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class MysqlCategoryDao implements CategoryDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlCategoryDao.class);

    @Override
    public Map getCategories() {
        LOGGER.debug("getCategories debug");
        Map<String, String> categories = new HashMap();
           ResultSet rs = null;
           Statement s = null;
           Connection con = null;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            s = con.createStatement();
            String sql = "SELECT category_Id, category_name FROM categories " +" ";
            rs = s.executeQuery(sql);
            while (rs.next()) {
                categories.put(rs.getString(1), rs.getString(2));
            }
        }
        catch (SQLException e) {
            LOGGER.error("Get Category exception", e );
        }
        finally {
          try {
            if (rs!=null )rs.close();
            if (s!=null)s.close();
            if (con!=null)con.close();
          }catch (SQLException e){
            LOGGER.error("SQLException", e);
          }
        }

        return categories;
    }


    public Category createCategory(String categoryName) {

           Category category = null;


        try {
            Connection con = ConnectionDataBaseFactory.getConnection();
            Statement s = con.createStatement();
            String sql = "INSERT INTO categories"+
                    " (category_name)" +
                    " VALUES" +
                    "('" + categoryName + "')";
            int i = s.executeUpdate(sql);
            if (i==1){
                sql = "SELECT category_id FROM categories " +
                        "WHERE category_name ='" + categoryName + "'";
                ResultSet rs = s.executeQuery(sql);
                if (rs.next()) {
                    int categoryId = rs.getInt("category_id");
                    category = new Category(categoryId, categoryName);
                }
                rs.close();
                s.close();
                con.close();
                                  }
    }   catch (SQLException e) {
            e.printStackTrace();
        }
         return category;
 }

      public Category getCategory(int categoryId) {

          Category category = null;


          try {
              Connection con = ConnectionDataBaseFactory.getConnection();
              Statement s = con.createStatement();
              String sql = "SELECT category_name FROM categories " +
                      "WHERE category_id ='" + categoryId + "'";
              ResultSet rs = s.executeQuery(sql);
              if (rs.next()) {
                  String categoryName = rs.getString("category_name");
                  category = new Category(categoryId, categoryName);
              }
              rs.close();
              s.close();
              con.close();
              }  catch (SQLException e) {
              e.printStackTrace();
          }

          return category;
      }
}