<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Doctor Transactions Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-App="myApp" class="ng-cloak">
	<div align="center">
	<div align="center" class="generic-container" ng-controller="UserDetailsController as ctrl" data-ng-init="init('clienttransaction')">
	<div class="panel panel-default">
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> Doctor Transactions Form </span>
		</div>
		<div class="floatCenter labelHeader">
			<a href="doctortransactions">Doctor Transactions</a>
			&nbsp; &nbsp; 
			Patient Transactions 
		</div>
		<div class="formcontainer">
			<form name="doctorForm" class="form-horizontal">
			<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
				<br>
				
				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-6 control-lable" for="file">Doctor Name  : {{ ctrl.user.userName }}  </label>
						<label align="left" class="col-md-6 control-lable" for="file"> Patient Name : {{ ctrl.patient.userName }}  </label>
					</div>
				</div>       

				<hr>
				<div id="transactionDetails" style="visibility: hidden">
					<table border="1px solid grey"  background-color="#FFA500" ng-table="ctrl.tableParams2" class="table">
	    				<tr ng-repeat="transaction in $data">
	        				<td title="'User Name'">
	            					{{ transaction.userName }} </td>
	        				<td title="'Subject'">
	            					{{transaction.subject}} </td>
	            			<td title="'Description'">
	            					{{transaction.description}} </td>
	            			<td title="'Created By'">
	            					{{transaction.createdBy}} </td>
	            			<td title="'Created Date'">
	            					{{transaction.createdDate}} </td>
	            			</tr>
					</table>
				</div>
				
			</form>
		</div>
	</div>
	<div align="right"> Go To <a href="doctorhome"> Doctor Home</a></div>
	</div>
	</div>	

	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.js"></script>
	<script src="<c:url value='/static/js/app_ngtable.js' />"></script>
	<script src="<c:url value='/static/js/service/userdetail_service.js' />"></script>
	<script	src="<c:url value='/static/js/controller/userdetail_controller.js' />"></script>
</body>
</html>