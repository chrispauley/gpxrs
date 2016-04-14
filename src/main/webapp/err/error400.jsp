<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GPXDB Webapp - 400 Bad Request. Syntax of the request not understood by the server.</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="404 page for gpxdb.com. GPXDB is a REST enabled web application database storage and retrieval of gpx data.">
<meta name="author" content="Chris Pauley, Solebury Internet Marketing, LLC">

<!-- Le styles -->
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="../assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="../ico/apple-touch-icon-57-precomposed.png">
<script src="../js/lib/jquery.js"></script>
<script src="../js/lib/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="/page-nav.jsp" %>
<div class="container-fluid">
<div class="row-fluid">    
	<article class="offset2 span8">
      <header>
        <h2>400 - Bad Request.</h2>
        <h3>Syntax of the request not understood by the server</h3>
      </header>
      <p>Sorry, but the page/resource could not be served.</p>
      <ul>
        <li><p>
          <% out.println(pageContext.getErrorData().getRequestURI()); %>
          <br> <% out.println(pageContext.getErrorData().getStatusCode()); %>
          </p>
        </li>
        <li>an out-of-date link</li>
      </ul>
    </article>
    </div>
</div>
</body>
</html>