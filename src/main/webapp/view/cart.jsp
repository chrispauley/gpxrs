<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GPXDB cart.jsp - test view template file.</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">

<style>
body {
	padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
}
</style>


</head>

<body>
 <%@ include file="/page-nav.jsp" %>
<h1>cart.js</h1>
<div class="container" style="margin:0 auto;">
  <div class="row">
      
  <h1>Welcome ${it.user}!</h1>
  <p>
    items in your cart :<br />
    <c:forEach var="item" items="${it.items}">
        ${item}<br />
    </c:forEach>
  </p>
 </div>
</div>
<jsp:include page="/footer.jsp" />
 <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
 <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>