<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.sagar.User"%>
<%@page import="cn.sagar.dao.*"%>
<%@page import="java.util.*"%>
<%@page import="cn.sagar.cart" %>

<%@page import="cn.sagar.Product"%>
<%@page import="cn.sagar.servlet.*"%>
<%@page import="cn.sagar.connection.dbCon"%>
<
<%
ArrayList<User> hi = (ArrayList<User>) request.getSession().getAttribute("user-list");
if(hi!=null) {
	
	request.getSession().setAttribute("user-list",hi);
	response.sendRedirect("userdetail.jsp");
}

UserDao udao = new UserDao(dbCon.getConnection());
List<User> userList = udao.getAllUser();


%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
</head>
<body>
    <h1>Welcome Admin!</h1>
    
    <!-- Example form for admin actions -->
    <form action="admin" method="post">
        <label for="action">Action:</label>
        <select name="action" id="action">
            <option value="view_users">View Users</option>
            <option value="manage_posts">Manage Posts</option>
            <!-- Add more options as needed -->
        </select>
        <button type="submit">Submit</button>
    </form>
    
    <div id="result">
        <!-- Display results here -->
        <%-- This section can be used to display server-side results --%>
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
    </div>
</body>
</html>
