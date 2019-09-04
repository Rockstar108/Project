<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Map Doctor Patients </title>
<style>
.username.ng-valid {
	background-color: lightgreen;
}

.username.ng-dirty.ng-invalid-required {
	background-color: red;
}

.username.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div align="center">
	<div align="center" class="generic-container" ng-controller="UserDetailsController as ctrl" data-ng-init="init('mappatients')">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead"> Patient Details </span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit('mappatients')" name="mapPatientForm" class="form-horizontal">
					<div ng-if="successMessage" style="color:green;font-weight: bold;">{{ successMessage }}</div>
					<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
					<div class="row">
						<div class="form-group col-md-12">
							<h4>
								<label align="right" class="col-md-6 control-lable" for="file"> Doctor Name : </label>
								<div class="col-md-6">
									<label align="left" class="col-md-5 control-lable" for="file"> {{ ctrl.user.userName }} </label>
								</div>
							</h4>
						</div>
					</div>

					<div class="row">
						<div align="center" class="form-actions">
							<input type="submit" value="Save Details" ng-click="ctrl.saveDoctorPatientMapDetails()" class="btn btn-primary btn-sm">
						</div>
					</div>

				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead" >List of Patients </span>
			</div>
			<div style="background-color:#EBF5FB" ng-controller="MoveItemsController">
				<div>
				<table align="center">
					<tr> 
						<td colspan="5"> &nbsp; </td>
					</tr>	
					<tr>
						<td><label for="aclients">Available Patients</label></td>
						<td width="150px" colspan="3"> &nbsp;</td>
						<td><label for="aclients">Selected Patients</label></td>
					</tr>
					<tr>
						<td rowspan="6">
							<select size="12" multiple ng-model="ctrl.availableUserId" id="availablePatients"
								ng-options="patient as patient.userName for patient in ctrl.availablepatients"
								style="width: 300px"></select>
						</td>
						<td> &nbsp; </td>
						<td align="center">
							<input id="moveright" type="button" value="Add" class="btn btn-success custom-width"
								ng-click="moveItem(ctrl.availableUserId[0], ctrl.availablepatients, ctrl.selectedpatients)" />
						</td>
						<td> &nbsp; </td>
						<td rowspan="6">
							<select size="13" multiple ng-model="ctrl.selectedUserId" id="selectedPatients"
								ng-options="patient as patient.userName for patient in ctrl.selectedpatients"
								style="width: 300px"></select>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="3"><input id="move left" type="button" value="Remove" class="btn btn-danger custom-width"
							ng-click="moveItem(ctrl.selectedUserId[0], ctrl.selectedpatients, ctrl.availablepatients)" />
						</td>
					</tr>
					
				</table>
				</div>
			<!-- <div>Selected Clients: {{selectedpatients}}</div>
				 <div>Available Clients: {{availablepatients}}</div>
				 <div>Selected: {{selected}}</div>
				 <div>Available: {{available}}</div> -->
			</div>
		</div>
	</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.js"></script>
	<script src="<c:url value='/static/js/app_ngtable.js' />"></script>
	<script src="<c:url value='/static/js/service/userdetail_service.js' />"></script>
	<script	src="<c:url value='/static/js/controller/userdetail_controller.js' />"></script>
	<script src="<c:url value='/static/js/controller/moveitems_controller.js' />"></script>
</body>
</html>