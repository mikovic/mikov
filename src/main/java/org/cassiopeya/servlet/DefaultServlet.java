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


public class DefaultServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        HashMap categories = (HashMap) categoryDao.categories();
        request.setAttribute("categories", categories);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Default.jsp");
        rd.forward(request, response);

    }

}
