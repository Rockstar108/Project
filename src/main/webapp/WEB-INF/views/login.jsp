<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-App="myApp" class="ng-cloak" background="static/images/login-background.jpg" class="bg">
	<div align="center"> 
	<div align="center" class="generic-container" ng-controller="LoginController as ctrl">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> User Login Form </span>
		</div>
		<div class="formcontainer">
			<form ng-submit="ctrl.submit()" name="loginForm" class="form-horizontal">
				<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
				<br>
				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">User Name</label>
						<div class="col-md-5">
							<input type="text" ng-model="ctrl.user.userName" name="userName"
								class="username form-control input-sm" placeholder="Enter your User Name" required />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Password</label>
						<div class="col-md-5">
							<input type="password" ng-model="ctrl.user.password" name="password"
								class="password form-control input-sm" placeholder="Enter your Password" required />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-actions " align="center">
						<input type="submit" value="Login" class="btn btn-primary btn-sm">
						<button type="button" class="btn btn-warning btn-sm" ng-click="ctrl.reset()" ng-disabled="loginForm.$pristine">Reset Form</button>
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