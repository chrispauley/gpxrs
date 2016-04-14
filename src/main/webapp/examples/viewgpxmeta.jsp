<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GpxDb.com Examples - View GPX Meta Data</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Example for gpxdb.com. GPXDB is a REST enabled web application database storage and retrieval of gpx data.">
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
<script src="../js/lib/json2.js"></script>
<script src="../js/lib/jquery.tmpl.js"></script>
<script src="../js/lib/underscore.js"></script>
<script src="../js/lib/backbone.js"></script>
<script src="../js/lib/backbone.localstorage.js"></script>
<script src="../js/app.js"></script>
</head>

  <body>
<%@ include file="/page-nav.jsp" %>

	<div class="container">
	 <div class="row">
	    <div class="span2">
	      <h3>source</h3>
	       <ul>
	       	<li><button id="btnFetchJson" class="btn btn-small" type="submit">Fetch</button></li>
	       	<li><button id="btnClear" class="btn" btn-small type="submit">Clear</button></li>
	       	<li><button id="btnShow" class="btn" btn-small type="submit">btnShow</button></li>
	       </ul>
	    </div>

	    <div id="target" class="span8">
	      <h3>target</h3>
	      <p>These headings and the text area show data retrieve from the result and the raw data.</p>
	      <h4 id="FormattedName"></h4>
	      <h4 id="Objective"></h4>
	      <h4 id="EmploymentHistory"></h4>
	      
	      <textarea id="result" class="span8" name="result" cols="300" rows="5"></textarea>

	      <div id="divMetadata"></div>
	    </div>
	    
	  </div>
	  <hr>
	</div>

	<script id="template_Metadata" type="text/template">
		<div class="control-group">
			<label class="control-label" for="Metadata">Metadata</label>
			<div class="controls">
				<textarea id="Metadata" class="span6"
					name="Metadata" cols="300" rows="2">${metadata}</textarea>
			</div>
		</div>
	</script>


</body>
</html>
