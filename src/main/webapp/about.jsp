<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GPXDB Webapp - About</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="About page for gpxdb.com. GPXDB is a REST enabled web application database storage and retrieval of gpx data.">
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
              <li><a href="index"><i class="icon-home"></i>&nbsp;Home</a></li>
              <li><a href="apidocs">Api Docs</a> </li>
              <li class="active"><a href="about">About</a> </li>
              <li><a href="contact">Contact</a> </li>
            </ul>
      </div>
          <!--/.nav-collapse --> 
        </div>
  </div>
    </div>
    
<div class="container">
      <div class="row-fluid">
    <div class="span9 offset1">
          <h1>about gpxdb.com</h1>
          <h2>A database engine for gpx formatted location data. </h2>
          <blockquote>
        <p> GPX, or GPS eXchange Format is an XML schema designed as a common GPS data format for software applications.
              It can be used to describe waypoints, tracks, and routes. The format is open and can be used without the need to pay license fees. Its tags store location, elevation, and time and can in this way be used to interchange data between GPS devices and software packages. Such computer programs allow users, for example, to view their tracks, project their tracks on satellite images or other maps, annotate maps, and tag photographs with the geolocation in the Exif metadata. </p>
        <small><a href="https://en.wikipedia.org/wiki/GPS_eXchange_Format" title="Wikipedia description of GPX." target="_blank">--Wikipedia</a> description.</small> </blockquote>
        <h2>More Background Information about GPX</h2>
        <p>The GPX format has been around for awhile and it is widely accepted. <br>
        Here's a link to the Topografix site: 
        <a href="http://www.topografix.com/gpx.asp" title="Topografix Website Link" target="_blank">http://www.topografix.com/gpx.asp</a></p>
        
        <h2>What is the purpose of gpx db? </h2>
        <p>GPX DB is a web application that uses a REST style interface to store and retrieve gpx data in xml or json format.</p>
        </div>
  </div>
      <hr>
      <footer>
    <p>&copy; SIMLLC 2016</p>
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
