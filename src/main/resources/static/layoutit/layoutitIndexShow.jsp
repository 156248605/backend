<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<%@include file="/commons/dynamic.jspf" %>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>我的门户</title>
	<link rel="stylesheet" type="text/css" href="css/layoutit.css">
	<link rel="stylesheet" type="text/css" href="${ctxPath}/styles/icons.css">
	<link rel="stylesheet" type="text/css" href="${ctxPath}/scripts/layoutit/css/layoutitIndex.css">
	<link rel="stylesheet" type="text/css" href="${ctxPath}/scripts/layoutit/css/layout.css">
	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>	
	<![endif]-->
	<script type="text/javascript" src="${ctxPath }/scripts/echart/echarts.min.js"></script>
	<script type="text/javascript" src="${ctxPath}/scripts/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${ctxPath}/scripts/mini/miniui/miniui.js"></script>
	<script type="text/javascript" src="${ctxPath}/scripts/share.js"></script>
	<script type="text/javascript" src="${ctxPath}/scripts/layoutit/js/layoutitIndex.js"></script>
</head>
<body>
	<div class="personalPort"></div>
</body>
<!-- ie自适应 -->
<!--[if lte IE 8]>
<script type="text/javascript">
 	window.onresize = function(){
		cardResponse();
	} 
</script>
<![endif]-->
</html>
