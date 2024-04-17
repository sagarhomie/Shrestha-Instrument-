package cn.sagar.servlet;
import cn.sagar.cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class cartServlet
 */
@WebServlet("/cartServlet")
public class cartServlet extends HttpServlet {
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
		try(PrintWriter writer= response.getWriter()){
			ArrayList<cart> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			cart cm = new cart();
			cm.setId(id);
			cm.setQuantity(1);
			HttpSession session = request.getSession();
			ArrayList<cart>cart_list =(ArrayList<cart>) session.getAttribute("cart-list");
			if(cart_list == null) {
				cartList.add(cm);
				session.setAttribute("cart-list",cartList);
				response.sendRedirect("index.jsp");
			}else {
				cartList= cart_list;
				boolean exist = false;
				for(cart c:cartList) {
					if(c.getId()== id) {
						exist = true;
						writer.print("<h3 style = 'color:red; text-align:center'>Item already exist in Cart.<a href='cart.jsp'>Go to Cart Page</a></h3>");
					}
					
				}
				if(!exist) {
					cartList.add(cm);
					response.sendRedirect("index.jsp");
				}
			}
		for(cart c:cart_list) {
			writer.print(c.getId());
		}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
}
