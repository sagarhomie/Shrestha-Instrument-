<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
ProductDao pd= new ProductDao(dbCon.getConnection()); 
List<Product>products = pd.getAllProducts();
List<Product>westernproduct=pd.getWesternProduct();
ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
List<cart> cartProduct = null;
if(cart_list != null){
	
	request.setAttribute("cart_list",cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>

<title>Eshopping</title>
<style>
.card .img-fit{
aspect-ratio:4/3;
object-fit:cover;

}
.banner-img {
            width: 910px;
            height: 344px;
            margin:auto;
            aspect-ratio:16/3;
            object-fit:cover;
            display:flex;
            justify-content:center;
        }
        .offer-overlay {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: rgba(0, 0, 0, 0.7);
            padding: 10px;
            border-radius: 5px;
        }
        .offer-text {
            color: #fff;
            font-size: 24px;
            font-weight: bold;
        }
</style>
<script src="https://khalti.s3.ap-south-1.amazonaws.com/KPG/dist/2020.12.17.0.0.0/khalti-checkout.iffe.js"></script>

<%@include file="includes/header.jsp"%>

</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	        <img src="product-images/discount.jpg" alt="Your Logo" class="banner-img">
        <div class="offer-overlay">
            <div class="offer-text">10% Off Now</div>
        </div>
	
	<div class="container">
		<div class="card-header my-3">All products</div>
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
						<a href="NewFile.jsp"  class="btn btn-primary" >Buy Now</a>						
						</div>
					</div>
					</div>
					</div>
					
				
			<%}
		}%>
			

			</div>
				<div class="card-header my-3">Western Musical instruments</div>
		<div class="row">
		<%
		if(!westernproduct.isEmpty()){
			for(Product ps:westernproduct){%>
				<div class="col-md-3 my-3 lg-3">
				<div class="card h-100 w-100" style="width: 18rem;">
					<img src="product-images/<%=ps.getImage() %>" class="card-img-top img-fluid img-fit" alt="dhimey">
					<div class="card-body mt-1 pt-2">
						<h5 class="card-title"><%= ps.getName()%></h5>
						<h6 class="price">Price:Rs <%=ps.getPrice()%></h6>
						<h6 class="price">Category:<%= ps.getCategory()%></h6>
						<div class="mt-7 d-flex justify-content-between">
						<a href="cartServlet?id=<%=ps.getId() %>" class="btn btn-dark">Add to cart</a>
						<a href="NewFile.jsp"  class="btn btn-primary" >Buy Now</a>
						</div>
						
					</div>
					</div>
					</div>
					
				
			<%}
		}%>
			

			</div>
		
		</div>
		<script>
        var config = {
            // replace the publicKey with yours
            "publicKey": "test_public_key_dc74e0fd57cb46cd93832aee0a390234",
            "productIdentity": "1234567890",
            "productName": "Dragon",
            "productUrl": "http://gameofthrones.wikia.com/wiki/Dragons",
            "paymentPreference": [
                "KHALTI",
                "EBANKING",
                "MOBILE_BANKING",
                "CONNECT_IPS",
                "SCT",
                ],
            "eventHandler": {
                onSuccess (payload) {
                    // hit merchant api for initiating verfication
                    console.log(payload);
                },
                onError (error) {
                    console.log(error);
                },
                onClose () {
                    console.log('widget is closing');
                }
            }
        };

        var checkout = new KhaltiCheckout(config);
        var btn = document.getElementById("button");
        btn.onclick = function () {
            // minimum transaction amount must be 10, i.e 1000 in paisa.
            checkout.show({amount: 100000});
        }
    </script>
		
	
	<%@include file="includes/footer.jsp"%>
</body>
</html>