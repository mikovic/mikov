package org.cassiopeya.servlet;

import org.cassiopeya.dao.CategoryDao;
import org.cassiopeya.dao.DaoCategoryFactory;
import org.cassiopeya.dao.DaoUserFactory;
import org.cassiopeya.dao.UserDao;
import org.cassiopeya.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ResultLogInServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UserDao userDao = DaoUserFactory.getUserDao();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userDao.regUser(login, password);
        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();

        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        if (user!=null) {
            String messageVal = "Welcome to Cassiopeya " + user.getUserLogin() + "!";
            request.setAttribute("message", messageVal);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Result.jsp");
            rd.forward(request, response);
        } else {
            String messageVal = "Please, try to be registered later";
            request.setAttribute("message", messageVal);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegLogin.jsp");
            rd.forward(request, response);
        }
    }
}