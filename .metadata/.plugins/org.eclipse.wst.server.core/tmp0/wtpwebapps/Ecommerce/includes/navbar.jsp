<%@page import="cn.sagar.User" %>
<%@page import="cn.sagar.dao.UserDao" %>
<%@ page import="cn.sagar.servlet.*" %>

<nav class="navbar navbar-expand-lg navbar-collapse-sm bg-body-tertiary">
  <div class="container-fluid container-dark text-bg-dark text-white ">
    <a class="navbar-brand text-white" href="index.jsp ">Shrestha Instruments</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="dark" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar navbar-expand-md  text-white"  id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0 display-flex justify-content-end text-white">
        <li class="nav-item">
          <a class="nav-link active text-white" aria-current="page" href="index.jsp">Home</a>
        </li>
        <li class="nav-item text-white">
          <a class="nav-link text-white " href="cart.jsp">Cart<span class="badge badge-danger">${cart_list.size() }</span></a>
        </li>
        <%if(auth!=null){%>
        	<li class="nav-item ">
            <a class="nav-link text-white" href="orders.jsp">Orders</a>
          </li>
          <li class="nav-item nav-text-white">
            <a class="nav-link text-white" href="LogoutServlet">Logout</a>
          </li>
                  	<form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
          

        	<% }
        	else{%>
        		 <li class="nav-item text-white">
                 <a class="nav-link text-white" href="login.jsp">Login</a>
               </li>
        	<%}%>
        
               
      </ul>
      <span class="navbar-text">
       
      </span>
    </div>
  </div>
</nav>