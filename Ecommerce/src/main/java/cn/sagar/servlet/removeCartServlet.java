package cn.sagar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sagar.cart;

/**
 * Servlet implementation class removeCartServlet
 */
@WebServlet("/removeCartServlet")
public class removeCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter writer=response.getWriter()){
			String id =request.getParameter("id");
			if(id!=null) {
				ArrayList<cart> cart_list =(ArrayList<cart>) request.getSession().getAttribute("cart-list");
				if(cart_list!=null) {
					for(cart c:cart_list) {
						if(c.getId()==Integer.parseInt(id)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
						response.sendRedirect("cart.jsp");
					}
				}else {
					response.sendRedirect("cart.jsp");
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace()	;
			}
	}

}
