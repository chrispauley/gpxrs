<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GPXDB Webapp - 500 Server Error</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="500 page for gpxdb.com. GPXDB is a REST enabled web application database storage and retrieval of gpx data.">
<meta name="author" content="Chris Pauley, Solebury Internet Marketing, LLC">

<!-- Le styles -->
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-responsive.min.css" rel="stylesheet">
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
        <h2>500 - Internal Server Error</h2>
      </header>
      <p>Request unsuccessful because of an unexpected condition encountered by the server.</p>
      <ul class="unstyled">
        <li><p>
          <% out.println(pageContext.getErrorData().getRequestURI()); %></p>
        </li>
        <li><% out.println(pageContext.getErrorData().getStatusCode()); %><li>
         <li><% out.println(pageContext.getException()); %><li>
      </ul>
    </article>
    </div>
</div>
</body>
</html>