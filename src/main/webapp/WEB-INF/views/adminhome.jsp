<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin Home Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-App="myApp" class="ng-cloak" background="static/images/bg-admin.jpeg">
	<div align="center">
	<div align="center" class="generic-container" ng-controller="UserDetailsController as ctrl" data-ng-init="init('adminhome')">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> Admin Home Form </span>
		</div>
		<div class="floatCenter labelHeader ">
		    <a href="#">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="mapdoctorpatient">Map Patients</a>
		</div>
		<div class="formcontainer">
			<form ng-submit="ctrl.submit('adminhome')" name="adminHomeForm" class="form-horizontal">
			<div ng-if="successMessage" style="color:green;font-weight: bold;">{{ successMessage }}</div>
			<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
				<br>
				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">User Name</label>
						<div class="col-md-5">
							<input type="text" ng-model="ctrl.searchName" name="searchName"
								class="searchName form-control input-sm" placeholder="Search with User Name" required />
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Role</label>
						<div class="col-md-5">
							<select class="roleId form-control input-sm" ng-model="ctrl.user.roleId"
									ng-options="role.roleId as role.roleDescription for role in ctrl.roles">
								<option value="-2"> --- No Roles Available --- </option>
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-actions " align="center">
						<input type="submit" value="Search" class="btn btn-primary btn-sm">
						<button type="button" class="btn btn-warning btn-sm" ng-click="ctrl.reset()" ng-disabled="adminHomeForm.$pristine">Reset Form</button>
						<a class="btn btn-info btn-sm" href="registration">Create User</a>
					</div>
				</div>
				<hr>
				<br>
				<div id="userDetails" style="visibility: hidden">
					<table border="1px solid grey" align="center" background-color="#FFA500" ng-table="ctrl.tableParams2" class="table">
	    				<tr ng-repeat="user in $data">
	        				<td title="'User Name'">
	            					<a class=lendhand ng-click="ctrl.editUser(user)">	{{user.userName}} </a> </td>
	        				<td title="'First Name'">
	            					{{user.firstName}} </td>
	            			<td title="'Last Name'">
	            					{{user.lastName}} </td>
	            			<td title="'Role'">
	            					{{user.roleName}} </td>
	            			<td title="'Mobile Number'">
	            					{{user.mobileNumber}} </td>
	            			<td title="'Email Address'">
	            					{{user.email}} </td>
	            			<td title="'Delete'" align="center">
	            					<a class=lendhand ng-click="ctrl.remove(user)"><img src="static/images/delete.png" height="20" width="20"/> </a> </td>
	            			<td title="'Password'" align="center"> 
	            					<a class=lendhand ng-click="ctrl.generatePassword(user)"><img src="static/images/securitykey.png" height="20" width="20"/> </a> </td>
	   					 </tr>
					</table>
				</div>
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