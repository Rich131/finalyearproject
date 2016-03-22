<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">AutoReporter</a>
		</div>

		<script>
			// jQuery to set nav tab as active when clicked
			$(document).ready(function() {
				var url = window.location;
				
				$('ul.nav a[href="' + url + '"]').parent().addClass('active');
				
				$('ul.nav a').filter(function() {
					return this.href == url;
				}).parent().addClass('active');
			})
		</script>

		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right" id="nav">
				<li id="home-nav"><a
					href="${ pageContext.request.contextPath }/home">Home</a></li>
				<li id="emp-nav"><a
					href="${ pageContext.request.contextPath }/employees">Employees</a></li>
				<li id="sim-nav"><a
					href="${ pageContext.request.contextPath }/simulator">Simulator</a></li>
			</ul>

		</div>
		<!--/.nav-collapse -->
	</div>
</nav>