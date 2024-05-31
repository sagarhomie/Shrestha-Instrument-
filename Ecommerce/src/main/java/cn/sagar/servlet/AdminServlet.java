package cn.sagar.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sagar.User;
import cn.sagar.cart;
import cn.sagar.connection.dbCon;
import cn.sagar.dao.UserDao;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("view_users".equals(action)) {
        	try(PrintWriter out = response.getWriter()){
        		UserDao dao = new UserDao(dbCon.getConnection()); 
        		List<User> user = dao.getAllUser();
        		ArrayList<User> hi = (ArrayList<User>) request.getSession().getAttribute("user-list");
        		if(hi!=null) {
        			HttpSession session = request.getSession();
        			session.setAttribute("user-list",hi);
        			response.sendRedirect("userdetail.jsp");
        		}response.sendRedirect("userdetail.jsp");
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        
        
}
}
