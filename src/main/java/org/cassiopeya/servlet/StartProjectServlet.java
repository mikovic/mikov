package org.cassiopeya.servlet;

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
import java.text.DateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 19.05.13
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */
public class StartProjectServlet extends HttpServlet {
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    private static final String ROOT = "C:"+File.separator + "Cassiopeya" + File.separator +"src"+ File.separator +"main" + File.separator +
            "updir";
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");
        int userId = user.getUserId();
        ArrayList ideasInUserId = ideaDao.getIdeasInUserId(userId);
        request.setAttribute("ideasInUserId", ideasInUserId);
        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        String action = request.getParameter("action");
        if ("startProject".equals(action)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/StartProject.jsp");
            rd.forward(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        ImgDao imgDao = DaoImgFactory.getImgDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);

        Date createDate = new Date();
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
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");
        String userLogin =user.getUserLogin();
        String uploadPathUser = ROOT + File.separator +userLogin;
        File uploadDirUser = new File(uploadPathUser);
        if (!uploadDirUser.exists()) {
            uploadDirUser.mkdir();
        }
        factory.setRepository(uploadDirUser);
        String relativePathImg = null;
        File uploadFile = null;
        String fileName = null;
        int userId = 0;
        int categoryId = 0;
        String topicIdea = null;
        String descIdea = null;
        int budget = 0;
        Idea ideaNew = null;
        ArrayList<String> colRelativePathImg = new ArrayList<>();
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
                        relativePathImg = userLogin + File.separator + fileName;
                        if(!imgDao.isPathImg(relativePathImg)){
                            uploadFile = new File(ROOT + File.separator + relativePathImg);

                            try {
                                item.write(uploadFile);
                            } catch (FileUploadException e) {
                                e.printStackTrace();
                            }
                        }
                        colRelativePathImg.add(relativePathImg);
                    }else {
                        String fieldName = item.getFieldName();
                        String value = item.getString();

                        if ("userId".equals(fieldName)) userId = Integer.parseInt(value);
                        if ("categoryId".equals(fieldName)) categoryId = Integer.parseInt(value);
                        if ("topicIdea".equals(fieldName)) topicIdea = value;
                        if ("descIdea".equals(fieldName)) descIdea = value;
                        if ("budget".equals(fieldName)) budget = Integer.parseInt((value));
                    }


                }

                ideaNew = ideaDao.createIdea(userId, categoryId, topicIdea, descIdea, createDate, budget);

            }
        }   catch (Exception ex){
            ex.printStackTrace();
        }


        if (colRelativePathImg.size()!=0){
            int ideaNewId = ideaNew.getIdeaId();
            LinkedList<Integer> colImgId = imgDao.createIdImgUser(ideaNewId, userId, colRelativePathImg);
            int firstImgId = colImgId.poll();
            request.setAttribute("firstImgId", firstImgId);
            if (colImgId.size() != 0){
                request.setAttribute("colImgId", colImgId);
            }

        }
        String msg ="Our congratulations! You just backed new project";
        ArrayList ideasUser = ideaDao.getIdeasInUserId(userId);
        int countIdeasUser = ideaDao.getCountIdeasUser(userId);
        int page =1;
        request.setAttribute("countIdeasUser",countIdeasUser);
        request.setAttribute("page", page);
        request.setAttribute("ideasUser", ideasUser);
        request.setAttribute("msg", msg);
        request.setAttribute("ideaNew", ideaNew);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/MyProfile.jsp");
        rd.forward(request, response);
    }
}