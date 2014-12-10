<!doctype html>
<html lang="en" xmlns:fb="http://www.facebook.com/2008/fbml"
	xmlns:og="http://opengraphprotocol.org/schema/"
	xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
<title>Java Based Configuration</title>
<!-- Bootstrap -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet"
	media="screen">
<link href="resources/css/style.css" rel="stylesheet" media="screen">
<script src="resources/js/jquery-2.0.3.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="header.jsp" />
		<div class="container">
			<div class="row">
				<jsp:include page="${partial}" />
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
