<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.sagar.User"%>
<%@page import="cn.sagar.dao.ProductDao"%>
<%@page import="java.util.*"%>
<%@page import="cn.sagar.cart" %>

<%@page import="cn.sagar.Product"%>
<%@page import="cn.sagar.servlet.*"%>
<%@page import="cn.sagar.connection.dbCon"%>
    <%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
    	request.setAttribute("auth", auth);

    }
   
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Searched Item</title>
<%@include file = "includes/header.jsp" %>
</head>
<body>
<%@ include file = "includes/navbar.jsp" %>
<%
 ProductDao pd= new ProductDao(dbCon.getConnection()); 
    String searchitem= request.getParameter("newari musical instrument");
    List<Product> products = pd.searchedItem(searchitem);
    %>
<div class="container">
		<div class="card-header my-3">Search Item</div>
		<div class="row">
		<%
		if(!products.isEmpty()){
			for(Product p:products){%>
				<div class="col-md-3 my-3 lg-3">
				<div class="card h-100 w-100" style="width: 18rem;">
					<img src="product-images/<%=p.getImage() %>" class="card-img-top img-fluid img-fit" alt="dhimey">
					<div class="card-body mt-1 pt-2">
						<h5 class="card-title"><%= p.getName()%></h5>
						<h6 class="price">Price:Rs <%=p.getPrice()%></h6>
						<h6 class="price">Category:<%= p.getCategory()%></h6>
						<div class="mt-7 d-flex justify-content-between">
						<a href="cartServlet?id=<%=p.getId() %>" class="btn btn-dark">Add to cart</a>
						<a href="OrderNowServlet?quantity=1&id=<%=p.getId() %>" class="btn btn-primary">Buy Now</a>
						</div>
						
					</div>
					</div>
					</div>
					
				
			<%}
		}%>
			

			</div>
		</div>

</body>
</html>