<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users - Upload Form</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-App="myApp" class="ng-cloak"">
	
	<div align="center">
	<div align="center" class="generic-container" ng-controller="UserController as ctrl" data-ng-init="init('uploadusers')">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> User Details - Upload </span>
		</div>
		<div class="formcontainer">
			<form ng-submit="ctrl.submit('uploadusers')" enctype="multipart/form-data"   name="uploadUserForm" class="form-horizontal">
				<div ng-if="successMessage" style="color:green;font-weight: bold;">{{ successMessage }}</div>
				<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
				<br>
				<div>
					<table align="center">
						<tr>
							<h4>
								<td> <label for="myFileField">Logged In User Name :  </label></td>
								<td colspan="2"> <label class="control-lable labelFont"> {{ ctrl.user.userName }} </label> </td>
							</h4>
						</tr>
						<tr> <td colspan="3"> &nbsp; </td> </tr>	
						<tr>
							<td> <label for="myFileField">Upload File : </label></td>
							<td colspan="2"> <input file-model="myFile" type="file" class="form-control" id ="myFileField"/>  </td>
						</tr>
						<tr> <td colspan="3"> &nbsp; </td> </tr>
					</table>
					<br>
					<table align="center">
						<tr>	
							<td>
								<input id="submit" type="submit" value="Upload User Details" class="btn btn-success btn-sm" />
							</td>
							<td> &nbsp; </td>
							<td>
								<a class="btn btn-primary btn-sm" href="doctordetails">Show Doctor Details</a>
							</td>
						</tr>
					</table>					
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>	

	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script	src="<c:url value='/static/js/controller/user_controller.js' />"></script>
</body>
</html>