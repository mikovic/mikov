package org.cassiopeya.servlet;

import org.cassiopeya.dao.DaoImgFactory;
import org.cassiopeya.dao.ImgDao;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;


public class DownLoadServlet extends javax.servlet.http.HttpServlet implements
        javax.servlet.Servlet {
    static final long serialVersionUID = 1L;
    private static final String ROOT = "C:" + File.separator + "Cassiopeya" + File.separator + "src" + File.separator + "main" +
            File.separator + "updir";
    private static final int BUFSIZE = 4096;


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ImgDao imgDao = DaoImgFactory.getImgDao();
        String load = request.getParameter("load");
        if (load != null && "myProfile".equalsIgnoreCase(load)) {
            String imgIdParameter = request.getParameter("imgId");
            if (imgIdParameter != null) {
                int imgId = Integer.parseInt(imgIdParameter);
                String relativePathImg = imgDao.getPathImgInId(imgId);
                File imgFile = new File(ROOT + File.separator + relativePathImg);
                byte[] byteBuffer = new byte[BUFSIZE];
                int length = 0;
                FileInputStream in = new FileInputStream(imgFile);
                ServletOutputStream outStream = response.getOutputStream();
                while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
                    outStream.write(byteBuffer, 0, length);
                }
                in.close();
                outStream.close();
            }
        }
        if (load != null && "viewCateg".equalsIgnoreCase(load)) {
            int ideaId = Integer.parseInt(request.getParameter("ideaId"));
            LinkedList<Integer> colImgId = imgDao.getIdImgUser(ideaId);
            if (colImgId.size() != 0) {
                int imgId = colImgId.get(0);
                String relativePathImg = imgDao.getPathImgInId(imgId);
                File imgFile = new File(ROOT + File.separator + relativePathImg);
                byte[] byteBuffer = new byte[BUFSIZE];
                int length = 0;
                FileInputStream in = new FileInputStream(imgFile);
                ServletOutputStream outStream = response.getOutputStream();
                while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
                    outStream.write(byteBuffer, 0, length);
                }
                in.close();
                outStream.close();
            }
        }
        if (load != null && "ideaV".equalsIgnoreCase(load)) {
            String imgIdParameter = request.getParameter("imgId");
            if (imgIdParameter != null) {
                int imgId = Integer.parseInt(imgIdParameter);
                String relativePathImg = imgDao.getPathImgInId(imgId);
                File imgFile = new File(ROOT + File.separator + relativePathImg);
                byte[] byteBuffer = new byte[BUFSIZE];
                int length = 0;
                FileInputStream in = new FileInputStream(imgFile);
                ServletOutputStream outStream = response.getOutputStream();
                while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
                    outStream.write(byteBuffer, 0, length);
                }
                in.close();
                outStream.close();
            }
        }
        if (load != null && "edit".equalsIgnoreCase(load)) {
            String imgIdParameter = request.getParameter("imgId");
            if (imgIdParameter != null) {
                int imgId = Integer.parseInt(imgIdParameter);
                String relativePathImg = imgDao.getPathImgInId(imgId);
                File imgFile = new File(ROOT + File.separator + relativePathImg);
                byte[] byteBuffer = new byte[BUFSIZE];
                int length = 0;
                FileInputStream in = new FileInputStream(imgFile);
                ServletOutputStream outStream = response.getOutputStream();
                while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
                    outStream.write(byteBuffer, 0, length);
                }
                in.close();
                outStream.close();
            }
        }
    }

        /*else {
        int imgId = Integer.parseInt(request.getParameter("imgId"));
        byte[] buf = imgDao.getImgFromIdea(imgId);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(buf,0,buf.length);
        outStream.close();
        } */
}


