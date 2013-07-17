package org.cassiopeya.dao.mysql;

import org.cassiopeya.dao.ConnectionDataBaseFactory;
import org.cassiopeya.dao.ImgDao;
import org.cassiopeya.dto.Idea;
import org.cassiopeya.dto.Img;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class MysqlImgDao implements ImgDao {


    @Override
    public int addImgToIdea( int userId, String fileName, String filePath) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        FileInputStream fin = null;
        int imgId = 0;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            String query = "SELECT  img_Id FROM images WHERE user_id= ? AND file_name= ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            pst.setString(2, fileName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                imgId = rs.getInt(1);
            } else {
                File img = new File(filePath + fileName);
                fin = new FileInputStream(img);
                query = "INSERT INTO images( user_id, file_name, img) VALUES(?,?,?)";
                pst = con.prepareStatement(query);
                pst.setInt(1, userId);
                pst.setString(2, fileName);
                pst.setBinaryStream(3, fin, (int) img.length());
                int i = pst.executeUpdate();
                if (i==1){
                    pst = con.prepareStatement("SELECT img_id FROM images WHERE user_id= ? AND file_name= ? ");
                    pst.setInt(1, userId);
                    pst.setString(2, fileName);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        imgId = rs.getInt(1);

                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
                if (fin != null) {
                    fin.close();
                }

            } catch (IOException e) {
                e.printStackTrace();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return imgId;
    }
    @Override
    public byte [] getImgFromIdea(int imgId) {
        Connection con = null;
        PreparedStatement pst = null;
        byte[] buf = null;


        try {
            con = ConnectionDataBaseFactory.getConnection();
            String query = "SELECT  img FROM images WHERE img_id= ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, imgId);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                Blob blob = result.getBlob("img");
                int len = (int) blob.length();
                buf = blob.getBytes(1, len);

            }

        } catch (SQLException e){
            e.printStackTrace();

        } /*catch (IOException e) {
           e.printStackTrace();

       }*/  finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
      /*         if (fos != null) {
                   fos.close();
               }   */

       /*    } catch (IOException e) {
               e.printStackTrace();  */

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return buf;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    @Override
    public LinkedList<Integer> createIdImgUser(int ideaId, int userId, ArrayList<String> colRelativePathImg) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int imgId = 0;
        LinkedList<Integer> colImgId = new LinkedList<>();
        try {
            con = ConnectionDataBaseFactory.getConnection();
            String query1 =  "INSERT INTO images( idea_id, user_id, path_img) VALUES(?,?,?)";
            String query2 =  "SELECT img_id FROM images WHERE user_id= ? AND path_img= ? ";
            for (String pathImg : colRelativePathImg) {
                pst = con.prepareStatement(query1);
                pst.setInt(1, ideaId);
                pst.setInt(2, userId);
                pst.setString(3, pathImg);
                int i = pst.executeUpdate();
                if (i==1){
                    pst = con.prepareStatement(query2);
                    pst.setInt(1, userId);
                    pst.setString(2, pathImg);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        imgId = rs.getInt(1);
                        colImgId.add(imgId);
                    }
                }

            }
        }catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {


                    if (pst!=null){
                        pst.close();
                    }
                    if (con !=null){
                        con.close();
                    }
                }catch (SQLException e){

                }
            }

            return colImgId;  //To change body of implemented methods use File | Settings | File Templates.
        }

    @Override
    public String getPathImgInId(int imgId) {
        Connection con = null;
        PreparedStatement pst = null;
        String relativePathImg = null;


        try {
            con = ConnectionDataBaseFactory.getConnection();
            String query = "SELECT  path_img FROM images WHERE img_id= ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, imgId);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                relativePathImg = result.getString("path_img");

            }

        } catch (SQLException e){
            e.printStackTrace();

        }
          finally {
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
        return relativePathImg;
    }

    @Override
    public LinkedList<Integer> getIdImgUser(int ideaId) {
        Connection con = null;
        PreparedStatement pst = null;
        String relativePathImg = null;
        int imgId = 0;
        LinkedList<Integer> colImgId = new LinkedList<>();

        try {
            con = ConnectionDataBaseFactory.getConnection();
            String query = "SELECT  img_id FROM images WHERE idea_id= ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, ideaId);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                imgId = result.getInt(1);
                colImgId.add(imgId);

            }

        } catch (SQLException e){
            e.printStackTrace();

        }
        finally {
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

        return colImgId;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isPathImg(String pathImg) {
        boolean isPathImg = false;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectionDataBaseFactory.getConnection();
            String query = "SELECT * FROM images " +
                    "WHERE path_img = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, pathImg);
            rs = pst.executeQuery(query);

            if (rs.next()) {
                isPathImg = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            try {
                if (rs!=null )rs.close();
                if (pst!=null)pst.close();
                if (con!=null)con.close();
            }catch (SQLException e){
            }
        }
        return isPathImg;  //To change body of implemented methods use File | Settings | File Templates.
    }
}