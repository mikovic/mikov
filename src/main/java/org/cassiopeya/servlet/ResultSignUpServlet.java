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


public class ResultSignUpServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        CategoryDao categoryDao = DaoCategoryFactory.getCategoryDao();

        Map categories =  categoryDao.getCategories();
        request.setAttribute("categories", categories);
        UserDao userDao = DaoUserFactory.getUserDao();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String email = request.getParameter("email");
        if ((login == null)||(login.trim().equals(""))) {
           String messageVal = "Login can't null or a white space.Please, correctly enter your name.";
            request.setAttribute("message", messageVal);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegSignUp.jsp");
            rd.forward(request, response);
            return;
        }
        if ((password == null)||(password.trim().equals(""))) {
            String messageVal = "Password can't null or a white space.Please, correctly enter your password.";
            request.setAttribute("message", messageVal);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegSignUp.jsp");
            rd.forward(request, response);
            return;
        }
        if (!(re_password.trim()).equals(password))
        {
            String messageVal = "Please, correctly repeat the password!";
            request.setAttribute("message", messageVal);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegSignUp.jsp");
            rd.forward(request, response);
            return;
        }
        if ( userDao.isUserByLogin(login)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegSignUp.jsp");
            String messageVal = "You has been taken.Please select another name.";
            request.setAttribute("message", messageVal);
            rd.forward(request, response);


        }else
        {
            User user = userDao.createUser(login, password, email);
            if (user!=null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Result.jsp");
                String messageVal = "You were successfully added.";
                request.setAttribute("message", messageVal);
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                rd.forward(request, response);

            }else  {
                String messageVal = "Please, try to be registered later";
                request.setAttribute("message", messageVal);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegSignUp.jsp");
                rd.forward(request, response);

            }

        }
    }
}
