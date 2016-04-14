<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GPXDB Webapp - Contact</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Contact page for gpxdb.com. GPXDB is a REST enabled web application database storage and retrieval of gpx data.">
<meta name="author" content="Chris Pauley, Solebury Internet Marketing, LLC">

<!-- Le styles -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
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
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container"> <a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> </a> <a
					class="brand" href="index"><i class="icon-hdd"></i>&nbsp;gpxdb</a>
      <div class="nav-collapse">
        <ul class="nav">
          <li><a href="index"><i class="icon-home">&nbsp;</i>Home</a></li>
          <li><a href="apidocs">Api Docs</a></li>
          <li><a href="about">About</a></li>
          <li class="active"><a href="contact">Contact</a></li>
        </ul>
      </div>
      <!--/.nav-collapse --> 
    </div>
  </div>
</div>
<div class="container">
  <div class="row-fluid">
    <h1>Contact us at gpxdb.com</h1>
    <hr>
  </div>
  <div class="row-fluid">
    <div class="offset2 span3">
      <div>
        <address>
        Solebury Internet Marketing<br>
        PO Box 340<br>
        Solebury, PA 18938<br>
        P: (267)714-8647<br>
        Email: <a href="mailto:chris@soleburyinternetmarketing.com">chris@soleburyinternetmarketing.com</a>
        </address>
      </div>
    </div>
    <!-- 
    <div class="span7">
      <form method="post" action="contactform">
        <div class="controls controls-row">
          <input id="name" name="name" type="text" class="span3" placeholder="Name">
          <input id="email" name="email" type="email" class="span3" placeholder="Email address">
        </div>
        <div class="controls">
          <textarea id="message" name="message" class="span6" placeholder="Your Message" rows="5"></textarea>
        </div>
        <div class="controls">
          <button id="contact-submit" type="submit" class="btn btn-primary input-medium pull-left">Send</button>
        </div>
      </form>
    </div>
     -->
  </div>
  <hr>
  <footer>
    <%@include file='footer.jsp'%>
  </footer>
</div>
<!-- /container --> 

<!-- Le javascript
    ================================================== --> 
<!-- Placed at the end of the document so the pages load faster --> 
<script src="js/lib/jquery.js"></script> 
<script src="js/lib/bootstrap.min.js"></script>
</body>
</html>
