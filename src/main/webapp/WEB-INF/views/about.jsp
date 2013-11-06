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

<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<link rel="stylesheet" href="resources/css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="resources/css/main.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="resources/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script
	src="http://twitter.github.com/bootstrap/assets/js/bootstrap-tooltip.js"></script>

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
				</a> <a class="brand" href="/zesoi">SW#</a>
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
			<h1 class="text-center">SW#</h1>
			<p>
			SW# (swsharp) is a library for sequence alignment based on CUDA enabled GPUs.
			It utilizes Smith-Waterman algorithm for local alignment, Needleman-Wunsch algorithm
			for global alignment and Hybrid-Waterman algorithm for semi-global alignment.
			</p>
			<p>
			There are three modules you can use:
			</p>
			<dl>
  				<dt><a href="swsharpn">SW#n</a></dt>
  				<dd>Module is used to align nucleotide chains. It is optimized for long chains.</dd>
  				<dt><a href="swsharpn">SW#p</a></dt>
  				<dd>Module is used to align proteins.</dd>
  				<dt><a href="swsharpn">SW#db</a></dt>
  				<dd>Module is used to align protein or nucleotide chains to a database. You can upload your own database or use one of the databases already available. If you wish to align nucleotide chains, please choose a EDNA_FULL as substitution matrix.
  				Using this matrix match will have a score of 5 and mismatch will have a score of -4.
  				</dd>
			</dl>
			<p>
			If you are having problems determining what the parameter of the module represent, hover your mouse over the its label
			and a popover with additional information will appear.

			If you are having trouble determining what should be the input format of the sequence or database, fill the text area with sample.
			</p>

			<p>
			SW# library and this website have been developed at the <a href="http://complex.zesoi.fer.hr" target="blank">Faculty of Electrical Engineering and Computer Science in Zagreb,
			Croatia. </a>
			</p>

			<p>
			SW# library is available as a SourceForge project <a href="http://sourceforge.net/projects/swsharp/" target="blank">here </a>.
			</p>
		</div>
		</div>
		<hr>

		<footer>
			<p>&copy; SW# 2013</p>
		</footer>

	</div>
	<!-- /container -->

	<script>
		window.jQuery
				|| document
						.write('<script src="resources/js/vendor/jquery-1.9.1.min.js"><\/script>')
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
