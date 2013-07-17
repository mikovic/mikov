package org.cassiopeya.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegistrationServlet  extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(RegistrationServlet.class.getName());
  /*  static {
        LOGGER.setLevel(Level.INFO);

        FileHandler fileTxt = null;
        try {
            fileTxt = new FileHandler("Logging.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.addHandler(fileTxt);
    } */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String action = request.getParameter("action");
        LOGGER.info("action = " + action);

        if (action!= null && "signUp".equalsIgnoreCase(action)){
            LOGGER.info("registration");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegSignUp.jsp");
            rd.forward(request, response);
        }
        if (action!=null && "login".equalsIgnoreCase(action)){
            LOGGER.info("login");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegLogin.jsp");
        rd.forward(request, response);
        }
        if (action == null) {
            LOGGER.info("error");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
            rd.forward(request, response);
        }
    }
  }
