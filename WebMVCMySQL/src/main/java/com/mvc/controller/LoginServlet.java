package com.mvc.controller;

import com.mvc.bean.LoginBean;
import com.mvc.dao.LoginDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

  public LoginServlet() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String userName = request.getParameter("username");//Se toman los valores
    String password = request.getParameter("password");//de la página JSP
    //Se crea un bean LoginBean, una clase normal de java, contiene sólo setters y getters, para acceder a la información del usuario cuando se requiere.
    LoginBean loginBean = new LoginBean();

    //establece username and password
    loginBean.setUserName(userName);
    loginBean.setPassword(password);

    //crea un objeto LoginDao. Contiene la lógica principal de la aplicación.
    LoginDao loginDao = new LoginDao();

    String userValidate = loginDao.authenticateUser(loginBean); //Llama la función para autenticar

    if (userValidate.equals("SUCCESS")) {
      request.setAttribute("userName", userName);
      request.getRequestDispatcher("/Home.jsp").forward(request, response);
    } else {
      request.setAttribute("errMessage", userValidate);
      request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }
  }

}
