package org.cassiopeya.servlet;

import org.cassiopeya.dao.*;
import org.cassiopeya.dto.Idea;
import org.cassiopeya.dto.Invest;
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


public class AddInvestServlet extends HttpServlet {
    public void doGet (HttpServletRequest request, HttpServletResponse  response)
            throws IOException, ServletException
    {
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

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddInvest.jsp");
            rd.forward(request, response);
        }else {
            String message = "You will be able to invest if you are registered";
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
        InvestDao investDao = DaoInvestFactory.getInvestDao();
        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();
        CommentDao commentDao = DaoCommentFactory.getCommentDao();
        Date investDate = new Date();
        int ideaId = Integer.parseInt(request.getParameter("ideaId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int investment = Integer.parseInt(request.getParameter("investment"));
        Idea idea = ideaDao.getIdeaInId(ideaId);
        Invest invest = investDao.createInvest(userId, ideaId, investment, investDate);
        String msg3 = "Thank you for your investments into our project of $ ";
        int myInvestInIdea = investDao.mySumInvestInIdea(userId, ideaId);
        String  msg1 = "Now all your investments into the project $ ";
        request.setAttribute("msg1", msg1);
        request.setAttribute("myInvestInIdea",myInvestInIdea );
        int sumInvest = investDao.sumInvest(ideaId);
        String  msg2 = "All investments into the project  $ ";
        ArrayList comments = commentDao.getCommentsInIdeId(ideaId);
        request.setAttribute("msg1", msg1);
        request.setAttribute("msg2", msg2);
        request.setAttribute("msg3", msg3);
        request.setAttribute("myInvestInIdea",myInvestInIdea );
        request.setAttribute("sumInvest",sumInvest );
        request.setAttribute("comments", comments);
        request.setAttribute("idea", idea);
        request.setAttribute("invest", invest);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/IdeaView.jsp");
        rd.forward(request, response);

    }



}

