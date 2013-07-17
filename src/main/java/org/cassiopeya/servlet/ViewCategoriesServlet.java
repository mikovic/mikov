package org.cassiopeya.servlet;

import org.cassiopeya.dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;


public class ViewCategoriesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {

        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int countPagesInCategory = ideaDao.getPagesInCategory(categoryId);
        int startPagin = Integer.parseInt(request.getParameter("startPagin"));
        String pagin = request.getParameter("pagin");
        int page = 0;
        if (pagin == null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int countIdeasOnPage = 3;

        if (pagin != null && "prev".equals(pagin)) {
            page = startPagin - 1;
            startPagin = startPagin - 3;
        }
        if (startPagin < 1){
            startPagin = 1;
            page = 1;
        }
        if (pagin != null && "next".equals(pagin)) {
            if (startPagin + 3> countPagesInCategory){
                page = startPagin;
            }else {
            startPagin = startPagin + 3;
            page = startPagin;
            }
        }

        int endPagin = startPagin + 2;
        if (endPagin >= countPagesInCategory) {
            endPagin = countPagesInCategory;
          }


        ArrayList ideasInCategory = ideaDao.getIdeasInCategory(categoryId, page ,countIdeasOnPage );

        request.setAttribute("startPagin", startPagin);
        request.setAttribute("endPagin", endPagin);
        request.setAttribute("categoryId", categoryId);

        request.setAttribute("ideasInCategory", ideasInCategory);
        request.setAttribute("countPagesInCategory", countPagesInCategory);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewCategories.jsp");
        rd.forward(request, response);

    }
}
