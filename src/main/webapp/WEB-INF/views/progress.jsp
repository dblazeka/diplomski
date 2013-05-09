<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SW#</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="/zesoi/resources/css/bootstrap.min.css">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<link rel="stylesheet" href="/zesoi/resources/css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="/zesoi/resources/css/main.css">

<script src="/zesoi/resources/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

<script>
setInterval(function(){
	  $.ajax({
	    url: '/zesoi/results/' + "${ jobId }",
	    success: function(data){
	      console.log("success");
	      window.location = '/zesoi/results/' + "${ jobId }" + "/show";
	    }
	  });
	}, 1000);
</script>

</head>
<body>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">SW#</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<c:forEach items="${ allModules }" var="module">
							<c:if test="${module.isActive}">
								<li class="active"><a href=<c:out value="${module.name}" />><c:out
											value="${module.displayName}" /></a></li>
							</c:if>
							<c:if test="${!module.isActive}">
								<li><a href=<c:out value="${module.name}" />><c:out
											value="${module.displayName}" /></a></li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container">

		<!-- Example row of columns -->
		<div class="row-fluid">
			<div class="span6 offset3">
				<div class="progress progress-striped active">
					<div class="bar" style="width: 100%;"></div>
				</div>
			</div>

			<p>${ jobId }</p>
			<p>temp ${ usrtmp }</p>
		</div>

		<hr>

		<footer>
			<p>&copy; SW# 2013</p>
		</footer>

	</div>
	<!-- /container -->

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="/zesoi/resources/js/vendor/jquery-1.9.1.min.js"><\/script>')
	</script>

	<script src="resources/js/vendor/bootstrap.min.js"></script>

	<script src="resources/js/main.js"></script>

	<script>
		var _gaq = [ [ '_setAccount', 'UA-XXXXX-X' ], [ '_trackPageview' ] ];
		(function(d, t) {
			var g = d.createElement(t), s = d.getElementsByTagName(t)[0];
			g.src = ('https:' == location.protocol ? '//ssl' : '//www')
					+ '.google-analytics.com/ga.js';
			s.parentNode.insertBefore(g, s)
		}(document, 'script'));
	</script>
</body>
</html>
