'use strict';

angular.module('myApp').controller('UserController', ['$scope', '$window', 'UserService',
	
				function($scope, $window, UserService) {
	
					var self = this;
					self.user = { userId : null, userName : '', firstName : '', lastName : '', dateOfBirth : '', email : '',
						phoneNumber : '', mobileNumber : '', roleId : '', password : '', city : '', state : '', country : '',
						pinCode : '', userStatus : '', retypePassword : ''
					};

					self.role = {
						roleId : null,
						roleName : '',
						roleDescription : ''
					};
					
					self.users = [];

					self.roles = [];
					
					self.submit = submit;
					self.reset = reset;					
					
					$scope.init = function (value) {
						if(value === 'register') fetchAllRoles();
						if(value === 'edituser') getUserDetailsById();
					};
		  			
		  			function displayLoggedUserName() {
		  				console.log("Display Logged In User Name");
		  				self.user.userName = $window.sessionStorage.getItem('sUserName').toUpperCase();
		  				console.log("User Name :: " + self.user.userName);
		  				$scope.errorMessage = '';
		  				$scope.successMessage = '';
		  			}

					function fetchAllRoles() {
						UserService.fetchAllRoles().then(function(d) {
							console.log("Roles retrieved successfully");
							self.roles = d;
							self.user.roleId = -1;
						}, function(errResponse) {
							console.error('Error while fetching Roles');
						});
					}
					
					function fetchAllUsers() {
						UserService.fetchAllUsers().then(function(d) {
							self.users = d;
						}, function(errResponse) {
							console.error('Error while fetching Users');
						});
					}
					
					function getUserDetailsById() {
						console.log(" Inside getUserDetailsById :: Starts ");
						var userName = $window.sessionStorage.getItem('sSelectedUser');
						console.log(" userName :: " + userName);
						UserService.getUserDetailsById(userName).then(function(d) {
							console.log('Response :: ' + d);
							$scope.errorMessage = '';
							self.user = d;
							console.log('User Data retrirved :: ' + userName);
							
						}, function(errResponse) {
							console.error('Error while searching User');
							$scope.errorMessage = 'Error Occured !! Please Search the User Details again';
						});
					}
					
					function createUser(user) {
						console.log('User Name :: ' + user.userName);
						$window.sessionStorage.setItem('sUserName', user.userName);
						console.log('Role Id :: ' + user.roleId);
						// var flag = userValidations(user);
						// console.log('flag :: createUser :: ' + flag);
						// if(flag === 'false') return false;
						
						UserService.createUser(user).then(function(d) {
							var sUserName = $window.sessionStorage.getItem('sUserName');
							console.log('User Created successfully' + sUserName);
							window.location = 'success';
						}, function(errResponse) {
								console.error('Error while creating User');
								$scope.errorMessage = 'Error Occured !! Please Enter the User Details again';
						});
					}

					function updateUser(user, id) {
						console.log('Update User :: ' + updateUser);
						var sUserName = $window.sessionStorage.getItem('sUserName');
						console.log('User Updated successfully' + sUserName);
						UserService.updateUser(user).then(function(d) {
								window.location = 'success';
							}, function(errResponse) {
									console.error('Error while creating User');
									$scope.errorMessage = 'Error Occured !! Please Enter the User Details again';
							}
						);
					}
					
					function userValidations(user) {
						console.log("Email :: " + user.email);						
						var flag = validateEmail(user.email)
						console.log("flag :: " + flag);					
						if(flag === 'false') {
							document.getElementById('emailId').style.visibility = 'visible';
						}
						return flag;
					}

					function deleteUser(id) {
						UserService.deleteUser(id).then(fetchAllUsers,
								function(errResponse) {
									console.error('Error while deleting User');
								});
					}

					function submit() {
						if (self.user.userId === null) {
							console.log('Saving New User', self.user);
							createUser(self.user);

						} else {
							updateUser(self.user, self.user.id);
							console.log('User updated with id ', self.user.id);
						}
						reset();
					}

					function reset() {
						self.user = {
							userId : null,
							userName : '',
							firstName : '',
							lastName : '',
							dateOfBirth : '',
							email : '',
							phoneNumber : '',
							mobileNumber : '',
							roleId : '',
							password : '',
							city : '',
							state : '',
							country : '',
							pinCode : '',
							userStatus : ''
						};
						$scope.userForm.$setPristine(); // reset Form
					}

} ]);
