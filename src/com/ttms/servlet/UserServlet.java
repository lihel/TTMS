package com.ttms.servlet;

import com.ttms.service.UserService;
import com.ttms.vo.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by lmy on 17-12-18.
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equals("login")) {
            login(request, response);
        }/* else if (method.equals("getEmpList")) {
            getEmpList(request, response);
        }*/

    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();

        String emp_no = request.getParameter("name");
        String emp_pass = request.getParameter("pass");
        UserService us = new UserService();
        User user = null;
        try {
            user = us.getUser(emp_no, emp_pass);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/studio.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  /*  public void getEmpList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
}
