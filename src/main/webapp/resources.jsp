<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GPXDB Webapp - Resources</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Resources page for gpxdb.com. GPXDB is a REST enabled web application database storage and retrieval of gpx data.">
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
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> </a> <a
					class="brand" href="index"><i class="icon-hdd">&nbsp;</i>gpxdb</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li class="active"><a href="index"><i class="icon-home">&nbsp;</i>Home</a>
						</li>
						<li><a href="resources">Documentation</a>
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

      <h1>gpxdb.com</h1>
      <p>A database engine for gpx formatted location data.</p>
      
      	<ol>
      	<li><a href="/gpxrs/v1/gpx/gpxcart">/gpxrs/v1/gpx/gpxcart</a></li>
		<li><a href="/gpxrs/v1/lov">/gpxrs/v1/lov</a></li>
		<li><a href="/gpxrs/v1/wpt">/gpxrs/v1/wpt</a></li>
		<li><a href="/gpxrs/v1/gpx">/gpxrs/v1/gpx</a></li>
		<li><a href="/gpxrs/v1/gpxs">/gpxrs/v1/gpxs</a></li>
		<li><a href="/gpxrs/v1/version">/gpxrs/v1/version</a></li>
		<li><a href="/gpxrs/v1/my">/gpxrs/v1/my/[userid]</a></li>
	</ol>
	<h2>ClientGpxResource</h2>
	<h3>ClientGpxResource Collections</h3>
	<ol>
		<li><a href="/gpxrs/v1/my">/gpxrs/v1/my</a></li>
		<li><a href="/gpxrs/v1/my/gpxs?start=0&limit=10">/gpxrs/v1/my/gpxs</a></li>
		<li><a href="/gpxrs/v1/my/rtes?start=0&limit=10">/gpxrs/v1/my/rtes</a></li>
		<li><a href="/gpxrs/v1/my/rtepts?start=0&limit=10">/gpxrs/v1/my/rtepts</a></li>
		<li><a href="/gpxrs/v1/my/trks?start=0&limit=10">/gpxrs/v1/my/trks</a></li>
		<li><a href="/gpxrs/v1/my/trksegs?start=0&limit=10">/gpxrs/v1/my/trksegs</a></li>
		<li><a href="/gpxrs/v1/my/trkpts?start=0&limit=10">/gpxrs/v1/my/trkpts</a></li>
		<li><a href="/gpxrs/v1/my/wpts?start=0&limit=10">/gpxrs/v1/my/wpts</a></li>
	</ol>
	<h3>ClientGpxResource Objects</h3>
	<ol>
		<li><a href="/gpxrs/v1/my/gpx/15">/gpxrs/v1/my/gpx/1</a></li>
		<li><a href="/gpxrs/v1/my/rte/10">/gpxrs/v1/my/rte/10</a></li>
		<li><a href="/gpxrs/v1/my/rtept/10">/gpxrs/v1/my/rtept/10</a></li>
		<li><a href="/gpxrs/v1/my/trk/10">/gpxrs/v1/my/trk/10</a></li>
		<li><a href="/gpxrs/v1/my/trkseg/10">/gpxrs/v1/my/trkseg/10</a></li>
		<li><a href="/gpxrs/v1/my/trkpt/10">/gpxrs/v1/my/trkpt/10</a></li>
		<li><a href="/gpxrs/v1/my/wpt/15">/gpxrs/v1/my/wpt/15</a></li>
	</ol>
	
	<h3>ClientGpxResource Gpx Objects</h3>
	<ol>
		<li><a href="/gpxrs/v1/my/gpx/15/metadata">/gpxrs/v1/my/gpx/1/metadata</a></li>
		<li><a href="/gpxrs/v1/my/gpx/15/wpt/2">/gpxrs/v1/my/gpx/15/wpt/2</a></li>
		<li><a href="/gpxrs/v1/my/gpx/15/rte/1">/gpxrs/v1/my/gpx/15/rte/1</a></li>	
		<li><a href="/gpxrs/v1/my/gpx/15/trk/3">/gpxrs/v1/my/gpx/15/trk/3</a></li>		
		<li><a href="/gpxrs/v1/my/gpx/15/extensions/1">/gpxrs/v1/my/gpx/15/extensions/1</a></li>
		</ol>
	
	<h3>ClientGpxResource Metadata Objects</h3>
	<ol>
		<li><a href="/gpxrs/v1/my/gpx/15/metadata">/gpxrs/v1/my/gpx/1/metadata</a></li>
		<li><a href="/gpxrs/v1/my/gpx/15/metadata/author">/gpxrs/v1/my/gpx/1/metadata/author</a></li>
		<li><a href="/gpxrs/v1/my/gpx/15/metadata/bounds?minlat=42.12&minlon=-74.3&maxlat=43.5&maxlon=-76.75">/gpxrs/v1/my/gpx/1/metadata/bounds</a></li>
		<li><a href="/gpxrs/v1/my/gpx/15/metadata/extensions">/gpxrs/v1/my/gpx/1/metadata/extensions</a></li>
		<li><a href="/gpxrs/v1/my/gpx/15/metadata/keywords">/gpxrs/v1/my/gpx/1/metadata/keywords</a></li>
		<li><a href="/gpxrs/v1/my/gpx/15/metadata/links">/gpxrs/v1/my/gpx/1/metadata/links</a></li>
		<li><a href="/gpxrs/v1/my/responsebuilder">/gpxrs/v1/my/responsebuilder</a></li>
	</ol>
	
	<hr>

	<footer>
	<p>&copy; SIMLLC 2016</p>
	</footer>
		
    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <script src="js/lib/jquery.js"></script>
    <script src="js/lib/bootstrap.min.js"></script>

  </body>
</html>
  