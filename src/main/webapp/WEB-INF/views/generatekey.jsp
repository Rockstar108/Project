<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Generate Secuirty Key</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body ng-App="myApp" class="ng-cloak">
	<div align="center">
	<div align="center" class="generic-container" ng-controller="UserDetailsController as ctrl" data-ng-init="init('generatekey')">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> User - Generate Security Key </span>
		</div>
		<!-- <div class="floatCenter labelHeader"  ng-include="'header.jsp'"> </div>	 -->
		<div class="floatCenter labelHeader">
			<a href="mapfolder">Map Folder</a>
			&nbsp; &nbsp; 
			<a href="mapbluetooth">Map Bluetooth</a> 
			&nbsp; &nbsp; 
			Generate Key
			&nbsp; &nbsp; 
			<a href="userkeyauthenticate">User Key Authenticate</a>
		</div>
		<div class="formcontainer">
			<form ng-submit="ctrl.submit('generatekey')" name="generateKeyForm" class="form-horizontal">
			<div ng-if="successMessage" style="color:green;font-weight: bold;">{{ successMessage }}</div>
			<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
			<br>
				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">User Name</label>
						<div class="col-md-5" align="left">
							<label class="control-lable labelFont"> {{ ctrl.user.userName }} </label>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">User Security Key : </label>
						<div class="col-md-6">
							<input type="text" ng-model="ctrl.user.securityKey" name="securityKey"
								class="securityanswer1 form-control input-sm" placeholder="Enter your Security Key" required />
						</div>
					</div>
				</div>
				
				<br> 
				
				<div class="row">
					<div class="form-actions " align="center">
						<input type="submit" value="Add / Update Security Key" class="btn btn-primary btn-sm">
					</div>
				</div>
				<br>
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