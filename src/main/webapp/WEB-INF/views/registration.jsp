<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login Form</title>
	<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<script>
		function limit(event, value, maxLength) {
			alert("hello");
		    if (value != undefined && value.toString().length >= maxLength) {
		        event.preventDefault();
		    }
		}	
	</script>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div align="center">
	<div align="center" class="generic-container" ng-controller="UserController as ctrl"  data-ng-init="init('register')">
	<div class="panel panel-default" >
		<div class="panel-heading floatCenter" align="center">
			<span class="lead"> User Registration Form </span>
		</div>
		<div class="formcontainer center_div">
			<form name="userForm" ng-submit="ctrl.submit('create')" class="form-horizontal">
				<div ng-if="errorMessage" style="color:red;font-weight: bold;">{{ errorMessage }}</div>
				<input type="hidden" name="userId" ng-model="ctrl.user.userId">
				<!-- <input type="hidden" name="roleId" ng-model="ctrl.user.roleId"> -->

				<div class="row" >
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">User Name</label>
						<div class="col-md-6">
							<input type="text" ng-model="ctrl.user.userName" name="userName"
								class="username form-control input-sm" placeholder="Enter your User Name" required />
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">First Name</label>
						<div class="col-md-6">
							<input type="text" ng-model="ctrl.user.firstName" name="firstName"
								class="lastName form-control input-sm"
								placeholder="Enter your First Name" required />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Last Name</label>
						<div class="col-md-6">
							<input type="text" ng-model="ctrl.user.lastName" name="lastName"
								class="lastName form-control input-sm"
								placeholder="Enter your Last Name" required />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Date of Birth</label>
						<div class="col-md-6">
							<input type="date" ng-model="ctrl.user.dateOfBirth" name="dateOfBirth"
								class="dobirth form-control input-sm"
								placeholder="Enter your Date of Birth" required />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Email</label>
						<div class="col-md-6">
							<input type="email" ng-model="ctrl.user.email" name="email"
								class="email form-control input-sm"
								placeholder="Enter your Email" required invalid />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Phone Number</label>
						<div class="col-md-6">
							<input type="number" ng-model="ctrl.user.phoneNumber" ng-pattern="/^[0-9]{1,7}$/" name="phoneNumber" 
									class="phoneNumber form-control input-sm"  type = "number" maxlength = "10" 
									oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
    								placeholder="Enter your Phone Number" required />
							<span style="color:Red" ng-show="userForm.phoneNumber.$dirty&&userForm.phoneNumber.$error.maxlength"> 
								Maximum 10 Characters are allowed</span>
								
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Mobile Number</label>
						<div class="col-md-6">
							<input type="number" ng-model="ctrl.user.mobileNumber" ng-pattern="/^[0-9]{1,7}$/" 
								name="mobileNumber" class="mobileNumber form-control input-sm"  maxlength="10" 
								oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
								placeholder="Enter your Mobile Number" required />
							<span style="color:Red" ng-show="userForm.mobileNumber.$dirty&&userForm.mobileNumber.$error.maxlength"> 
								Maximum 10 Characters are allowed</span>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Role</label>
						<div class="col-md-6">
							<select class="roleId form-control input-sm" ng-model="ctrl.user.roleId"
									ng-options="role.roleId as role.roleDescription for role in ctrl.roles">
								<option value="-2"> --- No Roles Available --- </option>
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Password</label>
						<div class="col-md-6">
							<input type="password" ng-model="ctrl.user.password" name="password" ng-maxlength="10" 
								class="password form-control input-sm" placeholder="Enter your Passsword" required />
								<span style="color:Red" ng-show="userForm.password.$dirty&&userForm.password.$error.maxlength"> 
								Maximum 10 Characters are allowed</span>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Re-Type Password</label>
						<div class="col-md-6">
							<input type="password" ng-model="ctrl.user.retypePassword" name="retypePassword" ng-maxlength=10 
								class="retypePassword form-control input-sm" placeholder="Please retype your Password" 
								required compare-to="ctrl.user.password" />
							<span style="color:Red" ng-show="userForm.retypePassword.$dirty&&userForm.retypePassword.$error.maxlength"> 
								Maximum 10 Characters are allowed</span>
							<div ng-messages="userForm.retypePassword.$error" ng-messages-include="error-messages"></div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">City</label>
						<div class="col-md-6">
							<input type="text" ng-model="ctrl.user.city" name="city"
								class="city form-control input-sm" placeholder="Enter your City"
								required />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">State</label>
						<div class="col-md-6">
							<input type="text" ng-model="ctrl.user.state" name="state"
								class="state form-control input-sm"
								placeholder="Enter your State" required />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Country</label>
						<div class="col-md-6">
							<input type="text" ng-model="ctrl.user.country" name="country"
								class="country form-control input-sm"
								placeholder="Enter your Country" required />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12">
						<label align="right" class="col-md-4 control-lable" for="file">Zip / Pin Code</label>
						<div class="col-md-6">
							<input type="text" ng-model="ctrl.user.pinCode" name="pinCode"
								class="pinCode form-control input-sm"
								placeholder="Enter your Zip/Pin Code" required />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-actions " align="center">
						<input type="submit" value="Add" class="btn btn-primary btn-sm">						
						<button type="button" class="btn btn-warning btn-sm" ng-click="ctrl.reset()" ng-disabled="userForm.$pristine">Reset Form</button>
						<a class="btn btn-info btn-sm" href="adminhome">Cancel</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>	

	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-messages.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script	src="<c:url value='/static/js/controller/user_controller.js' />"></script>
	<script type="text/ng-template" id="error-messages">
   		<span ng-message="compareTo">Must match the previous entry</span>
	</script>

</body>
</html>