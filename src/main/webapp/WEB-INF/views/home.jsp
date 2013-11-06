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
			<form id="form1" method="post" action="${ module.name }/submit"
				enctype="multipart/form-data">
				<div class="span6 offset3">
					<fieldset>
						<legend>${ module.name }</legend>
						<div class="control-group">
							<c:forEach items="${ moduleParameters }" var="parameter">
									<script type="text/javascript">
										$(document).ready(function() {
											$("#${ parameter.name }").tooltip({
												'selector' : '',
												'placement' : 'top',
												'title' : "${ parameter.tooltipText }",
												container: 'body'
											});

											console.log('yaddda');
										});
									</script>
								<c:if test="${ !parameter.hidden }">
									<c:if
										test="${parameter.type == 'text' || parameter.type == 'file'}">
										<label class="control-label" for="${ parameter.name }" id="${ parameter.name }">${
											parameter.displayName }</label>
										<div class="controls">
											<input type="${ parameter.type }" class="input-xlarge"
												name="${ parameter.name } "
												value="${ parameter.defaultValue }" />
										</div>
									</c:if>

									<c:if test="${parameter.type == 'select' }">
										<label class="control-label" for="${ parameter.name }" id="${ parameter.name }">${
											parameter.displayName }</label>
										<div class="controls">
											<select name="${ parameter.name } ">
												<c:forEach items="${ parameter.options }" var="option">
													<c:if test="${parameter.defaultValue == option }">
														<option value="${ option }" selected>${ option }</option>
													</c:if>
													<c:if test="${parameter.defaultValue != option }">
														<option value="${ option }">${ option }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
									</c:if>
									<c:if test="${parameter.type == 'textfile' }">
										<label class="control-label" for="${ parameter.name }" id="${ parameter.name }">${
											parameter.displayName }</label>
										<div class="controls">
											<textarea rows="7" cols="47"
												name="${ parameter.name }${ parameter.textSuffix }"
												class="span12"
												id="${ parameter.name }${ parameter.textSuffix }"
												></textarea>
											<input type="file" class="input-xlarge"
												name="${ parameter.name }${ parameter.fileSuffix }" />
											<a href="#" id="${ parameter.name }fill">Fill sample</a>
											<script type="text/javascript">
											$(document).ready(function() {
												$("#${ parameter.name }fill" ).click(function() {
													$("#${ parameter.name }${ parameter.textSuffix }").val("${ parameter.defaultValue }");

													console.log('yaddda');
												});
											});
											</script>
										</div>
									</c:if>
								</c:if>
							</c:forEach>
							<c:if test="${ module.usesDatabase }">
							<label class="control-label" for="database" id="database">Target database</label>
														<div class="controls">
															<select name="database">
																		<option value="none" selected>None</option>
																<c:forEach items="${ module.databases }" var="db">
																		<option value="${ db.name }">${ db.name }</option>
																</c:forEach>
															</select>
														</div>
														<script type="text/javascript">
										$(document).ready(function() {
											$("#database").tooltip({
												'selector' : '',
												'placement' : 'top',
												'title' : "Use existing database as target",
												container: 'body'
											});

											console.log('yaddda');
										});
									</script>

							</c:if>

							<c:if test="${ module.complementOption }">
								<label class="checkbox" id="complement">
									<input type="checkbox" name="complement" value="complement">
									Calculate for complement
								</label>
								<script type="text/javascript">
										$(document).ready(function() {
											$("#complement").tooltip({
												'selector' : '',
												'placement' : 'top',
												'title' : "Take complements into consideration while performing aligment",
												container: 'body'
											});

											console.log('yaddda');
										});
									</script>
							</c:if>

							<div class="accordion" id="accordion2">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" data-toggle="collapse"
											data-parent="#accordion2" href="#collapseOne"> <i
											class="icon-chevron-right"></i> Additional options
										</a>
									</div>
									<div id="collapseOne" class="accordion-body collapse in">
										<div class="accordion-inner">
											<c:forEach items="${ moduleParameters }" var="parameter">
												<c:if test="${ parameter.hidden }">
													<c:if
														test="${parameter.type == 'text' || parameter.type == 'file'}">
														<label class="control-label" for="${ parameter.name }" id="${ parameter.name }">${
															parameter.displayName }</label>
														<div class="controls">
															<input type="${ parameter.type }" class="input-xlarge"
																name="${ parameter.name } "
																value="${ parameter.defaultValue }" />
														</div>
													</c:if>
													<c:if test="${parameter.type == 'select' }">
														<label class="control-label" for="${ parameter.name }" id="${ parameter.name }">${
															parameter.displayName }</label>
														<div class="controls">
															<select name="${ parameter.name } ">
																<c:forEach items="${ parameter.options }" var="option">
																	<c:if test="${parameter.defaultValue == option }">
																		<option value="${ option }" selected>${
																			option }</option>
																	</c:if>
																	<c:if test="${parameter.defaultValue != option }">
																		<option value="${ option }">${ option }</option>
																	</c:if>
																</c:forEach>
															</select>
														</div>
													</c:if>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
							<div class="controls">
								<label class="checkbox"> <input type="checkbox"
									name="sendEmail" value="send"> Notify me by e-mail when
									done
								</label> <label for="email">E-mail</label> <input type="email"
									name="email" class="span9" />
							</div>
						</div>
						<input type="submit" value="Submit" class="btn btn-primary" />
					</fieldset>
				</div>
			</form>
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
