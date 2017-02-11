<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hackspace.dev.pojo.Product" %>
<%@ page import="hackspace.dev.servlets.MainServlet" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Education</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles/main.css">
  </head>
  <body>

  <%
    List<Product> products = (List<Product>) request.getSession().getAttribute(MainServlet.PRODUCT);
    for (Product product : products) {
  %>
    <h1>Product <%=product.getName()%></h1>

  <%
    }
  %>
  </body>
</html>