<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="cn.sagar.User"%>
<%@page import="cn.sagar.connection.dbCon"%>
<%@page import="cn.sagar.dao.*"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.sagar.cart"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);

}
ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
List<cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pDao = new ProductDao(dbCon.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	double total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>

<title>shopping cart</title>
<%@include file="includes/header.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
footer{
position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: red;
  color: white;
  text-align: center;}
</style>

</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price: Rs ${(total>0)?total:0 }</h3>
			<a class="mx-3 btn btn-primary" href="CheckOutServlet">Check Out</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td>Rs<%=c.getPrice()%></td>
					<td><form action="epay.jsp" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-incre"
									href="QuantityIncDecServlet?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a> <input type="text"
									name="quantity" class="form-control w-50"
									value=<%=c.getQuantity()%> readonly> <a
									class="btn btn-sm btn-decre"
									href="QuantityIncDecServlet?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a>
							
							<button type="submit" class ="btn btn-primary btn-md">Buy</button></div>
						</form></td>
					<td><a class="btn btn-sm btn-danger"
						href="removeCartServlet?id=<%=c.getId()%>">Remove</a>
						</td>
				</tr>
			</tbody>

			<%
			}
			}
			%>


		</table>
	</div>
<footer><%@include file="includes/footer.jsp"%></footer>
	
</body>
</html>