<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Doctor Details Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-App="myApp" class="ng-cloak">
	<div align="center">
	<div align="center" class="generic-container" ng-controller="UserDetailsController as ctrl" data-ng-init="init('doctorsend')">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> Client Details Form </span>
		</div>
		<div class="formcontainer">
			<form ng-submit="ctrl.submit('doctorsend')" name="doctorSendForm" class="form-horizontal">
			<div ng-if="successMessage" style="color:green;font-weight: bold;">{{ successMessage }}</div>
			<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
			
			<br>
				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-6 control-lable" for="file">Doctor Name  : {{ ctrl.user.userName }}  </label>
						<label align="left" class="col-md-6 control-lable" for="file"> Patient Name : {{ ctrl.patient.userName }}  </label>
					</div>
				</div>

				<hr>
				<div>
					<table align="center">
						<!-- <tr>
							<h4>
								<td> <label for="myFileField">Logged In User Name :  </label></td>
								<td colspan="2"> 
									<input type="text" ng-model="ctrl.user.lastName" name="lastName"
										class="lastName form-control input-sm" placeholder="Enter your Last Name" required /></td>
							</h4>
						</tr> -->
						<tr> <td colspan="3"> &nbsp; </td> </tr>	
						<tr>
							<td> <label for="myFileField">Doctor Subject : </label></td>
							<td colspan="2"> <input type="text" ng-model="ctrl.subject"  class="form-control" 
									id="myFileField" placeholder="Enter your Subject" /> </td>
						</tr>
						<tr> <td colspan="3"> &nbsp; </td> </tr>
						<tr>
							<td> <label for="myFileField">Doctor Description : </label></td>
							<td colspan="2"> 
								<textarea ng-model="ctrl.description"  rows="4" cols="50"> 
								</textarea> 
							</td>
						</tr>
						<tr> 
							<td colspan="3"> &nbsp; </td>
						</tr>							
					</table>
					<table align="center">
						<tr>	
							<td> 
								<input id="sendPatient" type="submit" value="Send To Patient" class="btn btn-success btn-sm" />
							</td>
							<td> &nbsp; </td>
							<td> <button type="button" class="btn btn-warning btn-sm" ng-click="ctrl.reset()" ng-disabled="clientDetailsForm.$pristine">Reset Form</button> </td>
							<td> &nbsp; </td>
							<td> <a href="javascript:window.history.back();" class="btn btn-info btn-sm" > Cancel </a> </td>
						</tr>
					</table>					
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>	

	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.js"></script>
	<script src="<c:url value='/static/js/app_ngtable.js' />"></script>
	<script src="<c:url value='/static/js/service/userdetail_service.js' />"></script>
	<script	src="<c:url value='/static/js/controller/userdetail_controller.js' />"></script>
</body>
</html>