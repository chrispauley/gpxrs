<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GPXDB Webapp - About</title>
<meta name="description" content="API documentation page for gpxdb.com. GPXDB is a REST enabled web application database storage and retrieval of gpx data.">
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
<div id="top" class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="index"><i class="icon-hdd">&nbsp;</i>gpxdb</a>
      <div class="nav-collapse">
        <ul class="nav">
          <li><a href="index"><i class="icon-home">&nbsp;</i>Home</a></li>
          <li class="active"><a href="apidocs">Api Docs</a>
          <li><a href="about">About</a></li>
          <li><a href="contact">Contact</a></li>
        </ul>
      </div>
      <!--/.nav-collapse --> 
    </div>
  </div>
</div>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span3 bs-docs-sidebar"> 
      <!--Sidebar content-->
      <ul class="nav nav-list bs-docs-sidenav affix">
        <li class="active"> <a href="#api-getting-started"> <i class="icon-chevron-right"></i> Getting Started </a> </li>
        <li> <a href="#api-structure"> <i class="icon-chevron-right"></i> API Structure </a> </li>
        <li> <a href="#api-authentication"> <i class="icon-chevron-right"></i> API Authentication </a> </li>
        <li> <a href="#api-index"> <i class="icon-chevron-right"></i> API Index </a> </li>
        <li> <a href="#gpxs"> <i class="icon-chevron-right"></i> GpxsResource </a> </li>
        <li> <a href="#gpx"> <i class="icon-chevron-right"></i> GpxResource </a> </li>
        <li> <a href="#rtes"> <i class="icon-chevron-right"></i> RtesResource </a> </li>
        <li> <a href="#rte"> <i class="icon-chevron-right"></i> RteResource </a> </li>
        <li> <a href="#"> <i class="icon-chevron-right"></i> Metadata </a> </li>
      </ul>
    </div>
    
    <!--START DOCUMENTATION-->
    <div class="span9">
      <section id="api-top">
        <div class="page-header">
          <h1>GPXDB.COM <small>REST API Documentation</small></h1>
        </div>
        <h2>A database engine for gpx formatted location data.</h2>
        <p>Add the power of a relational database gpx data. This enables users to add tracks, convert tracks to routes, edit and manage waypoints, create
          tourguide, and publish points of interest in a gpx format. </p>
      </section>
      <!-- END SECTION #api-top-->
      <section id="api-getting-started">
        <div class="page-header">
          <h1>Getting Started <small>How to use the API</small></h1>
        </div>
        <h2>What you need</h2>
        <p>Because the REST API is based on open standards, you can use any web development 
          language to access the API. This includes clients using
          ActionScript, Javascript, Java, IOS, Android... any client that can make calls using HTTP. </p>
        <p>For more information, please see: <a href="https://blog.apigee.com/detail/slides_for_restful_api_design_second_edition_webinar/">RESTful Design slides by Apigee</a></p>
        <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- -->
      <section id="api-structure">
        <div class="page-header">
          <h1>Structure of the REST URIs</h1>
        </div>
        <h2>What you need</h2>
        <p>Because the REST API is based on open standards, you can use any web development language to access the API. This includes clients using
          ActionScript, Javascript, Java, IOS, Android... any client that can make calls using HTTP. </p>
        <p>GPXDB's REST APIs provide access to resources (data entities) via URI paths. To use a REST API, your application will make an HTTP request and parse the response. The GPXDB REST API uses JSON and XML as its communication format, and the standard HTTP methods like GET, PUT, POST and DELETE (see API descriptions below for which methods are available for each resource). URIs for GPX's REST API resource have the following structure: </p>
        <div class="bs-docs-example">
          <pre>http://host:port/context/rest/api-name/api-version/resource-name</pre>
          <p>This translates to:</p>
          <pre>https://rest.gpxdb.com/gpxrs/v1/gpxs
           and for authentication: http://gpxdb.com/gpxrs/auth/v1/signon </pre>
        </div>
        <p>There is a <a href="http://en.wikipedia.org/wiki/Web_Application_Description_Language" title="Wikipedia Description of WADL" target="_new">WAD</a>L document that contains the documentation for each resource in the GPXDB REST API. It is available here: <a href="http://gpxdb.com/gpxrs/rest/application.wadl" title="Link to gpxdb web application description xml" target="_new">http://gpxdb.com/gpxrs/rest/application.wadl</a></p>
        <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- END api-structure  -->
      <section id="api-authentication">
        <div class="page-header"> Authentication<small>&nbsp;Control your data</small></div>
        <p>The prefered authentication methods are HTTP Basic (when using SSL) and OAuth, which are both documented in the GPXDB REST API Tutorials. Other supported methods include: HTTP Cookies<strong>, Trusted Applications, and os_username/os_password query parameters</strong>.</p>
        <p>The log-in page uses cookie-based authentication, so if you are using GPXDB in a browser you can call REST from Javascript on the page and rely on the authentication that the browser has established. Callers wanting to reproduce the behaviour of the GPXDB log-in page (for example, to display authentication error messages to users) or who are calling both the REST and SOAP API can POST to the /auth/1/session resource as per the documentation below.</p>
        <div class="bs-docs-example">
          <p>You can find OAuth code samples in several programming languages at bitbucket.org/rmanalan/atlassian-oauth-examples. </p>
          <p>cookies hold state in the client.</p>
        </div>
        <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- END api-authentication -->
      
      <section id="api-index">
        <h1>API Index<small>&nbsp;Links to specific GPXDB REST Api</small></h1>
        <h2></h2>
        <p>This documents the current REST API provided by GPXDB. </p>
        <ul>
          <li>Resources
            <ul>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/gpxs [<a href="#gpxs" target="_self">GET</a>]</li>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/gpx [<a href="#gpx" target="_self">GET</a>|PUT|POST|DELETE]</li>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/wpts [GET]</li>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/wpt [GET|PUT|POST|DELETE]</li>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/rtes [GET]</li>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/rte [GET|PUT|POST|DELETE]</li>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/trks [GET]</li>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/trk [GET|PUT|POST|DELETE]</li>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/resource_template [<a href="#resource-get">GET</a> |<a href="#resource-put" target="_self">PUT</a> |<a href="#resource_post">POST</a> |<a href="#resource_delete">DELETE</a> |OPTIONS]</li>
            </ul>
          </li>
          <li>Auth
            <ul>
              <li>http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/auth/v1/signon [GET|POST]</li>
            </ul>
          </li>
        </ul>
        <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- END api-index -->
      <section id="gpxs">
        <div class="page-header">
          <h2>Gpxs Resource</h2>
        </div>
        <h2><a href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/v1/gpxs" target="_self">gpxrs/v1/gpxs</a></h2>
        <p>This is the description for the resource <strong>gpxs</strong>. An http GET will envoke different response depending upon the Response Representation. Only partial data is returned: the &lt;gpx&gt;&lt;metadata/&gt;&lt;/gpx&gt;. Waypoints, routes and tracks are omitted by design. </p>
        <pre>The list of gpx data includes a wrapper &lt;gpxes&gt;&lt;gpx&gt;&lt;metadata/&gt;&lt;/gpx&gt;&lt;/gpxes&gt;.
        </pre>
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
              <tr>
                <td>limit</td>
                <td>int</td>
                <td>Number of values in page. Default is 10.</td>
              </tr>
              <tr>
                <td>orderby</td>
                <td>String</td>
                <td>Results can be returned by specified order. <br>
                  orderby=[name|datecreated]</td>
              </tr>
            </table>
            <p>available response representations:</p>
            <ul class="unstyled">
              <li>200 application/json</li>
              <li>200 application/xml </li>
              <li>
                <div id="myCollapsibleExample">200 application/atom+xml [<a href="#atom-feed" data-toggle="collapse">Expand</a>]</div>
                <div id="atom-feed" class="collapse"> Atom Feed listing of the gpx documents in the database. Contains links to each gpx.</div>
              </li>
              <li>
                <div id="myCollapsibleExample">400 [<a href="#demo" data-toggle="collapse">Expand</a>]</div>
                <div id="demo" class="collapse"> Returned if the version does not exist or the currently authenticated user does not have permission to view it.</div>
              </li>
            </ul>
            <!-- End Response Representations --> 
            
          </li>
        </ul>
        <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- END SECTION gpxs -->
      
      <section id="gpx">
        <div class="page-header">
          <h1>GpxResource<small>&nbsp;the core resource</small></h1>
        </div>
        <p>The gpx resource enables CRUD functions on a gpx</p>
        <h2>gpxrs/v1/gpx/{gpxid}</h2>
        <ul>
          <li>
            <h6>GET</h6>
            <p>Query Parameters</p>
            <table class="table table-bordered table-condensed" width="400px">
              <thead>
                <tr>
                  <th>parameter</th>
                  <th>value</th>
                  <th>description</th>
                </tr>
              </thead>
              <tr>
                <td>gpxid</td>
                <td>int</td>
                <td>Internal id for the gpx.</td>
              </tr>
            </table>
            <p>gpx element definition: <a class="btn-info btn-small" href="http://www.topografix.com/GPX/1/1/#element_gpx" title="Link to the element definition in the schema." target="_blank">gpx element in the schema</a></p>
            <p>available response representations:</p>
            <ul>
              <li>200 application/json [expand]</li>
              <li>200 application/xml [expand]</li>
              <li>
                <div id="gpx-json">400 [<a href="#gpx-json-detail" data-toggle="collapse">Expand</a>]</div>
                <div id="gpx-json-detail" class="collapse"> Returned if the version does not exist or the currently authenticated user does not have permission to view it.</div>
              </li>
            </ul>
            <!-- End Response Representations --> 
            
          </li>
        </ul>
        <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- END SECTION gpx -->
      
      <section id="rtes">
        <div class="page-header">
          <h1>Routes Resource<small>&nbsp;control routes</small></h1>
        </div>
        <h2>gpxrs/v1/rtes</h2>
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
              <tr>
                <td>limit</td>
                <td>int</td>
                <td>Number of values in page. Default is 10.</td>
              </tr>
              <tr>
                <td>orderby</td>
                <td>String</td>
                <td>Results can be returned by specified order. <br>
                  orderby=[name|datecreated]</td>
              </tr>
            </table>
            <p>rte element definition: <a class="btn-info btn-small" href="http://www.topografix.com/GPX/1/1/#type_rteType" title="Link to the element definition in the schema." target="_blank">rte element in the schema</a></p>
            <p>available response representations:</p>
            <ul class="unstyled">
              <li>200 application/json [expand]</li>
              <li>
                <div id="rtes-example">400 [<a href="#rtes-detail" data-toggle="collapse">Expand</a>]</div>
                <div id="rtes-detail" class="collapse"> Returned if the version does not exist or the currently authenticated user does not have permission to view it.</div>
              </li>
            </ul>
            <!-- End Response Representations --> 
            
          </li>
        </ul>
        <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- END SECTION rtes -->
      
      <section id="rte">
        <div class="page-header">
          <h1>Route Resource<small>&nbsp;control route</small></h1>
        </div>
        <h2>gpxrs/v1/rte/{rteid}</h2>
        <ul>
          <li>
            <h6>GET</h6>
            <p>Query Parameters</p>
            <table class="table table-bordered table-condensed" width="400px">
              <thead>
                <tr>
                  <th>parameter</th>
                  <th>value</th>
                  <th>description</th>
                </tr>
              </thead>
              <tr>
                <td>rteid</td>
                <td>int</td>
                <td>Internal id for the rte.</td>
              </tr>
            </table>
            <p>rte element definition: <a class="btn-info btn-small" href="http://www.topografix.com/GPX/1/1/#type_rteType" title="Link to the element definition in the schema." target="_blank">rte element in the schema</a></p>
            <p>available response representations:</p>
            <ul>
              <li>200 application/json [expand]</li>
              <li>200 application/xml [expand]</li>
              <li>
                <div id="rte-json">400 [<a href="#rte-json-detail" data-toggle="collapse">Expand</a>]</div>
                <div id="rte-json-detail" class="collapse"> Returned if the version does not exist or the currently authenticated user does not have permission to view it.</div>
              </li>
            </ul>
            <!-- End Response Representations --> 
            
          </li>
        </ul>
        <p class="pull-right"> <a href="#">Back to top</a> </p>
      </section>
      <!-- END SECTION rte --> 
    </div>
    <!-- END content on right --> 
  </div>
</div>
<div class="container"> 
  
  <!-- row -->
  
  <hr>
  <footer>
    <p>&copy; SIMLLC 2014</p>
  </footer>
</div>
<!-- /container --> 

<!-- Le javascript
    ================================================== --> 
<!-- Placed at the end of the document so the pages load faster --> 
<script src="js/lib/jquery.js"></script> 
<script src="js/lib/bootstrap.min.js"></script> 
<script>
	 function bootstrap_tab_bookmark (selector) { if (selector == undefined) {
    selector = ""; }

    /* Automagically jump on good tab based on anchor */
    $(document).ready(function() {
        url = document.location.href.split('#');
        if(url[1] != undefined) {
            $(selector + '[href=#'+url[1]+']').tab('show');
        }
    });

    var update_location = function (event) {
        document.location.hash = this.getAttribute("href");
    }

    /* Update hash based on tab */
    $(selector + "[data-toggle=pill]").click(update_location);
    $(selector + "[data-toggle=tab]").click(update_location);
}
     $(document).ready(function(){
     });
   </script>
</body>
</html>
