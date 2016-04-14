<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.codehaus.jettison.json.*" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GPXDB API Usage - ${it.uri} Resource</title>
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
 
 <div class="container" style="margin:0 auto;">
  <div class="row">
      
      <section>
        <div class="page-header">
          <h1>Hello from the ${it.resource} Resource</h1>
          <p>You made a call to this resource using an HTTP GET <br> 
          and the http call had a header was set/defaulted to <code>Accept: text/html</code>
          So here's a how-to page to help.
          </p>
        </div>
        <h2><a href="${it.uri}" target="_self">${it.uri}</a></h2>
        <p>This is the description for the resource <strong>${it.uri}</strong>. 
        <br>
        An http GET will envoke different response depending upon the Response Representation. </p>
        <p>${it.issue}</p>
<%--         <p>${it.json}</p> --%>

        
        <ul>
          <li>
            <h6>GET</h6>
            <p>Query Parameters</p>
            <table class="table table-bordered table-condensed" width="33%">
              <thead>
                <tr>
                  <th>parameter</th>
                  <th>value</th>
                  <th>description</th>
                </tr>
              </thead>
              <tr>
                <td>start</td>
                <td>int</td>
                <td>Starting point, or page. Default is 1.</td>
              </tr>
		    <c:forEach var="params" items="${it.paramArray}">
		     <tr>

		      </tr>
		    </c:forEach>
		    
            </table>
            <p>available response representations:</p>
            <ul class="unstyled">
              <li>200 application/json</li>
              <li>200 application/xml </li>
            </ul>
            <!-- End Response Representations --> 
            
          </li>
        </ul>
        <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- END SECTION for the description -->
      
      <section>
      	<div class="container">
	 	<div class="row">
 	     <div class="span2">
	      <h3>source</h3>
	       <ul class="unstyled">
	       	<li><button id="btnFetchJson" class="btn btn-small" type="submit">FetchJson</button></li>
	       	<li><button id="btnFetchXml" class="btn btn-small" type="submit">btnFetchXml</button></li>
	       	<li><button id="btnFetchAtom" class="btn btn-small" type="submit">btnFetchAtom</button></li>
	       	<li><button id="btnClear" class="btn btn-small" type="submit">btnClear</button></li>
	       </ul>
	     </div>

	     <div id="target" class="span8">
	      <h3>target</h3>
	      <p>These headings and the text area show data retrieve from the result and the raw data.</p>
	      
	      <textarea id="result" class="span8" name="result" cols="300" rows="5"></textarea>

	     </div>
	    
	  </div>
	  <hr>
	</div>
       <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- END SECTION for the sample calls -->

 </div>
 </div>
<jsp:include page="/footer.jsp" />
 <script src="${pageContext.request.contextPath}/js/lib/jquery.js"></script>
 <script src="${pageContext.request.contextPath}/js/lib/json2.js"></script>
 <script src="${pageContext.request.contextPath}/js/lib/bootstrap.min.js"></script>
 
 <script>
 var rootURL = "${it.uri}";
 var dataObject;
 $('#btnFetchAtom').click(function() {
	 console.log('btnFetchAtom');
		$.ajax({
			type: 'GET',
			url: rootURL,
			contentType: 'application/atom+xml',
			dataType: "xml", // data type of response
		    error: function(XMLHttpRequest, textStatus, errorThrown){
		       alert(errorThrown);},
			success: renderXml
		});	
 });

 $('#btnFetchXml').click(function() {
	 console.log('btnFetchXml');
		$.ajax({
			type: 'GET',
			url: rootURL,
			dataType: "xml", // data type of response
			success: renderXml
		});	
 });
		
 $('#btnFetchJson').click(function() {
 	console.log('btnFetchJson');
 	$.ajax({
 		type: 'GET',
 		url: rootURL,
 		dataType: "json", // data type of response
 		success: renderJson
 	});	
 });

 function resetResultTarget() {
	console.log('resetResult');
	$('#result').html('');
	$('textarea#result').val('');
 }
 function renderJson(data){
	resetResultTarget();
	dataObject = data;
	$('#result').val(JSON.stringify(data));
 }

 function renderXml(data){
	 console.log('renderXml');
	var xml = $(data);
	var xmlString = data.xml ? data.xml : (new XMLSerializer()).serializeToString(data);
	resetResultTarget();
	$('textarea#result').val(formatXml(xmlString));
 }

	function formatXml(xml) {
	    var formatted = '';
	    var reg = /(>)(<)(\/*)/g;
	    xml = xml.replace(reg, '$1\r\n$2$3');
	    var pad = 0;
	    jQuery.each(xml.split('\r\n'), function(index, node) {
	        var indent = 0;
	        if (node.match( /.+<\/\w[^>]*>$/ )) {
	            indent = 0;
	        } else if (node.match( /^<\/\w/ )) {
	            if (pad != 0) {
	                pad -= 1;
	            }
	        } else if (node.match( /^<\w[^>]*[^\/]>.*$/ )) {
	            indent = 1;
	        } else {
	            indent = 0;
	        }

	        var padding = '';
	        for (var i = 0; i < pad; i++) {
	            padding += ' ';
	        }

	        formatted += padding + node + '\r\n';
	        pad += indent;
	    });

	    return formatted;
	}
 $(document).ready(function() {
	    console.log('ready');

		$('#btnClear').click(function() {
			resetResultTarget();
		});

	});
	 
 </script>
</body>
</html>