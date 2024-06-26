package cn.sagar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sagar.Product;
import cn.sagar.User;
import cn.sagar.connection.dbCon;
import cn.sagar.dao.ProductDao;
import cn.sagar.dao.UserDao;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out= response.getWriter()){
			String searchitem = request.getParameter("search");
			
			try {
				ProductDao pdao = new ProductDao(dbCon.getConnection());
				List<Product> list = pdao.searchedItem(searchitem);
				if(list!=null) {
				response.sendRedirect("Search.jsp");
				}else {
					out.println("not found");
				}
				
			}catch(ClassNotFoundException| SQLException e) {
				e.printStackTrace();
			}
	}
		}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		}

}
