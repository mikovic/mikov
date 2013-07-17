package org.cassiopeya.servlet;

import org.cassiopeya.dao.*;
import org.cassiopeya.dto.Comment;
import org.cassiopeya.dto.Idea;
import org.cassiopeya.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class AddCommentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        CommentDao commentDao = DaoCommentFactory.getCommentDao();
        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();
        int ideaId = Integer.parseInt(request.getParameter("ideaId"));
        Idea idea = ideaDao.getIdeaInId(ideaId);
        request.setAttribute("idea", idea);
        ArrayList comments = commentDao.getCommentsInIdeId(ideaId);
        request.setAttribute("comments", comments);

        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");
        if (user != null) {

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddComment.jsp");
            rd.forward(request, response);
        }else {
            String message = "To leave the comment you should be registered";
            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/IdeaView.jsp");
            rd.forward(request, response);
        }

    }
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        CommentDao commentDao = DaoCommentFactory.getCommentDao();
        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();
        Date createDate = new Date();

        int ideaId = Integer.parseInt(request.getParameter("ideaId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String text = request.getParameter("text");

        commentDao.createComment(userId, ideaId, text,createDate);
        ArrayList comments = commentDao.getCommentsInIdeId(ideaId);
        Idea idea = ideaDao.getIdeaInId(ideaId);
        request.setAttribute("idea", idea);
        InvestDao investDao = DaoInvestFactory.getInvestDao();
        String msg1;
        int myInvestInIdea = investDao.mySumInvestInIdea(userId, ideaId);
        if (myInvestInIdea==0){
            msg1 = "You didn't do still investments in this project.";
        } else {
            msg1 = "Your investments into this project made $ ";
        }
        request.setAttribute("msg1", msg1);
        request.setAttribute("myInvestInIdea",myInvestInIdea );

        int sumInvest = investDao.sumInvest(ideaId);
        String msg2;
        if (sumInvest ==0){
            msg2 = "While nobody invested in this project.";
        } else {
            msg2 = "All investments into the project make $ ";
        }
        String msg4 = "Comments:";
        request.setAttribute("msg2", msg2);
        request.setAttribute("msg4", msg4);
        request.setAttribute("sumInvest",sumInvest );









        request.setAttribute("comments", comments);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/IdeaView.jsp");
        rd.forward(request, response);

    }
}
