<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    	<title>Doctor Details Home</title>  
      	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak" background="static/images/bg-doctor.jpeg">
  	  <div align="center">	
      <div align="center" class="generic-container" ng-controller="UserDetailsController as ctrl" data-ng-init="init('mapspecialist')">
      	  <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Doctor Details</span></div>
              <div class="floatCenter labelHeader ">
				    <a href="doctorhome">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <a href="#">Manage Specialist</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit('mapspecialist')" name="mapSpecialistForm" class="form-horizontal">
                      <div align="center" ng-if="successMessage" style="color:green;font-weight: bold;">{{ successMessage }}</div>
		 			  <div align="center" ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
         			  <br>
                      <input type="hidden" ng-model="ctrl.user.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Specialist Name</label>
                              <div class="col-md-7">
                              	<select class="userId form-control input-sm" ng-model="ctrl.specialist.userId"
									ng-options="user.userId as user.userName for user in ctrl.specialists">
								<option value="-2"> --- No Specialist Available --- </option>
								</select>
                              </div>
                          </div>
                      </div>     
                      
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Patient Name</label>
                              <div class="col-md-7">
                              	<select class="userId form-control input-sm" ng-model="ctrl.patient.userId"
									ng-options="user.userId as user.userName for user in ctrl.patients">
								<option value="-2"> --- No Specialist Available --- </option>
								</select>
                              </div>
                          </div>
                      </div>            
                                        
     				  <br/>
                      <div class="row">
                          <div align="center" class="form-actions">
                              <input type="submit"  value="Map Specialist Patient" class="btn btn-primary btn-sm">
                              <a class="btn btn-info btn-sm" href="doctorhome">Cancel</a>
                          </div>
                      </div>
                      <hr>
					  <br>
			          <div id="specialistPatientDetails" style="visibility: hidden">
							<table border="1px solid grey"  background-color="#FFA500" ng-table="ctrl.tableParams2" class="table">
			    				<tr ng-repeat="user in $data">
			        				<td title="'Specialist Name'">
			            					{{user.specialistName}} </td>
			        				<td title="'Patient Name'">
			            					{{user.userName}} </td>
			            			<td title="'Date Of Birth'">
			            					{{user.dateOfBirth}} </td>
			            			<td title="'Email'">
			            					{{user.email}} </td>
			            			<td title="'Phone Number'">
			            					{{user.phoneNumber}} </td>
			            			<!-- <td title="'Delete'" align="center">
			            					<a class=lendhand ng-click="ctrl.remove(user)"><img src="static/images/delete.png" height="20" width="20"/> 
			            			</a> </td> -->
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