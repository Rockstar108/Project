<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin Home Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-App="myApp" class="ng-cloak">
	<div align="center">
	<div align="center" class="generic-container" ng-controller="UserDetailsController as ctrl" data-ng-init="init('adminhome')">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> User Home Form </span>
		</div>
		<div class="floatCenter labelHeader ">
		    <a href="#">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div class="formcontainer">
			<form ng-submit="ctrl.submit('userhome')" name="userHomeForm" class="form-horizontal">
			<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
				<br>
				<div class="row">
					
				</div>
				
				<div class="row">
						<h4> Image and Content to be added </h4>
				</div>
				<hr>
				<br>
			</form>
		</div>
	</div>
	<div align="right"> <a href="home"> Logout </a></div>
	</div>
	</div>	

	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.js"></script>
	<script src="<c:url value='/static/js/app_ngtable.js' />"></script>
	<script src="<c:url value='/static/js/service/userdetail_service.js' />"></script>
	<script	src="<c:url value='/static/js/controller/userdetail_controller.js' />"></script>
</body>
</html>