package org.cassiopeya.servlet;


import org.cassiopeya.dao.*;
import org.cassiopeya.dto.Category;
import org.cassiopeya.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class DefaultServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {

        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();
        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Default.jsp");
        rd.forward(request, response);

    }

}
