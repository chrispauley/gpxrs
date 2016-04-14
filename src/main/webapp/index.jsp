<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GPXDB Webapp - Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Home page for gpxdb.com. GPXDB is a REST enabled web application database storage and retrieval of gpx data.">
<meta name="author" content="Chris Pauley, Solebury Internet Marketing, LLC">
 
<!-- Le styles -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="ico/apple-touch-icon-57-precomposed.png">
</head>
<body>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> </a> <a
					class="brand" href="index"><i class="icon-hdd"></i>&nbsp;gpxdb</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li class="active"><a href="index"><i class="icon-home"></i>&nbsp;Home</a>
						</li>
						<li><a href="apidocs">Api Docs</a>
						</li>
						<li><a href="about">About</a>
						</li>
						<li><a href="contact">Contact</a>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container">
		<div class="hero-unit">
			<h1>gpx database</h1>
			<p>Welcome. This is a database engine for gpx formatted location
				data with a RESTful api. </p>

			<p>
				<a href="about" class="btn btn-primary btn-large"> Learn more </a>
			</p>
		</div>

		<!-- Example row of columns -->
		<div class="row">
			<div class="span4">
				<h2>about</h2>
				<p>Gpx is an open standards xml format for exchanging gps data. Datatypes include waypoints, routes and tracks.</p>
                <p>This code provides structure for gpx data in a relational database.</p>

			</div>
	
		</div>

		<hr>

		<footer>
		<p>&copy; SIMLLC 2016</p>
		</footer>


	</div>
	<!-- /container -->
	<script src="js/lib/jquery.js"></script>
	<script src="js/lib/bootstrap.js"></script>	
</body>
</html>

