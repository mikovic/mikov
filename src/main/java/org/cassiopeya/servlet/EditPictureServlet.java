package org.cassiopeya.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.cassiopeya.dao.*;
import org.cassiopeya.dto.Idea;
import org.cassiopeya.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;


public class EditPictureServlet extends HttpServlet {
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    private static final String ROOT = "C:"+File.separator + "Cassiopeya" + File.separator +"src"+ File.separator +"main" + File.separator +
            "updir";
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        ImgDao imgDao = DaoImgFactory.getImgDao();
        int imgId = Integer.parseInt(request.getParameter("imgId"));

        String pathImg = imgDao.getPathImgInId(imgId);
        new File(ROOT + File.separator + pathImg).delete();
        imgDao.deleteImg(imgId);

        String msg ="You successfully remove your photo!" ;
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(msg);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

        ImgDao imgDao = DaoImgFactory.getImgDao();

        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files


        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String userLogin =user.getUserLogin();
        int userId = user.getUserId();
        String uploadPathUser = ROOT + File.separator +userLogin;
        File uploadDirUser = new File(uploadPathUser);
        if (!uploadDirUser.exists()) {
            uploadDirUser.mkdir();
        }
        factory.setRepository(uploadDirUser);
        String relativePathImg = null;
        File uploadFile = null;
        String fileName = null;
        int ideaId = 0;
        int formId = 0;
        int imgId =0;

        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {

                    if (!item.isFormField()) {
                        fileName = item.getName();
                        if (fileName.lastIndexOf("\\") >= 0) {
                            fileName = fileName.substring(fileName
                                    .lastIndexOf("\\"));
                        } else {
                            fileName = fileName.substring(fileName
                                    .lastIndexOf("\\") + 1);
                        }
                        if (fileName.equals("")) continue;
                        relativePathImg = userLogin + File.separator + fileName;
                        if(!imgDao.isPathImg(relativePathImg)){
                            uploadFile = new File(ROOT + File.separator + relativePathImg);

                            try {
                                item.write(uploadFile);
                            } catch (FileUploadException e) {
                                e.printStackTrace();
                            }
                        }

                    }else {
                        String fieldName = item.getFieldName();
                        String value = item.getString();
                        if ("formId".equals(fieldName)) formId = Integer.parseInt(value);
                        if ("ideaId".equals(fieldName)) ideaId = Integer.parseInt(value);

                    }


                }

                imgId = imgDao.addImgToIdea( ideaId, userId,relativePathImg );

            }
        }   catch (Exception ex){
            ex.printStackTrace();
        }
        Gson gson = new Gson();

        if (fileName ==null ||fileName.equals(""))  {
            String mes="You couldn't add the picture!Please try again!";
            String json = gson.toJson(mes);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } else {
            int[] items = {formId, ideaId};
            String json = gson.toJson(items);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }

    }
}