package org.cassiopeya.servlet;

import org.cassiopeya.dao.DaoImgFactory;
import org.cassiopeya.dao.ImgDao;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 03.10.13
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class DownLoadEditPictureServlet extends HttpServlet {
    static final long serialVersionUID = 1L;
    private static final String ROOT = "C:" + File.separator + "Cassiopeya" + File.separator + "src" + File.separator + "main" +
            File.separator + "updir";
    private static final int BUFSIZE = 4096;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgDao imgDao = DaoImgFactory.getImgDao();
        String imgIdParameter = request.getParameter("imgId");
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