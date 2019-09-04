<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    	<title>Doctor Details Home</title>  
      	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="UserDetailsController as ctrl" data-ng-init="init('doctor')">
      	  <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Doctor Details</span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit('showpatients')" name="doctorDetailsForm" class="form-horizontal">
                      <div align="center" ng-if="successMessage" style="color:green;font-weight: bold;">{{ successMessage }}</div>
		 			  <div align="center" ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
         			  <br>
                      <input type="hidden" ng-model="ctrl.user.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Doctor Name</label>
                              <div class="col-md-7">
                              	<select class="userId form-control input-sm" ng-model="ctrl.user.userId"
									ng-options="user.userId as user.userName for user in ctrl.doctors">
								<option value="-2"> --- No Roles Available --- </option>
								</select>
                              </div>
                          </div>
                      </div>                       
     				  <br/>
                      <div class="row">
                          <div align="center" class="form-actions">
                              <input type="submit"  value="Show Patient Details" class="btn btn-primary btn-sm">
                              <a class="btn btn-info btn-sm" href="doctorclientmap">Map Doctor Patient</a>
                          </div>
                      </div>
                  </form>
              </div>
          </div>

          <div id="patientDetails" style="visibility: hidden">
				<table border="1px solid grey"  background-color="#FFA500" ng-table="ctrl.tableParams2" class="table">
    				<tr ng-repeat="user in $data">
        				<td title="'User Name'">
            					{{user.userName}} </td>
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
   					 </tr>
				</table>
			</div>
     
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.js"></script>
	  <script src="<c:url value='/static/js/app_ngtable.js' />"></script>
	  <script src="<c:url value='/static/js/service/userdetail_service.js' />"></script>
	  <script	src="<c:url value='/static/js/controller/userdetail_controller.js' />"></script>
	  
  </body>
</html>