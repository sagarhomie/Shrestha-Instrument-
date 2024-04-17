package cn.sagar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sagar.OrderModel;
import cn.sagar.User;
import cn.sagar.cart;
import cn.sagar.connection.dbCon;
import cn.sagar.dao.orderdao;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try (PrintWriter out= response.getWriter()){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		ArrayList<cart>cart_list =(ArrayList<cart>)request.getSession().getAttribute("cart-list");
		User auth = (User) request.getSession().getAttribute("auth");
		if(cart_list!=null&&auth!=null) {
			for(cart c:cart_list) {
				OrderModel order= new OrderModel();
				order.setId(c.getId());
				order.setUid(auth.getId());
				order.setQuantity(c.getQuantity());
				order.setDate(formatter.format(date));
				orderdao odao = new orderdao(dbCon.getConnection());
				boolean result=odao.insertOrder(order);
				if(!result) {
					break;
				}
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			}
			
		}else {
			if(auth==null) {
				response.sendRedirect("login.jsp");
			}else {
				response.sendRedirect("cart.jsp");
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
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
