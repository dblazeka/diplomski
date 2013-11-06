<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Results</title>
<link href="/zesoi/resources/js/flot/examples/examples.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/zesoi/resources/css/bootstrap.min.css">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<style type="text/css">
.data { white-space: pre; }
</style>
<link rel="stylesheet" href="/resources/js/flot/examples/examples.css">


<style type="text/css">
#placeholder .button {
	position: absolute;
	cursor: pointer;
}

#placeholder div.button {
	font-size: smaller;
	color: #999;
	background-color: #eee;
	padding: 2px;
}


</style>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="../../excanvas.min.js"></script><![endif]-->
<script language="javascript" type="text/javascript"
	src="/zesoi/resources/js/flot/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="/zesoi/resources/js/flot/jquery.flot.js"></script>
<script language="javascript" type="text/javascript"
	src="/zesoi/resources/js/flot/jquery.flot.navigate.js"></script>
<script language="javascript" type="text/javascript"
	src="/zesoi/resources/js/vendor/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {

		// generate data set from a parametric function with a fractal look

		function sumf(f, t, m) {
			var res = 0;
			for ( var i = 1; i < m; ++i) {
				res += f(i * i * t) / (i * i);
			}
			return res;
		}

		var d1 = [];

		<c:forEach items="${ points }" var="point">
			d1.push([${point.x}, ${point.y}]);
		</c:forEach>

		var data = [ d1 ], placeholder = $("#placeholder");

		var plot = $.plot(placeholder, data, {
			series : {
				lines : {
					show : false
				},
				points : {
					show : true
				},
				shadowSize : 0
			},
			xaxis : {
// 				zoomRange : [ 0.1, 10 ],
// 				panRange : [ ${minX} - 1, ${maxX} + 1],
				position : "top"
			},
			yaxis : {
// 				zoomRange : [ 0.1, 10 ],
// 				panRange :  [ ${minY} - 1, ${maxY} + 1],
				transform: function (v) {return -v;},
	            inverseTransform: function (v) {return -v;}
			},
			zoom : {
				interactive : true
			},
			pan : {
				interactive : true
			}
		});

		// show pan/zoom messages to illustrate events

		placeholder.bind("plotpan", function(event, plot) {
			var axes = plot.getAxes();
		});

		placeholder.bind("plotzoom", function(event, plot) {
			var axes = plot.getAxes();
		});

		// add zoom out button

		$("<div class='button' style='right:20px;top:20px'>zoom out</div>")
				.appendTo(placeholder).click(function(event) {
					event.preventDefault();
					plot.zoomOut();
				});

		// and add panning buttons

		// little helper for taking the repetitive work out of placing
		// panning arrows

		function addArrow(dir, right, top, offset) {
			$(
					"<img class='button' src='/zesoi/resources/js/flot/examples/navigate/arrow-" + dir + ".gif' style='right:" + right + "px;top:" + top + "px'>")
					.appendTo(placeholder).click(function(e) {
						e.preventDefault();
						plot.pan(offset);
					});
		}

		addArrow("left", 55, 60, {
			left : -100
		});
		addArrow("right", 25, 60, {
			left : 100
		});
		addArrow("up", 40, 45, {
			top : -100
		});
		addArrow("down", 40, 75, {
			top : 100
		});

		// Add the Flot version string to the footer

	});
</script>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">


</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="http://161.53.64.233:8080/zesoi">SW#</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<c:forEach items="${ allModules }" var="module">
								<li><a href="http://161.53.64.233:8080/zesoi/${ module.name }">${ module.displayName }</a></li>
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



			<div id="content">
				<ul id="myTab" class="nav nav-tabs">
					<li class="active"><a href="#pairstat" data-toggle="tab">Result</a></li>
					<c:if test="${ drawchart }">
					<li class><a href="#flot" data-toggle="tab">Chart</a></li>
					</c:if>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade active in" id="pairstat">
						<div class="text-right">
						<a href="http://161.53.64.233:8080/zesoi/download?jobId=${ jobId }">Download results</a>
						<br>
						</div>
						${ pairstat }
					</div>

					<div class="demo-container tab tab-pane fade" id="flot">
						<div id="placeholder" class="demo-placeholder"></div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
