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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class EditServlet extends HttpServlet {

    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();
        int ideaId = Integer.parseInt(request.getParameter("ideaId"));
        Idea ideaEdit = ideaDao.getIdeaInId(ideaId);
        request.setAttribute("ideaEdit", ideaEdit);
        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        ImgDao imgDao = DaoImgFactory.getImgDao();
        LinkedList<Integer> idImgUser= imgDao.getIdImgUser(ideaId);
        request.setAttribute("idImgUser", idImgUser);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Edit.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files


        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        File uploadFile = null;
        int ideaId = 0;
        int page = 0;
        int imgId = 0;
        String fileName = null;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int userId = user.getUserId();
        String uploadDir = "uploadimgdir";
        String root = "C:"+File.separator + "Cassiopeya" + File.separator +"src"+"File.separator"+"main" + File.separator +
                uploadDir;
        factory.setRepository(new File(root));
        String filePath = root + File.separator;
        ImgDao imgDao = DaoImgFactory.getImgDao();
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
                        uploadFile = new File(filePath + fileName);
                        try {
                            item.write(uploadFile);
                        } catch (FileUploadException e) {
                            e.printStackTrace();
                        }
                    }else {
                        String fieldName = item.getFieldName();
                        String value = item.getString();

                        if ("page".equals(fieldName)) page = Integer.parseInt(value);

                    }

                }

                imgId = imgDao.addImgToIdea(userId, fileName, filePath);
            }
        }   catch (Exception ex){
            ex.printStackTrace();
        }


        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();
        CommentDao commentDao = DaoCommentFactory.getCommentDao();

        Idea ideaUserOnPage = ideaDao.getIdeaUserOnPage(userId, page );
        int countIdeasUser = ideaDao.getCountIdeasUser(userId);
        Idea infoInvestInIdea = ideaDao.getInfoInvestInIdea(ideaUserOnPage.getIdeaId());
        ArrayList comments = commentDao.getCommentsInIdeId(ideaUserOnPage.getIdeaId());
        request.setAttribute("ideaUserOnPage", ideaUserOnPage);
        request.setAttribute("countIdeasUser", countIdeasUser);
        request.setAttribute("page", page);
        request.setAttribute("imgId", imgId);
        if (comments.size()==0){
            String msg1 = "Unfortunately to the project so nobody left the comment";
            request.setAttribute("msg1", msg1);
        } else {
            request.setAttribute("comments", comments);
        }

        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("infoInvestInIdea", infoInvestInIdea);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/MyProfile.jsp");
        rd.forward(request, response);






    }
}
