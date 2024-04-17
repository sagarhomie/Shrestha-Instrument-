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
 * Servlet implementation class QuantityIncDecServlet
 */
@WebServlet("/QuantityIncDecServlet")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter writer = response.getWriter()){
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<cart> cart_list= (ArrayList<cart>)request.getSession().getAttribute("cart-list");
			if(action !=null && id>=1) {
				if(action.equals("inc")) {
					for(cart c:cart_list) {
						if(c.getId()==id) {
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}else {
					for(cart c:cart_list) {
						if(c.getId()==id&&c.getQuantity()>1) {
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
				}
			}
	
		catch(Exception e) {
			e.printStackTrace();
		}
			
		
	}
}


