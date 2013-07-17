package org.cassiopeya.servlet;

import org.cassiopeya.dao.*;
import org.cassiopeya.dto.Idea;
import org.cassiopeya.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;


public class MyProfileServlet  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();
        CommentDao commentDao = DaoCommentFactory.getCommentDao();
        ImgDao imgDao = DaoImgFactory.getImgDao();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int userId = user.getUserId();
        int page = Integer.parseInt(request.getParameter("page"));
        Idea ideaUserOnPage = ideaDao.getIdeaUserOnPage(userId, page );
        int ideaIdOnPage = ideaUserOnPage.getIdeaId();
        LinkedList<Integer> colImgId = imgDao.getIdImgUser(ideaIdOnPage);
        if (colImgId.size()!=0){
            int firstImgId = colImgId.poll();
            request.setAttribute("firstImgId", firstImgId);
            if (colImgId.size() != 0){
                request.setAttribute("colImgId", colImgId);
            }
        }
        int countIdeasUser = ideaDao.getCountIdeasUser(userId);
        Idea infoInvestInIdea = ideaDao.getInfoInvestInIdea(ideaUserOnPage.getIdeaId());
        ArrayList comments = commentDao.getCommentsInIdeId(ideaUserOnPage.getIdeaId());
        request.setAttribute("ideaUserOnPage", ideaUserOnPage);
        request.setAttribute("countIdeasUser", countIdeasUser);
        request.setAttribute("page", page);
        if (comments.size()==0){
            String msg1 = "Unfortunately to the project so nobody left the comment";
            request.setAttribute("msg1", msg1);
        } else {
            request.setAttribute("comments", comments);
        }

        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        String action = request.getParameter("action");
        request.setAttribute("infoInvestInIdea", infoInvestInIdea);
        if ("myProfile".equals(action)){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/MyProfile.jsp");
            rd.forward(request, response);
        }
        if ("editSetting".equals(action)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/MyProfile.jsp");
            rd.forward(request, response);
        }
        if ("logOut".equals(action)){

            session.setAttribute("user", null);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Default.jsp");
            rd.forward(request, response);
        }
    }

}
