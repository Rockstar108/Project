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
	<div align="center" class="generic-container" ng-controller="LoginController as ctrl">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> Mobile Healthcare - Application </span>
		</div>
		<div class="formcontainer">
			<form name="loginForm" class="form-horizontal">
				<div class="row">
					<div class="col-md-3">
						<a class=lendhand ng-click="ctrl.validateUser('Administrator')"  style="display: block; width:200px; height:200px; overflow:hidden;">
   						 	<img src="static/images/administrator.png" title="Administrator"/>
						</a>
					</div>
					<div class="col-md-3">
						<a class=lendhand ng-click="ctrl.validateUser('Doctor')" style="display: block; width:200px; height:200px; overflow:hidden;">
   						 	<img src="static/images/doctor.png" title="Doctor"/>
						</a>
					</div>						
					<div class="col-md-3">
						<a class=lendhand ng-click="ctrl.validateUser('Patient')" style="display: block; width:200px; height:200px; overflow:hidden;">
   						 	<img src="static/images/patient.jpg" title="Patient"/>
						</a>
					</div>						
					<div class="col-md-3">
						<a class=lendhand ng-click="ctrl.validateUser('Specialist')" style="display: block; width:200px; height:200px; overflow:hidden;">
   						 	<img src="static/images/specialist.jpg" title="Specialist"/>
						</a>
					</div>		
				</div>
				
				<hr/>
				<div class="panel-heading floatCenter" align="center">
					<span class="lead"> User Login Form </span>
				</div>
				
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