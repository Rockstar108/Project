<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Doctor Details Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<style>
		.mainDiv{
			background-image:url("static/images/bg-container-1.jpeg");
		}
	</style>
</head>
<body ng-App="myApp" class="ng-cloak" background="static/images/bg-doctor.jpeg">
	<div align="center">
	<div align="center" class="generic-container" ng-controller="UserDetailsController as ctrl" data-ng-init="init('doctorhome')">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> Doctor Details Form </span>
		</div>
		<div class="floatCenter labelHeader ">
		    <a href="#">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="mapspecialistpatient">Manage Specialist</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div class="formcontainer">
			<form name="doctorForm" class="form-horizontal">
			<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
				<br>
				
				<div class="row">
                    <div class="form-group col-md-12">
                        <label align="right" class="col-md-2 control-lable" for="file">Doctor Name</label>
                        <div class="col-md-7"> {{ ctrl.user.userName }} </div>
                    </div>
                </div>           

				<hr>
				<div id="patientDetails" style="visibility: hidden">
					<table border="1px solid grey"  background-color="#FFA500" ng-table="ctrl.tableParams2" class="table">
	    				<tr ng-repeat="user in $data">
	        				<td title="'Patient User Name'">
	            					<a class=lendhand ng-click="ctrl.displaySendPatient(user)"> {{ user.userName }} </a> </td>
	        				<td title="'First Name'">
	            					{{user.firstName}} </td>
	            			<td title="'Last Name'">
	            					{{user.lastName}} </td>
	            			<td title="'Date Of Birth'">
	            					{{user.dateOfBirth}} </td>
	            			<td title="'City'">
	            					{{user.city}} </td>
	            			<td title="'State'">
	            					{{user.state}} </td>
	            			<td title="'Transactions'" align="center">
	            					<a ng-click="ctrl.displayDoctorTransaction(user)"><img src="static/images/details.png" height="20" width="20"/> </a> </td>
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