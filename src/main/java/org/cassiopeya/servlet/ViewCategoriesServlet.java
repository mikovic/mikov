package org.cassiopeya.servlet;

import org.cassiopeya.dao.CategoryDao;
import org.cassiopeya.dao.DaoCategoryFactory;
import org.cassiopeya.dao.DaoIdeaFactory;
import org.cassiopeya.dao.IdeaDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ViewCategoriesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        IdeaDao ideaDao = DaoIdeaFactory.getIdeaDao();
        String categoryId = request.getParameter("categoryId");
        ArrayList ideas = ideaDao.getIdeasInCategory(categoryId);
        request.setAttribute("ideas", ideas);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewCategories.jsp");
        rd.forward(request, response);
        return;

    }
}
