package cn.sagar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sagar.User;
import cn.sagar.connection.dbCon;
import cn.sagar.dao.UserDao;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/Signin")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter out = response.getWriter()) {
			String fullname= request.getParameter("fullname");
			String email=request.getParameter("email");
			String password = request.getParameter("password");
			String confirmpassword = request.getParameter("confirm-password");
			int id=2;
			try {
				UserDao udao = new UserDao(dbCon.getConnection());
				boolean user = udao.userRegister(id,fullname,email, password);
				if(!user) {
					out.print("<h3 style = 'color:red; text-align:center'>Registration Fail.<a href='signup.jsp'>go to signup page</a></h3>");
					
				}else {
					response.sendRedirect("login.jsp");
				}
			}catch(ClassNotFoundException| SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}
