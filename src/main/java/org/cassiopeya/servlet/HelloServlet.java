package org.cassiopeya.servlet;


import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    String messageVal = null;
    boolean flag = false;
    boolean error = false;
    String  messageError = null;

    public void init()   {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Throwable e) {
            System.out.println(e.toString());
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {

       RequestDispatcher rd = getServletContext().getRequestDispatcher("/reg.jsp");
        rd.forward(request, response);


    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();

         try {
             Connection con =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
             Statement s = con.createStatement();
             s.executeQuery("USE cassiopeya");

             String sql = "SELECT userLogin FROM users " +
                     "WHERE userLogin ='" + login + "'" +
                     " AND password ='" + password + "'";
             ResultSet rs = s.executeQuery(sql);
             if (rs.next()) {
                rs.close();
                 s.close();
                 flag = true;
                 messageVal = "You has been taken.Please select another name.";
             }
             else {
                 rs.close();
                 sql = "INSERT INTO users"+
                         " (userLogin, password, email)" +
                         " VALUES" +
                         "('" + login +
                         "','" + password +"','" +
                          email +"')";
                 int i = s.executeUpdate(sql);
                 if (i==1){
                    messageVal = "You were successfully added.";
                     flag = false;
                 }


             }
             s.close();
             con.close();
         }
         catch (SQLException e) {
             messageError = "There was a SQL mistake.";
            error = true;
            // e.printStackTrace();
             out.println(e.toString());
         }
         catch (Exception e) {
             messageError = "There was a mistake. Try to be registered again.";
             error = true;
            // e.printStackTrace();
             out.println(e.toString());
         }
        request.setAttribute("message", messageVal);
        request.setAttribute("messageEr",messageError );
        if (error) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/errorPage.jsp");
            rd.forward(request, response);
            return;

        }
         if (flag) {
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/reg.jsp");
             rd.forward(request, response);


         }
         else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/result.jsp");
             rd.forward(request, response);
         }

         }

 }
