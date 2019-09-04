<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-App="myApp" class="ng-cloak">
	<div align="center">
	<div align="center" class="generic-container" ng-controller="">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> Success Message Form </span>
		</div>
		<div class="formcontainer">
			<form ng-submit="ctrl.submit()" name="loginForm" class="form-horizontal">

				<div class="row">
					<div class="form-group col-md-12">
						<label>The User Details created successfully</label>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-12">
						<label> Go To <a href="adminhome"> Admin Home Page </a></label>
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>	

	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/login_service.js' />"></script>
	<script	src="<c:url value='/static/js/controller/login_controller.js' />"></script>
</body>
</html>