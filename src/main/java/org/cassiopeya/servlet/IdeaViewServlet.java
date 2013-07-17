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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;


public class IdeaViewServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {

        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        CommentDao commentDao = DaoCommentFactory.getCommentDao();
        InvestDao investDao = DaoInvestFactory.getInvestDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();

        int ideaId = Integer.parseInt(request.getParameter("ideaId"));
        ArrayList comments = commentDao.getCommentsInIdeId(ideaId);
        String msg4;
        if (comments.size() != 0){
            request.setAttribute("comments", comments);
             msg4 = "Comments:";
        } else {
             msg4 = "Comments while aren't present!";
        }
        Idea idea = ideaDao.getIdeaInId(ideaId);

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user!=null) {
            String msg1;
            int userId = user.getUserId();
            int myInvestInIdea = investDao.mySumInvestInIdea(userId, ideaId);
            if (myInvestInIdea==0){
                msg1 = "You didn't do still investments in this project.";
            } else {
                msg1 = "Your investments into this project made $ ";
            }
            request.setAttribute("msg1", msg1);
            request.setAttribute("myInvestInIdea",myInvestInIdea );

        }
        int sumInvest = investDao.sumInvest(ideaId);
        String msg2;
        if (sumInvest ==0){
            msg2 = "While nobody invested in this project.";
        } else {
            msg2 = "All investments into the project make $ ";
        }

        ImgDao imgDao = DaoImgFactory.getImgDao();
        LinkedList<Integer> colImgId = imgDao.getIdImgUser(ideaId);
        if (colImgId.size()!=0){
            int firstImgId = colImgId.poll();
            request.setAttribute("firstImgId", firstImgId);
            if (colImgId.size() != 0){
                request.setAttribute("colImgId", colImgId);
            }
        }
        request.setAttribute("msg2", msg2);
        request.setAttribute("msg4", msg4);
        request.setAttribute("sumInvest",sumInvest );
        request.setAttribute("idea", idea);
        request.setAttribute("comments", comments);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/IdeaView.jsp");
        rd.forward(request, response);

    }


}
