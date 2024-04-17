package cn.sagar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.sagar.OrderModel;

public class orderdao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public orderdao(Connection con) {
		this.con = con;
		
	}
	public boolean insertOrder(OrderModel model) {
		boolean result = false;
		try {
			query =" insert into ecommerce_orders (p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
			pst= this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			pst.executeUpdate();
			result = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<OrderModel> userOrders(int id){
		List<OrderModel> list = new ArrayList<>();
		try {
			query = "select* from ecommerce_orders where u_id=? order by orders.o_id desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			while(rs.next()) {
				OrderModel order = new OrderModel();
				ProductDao productDao = new ProductDao(this.con);
				
				
			}
		}
	}
}
