'use strict';

angular.module('myApp').controller('LoginController', ['$scope', '$window', 'LoginService', 
	
	function($scope, $window, LoginService) {
	
	var self = this;
	self.user={id:null,userName:'',password:'',email:''};

    self.reset = reset;
    self.validateUser = validateUser;
 
    function validateUser(role) {
    	$scope.errorMessage = '';
    	console.log("Role :: " + role);
    	if(self.user.userName === null) {
            console.log('UserName is blank, Please Enter User Name');
        } else if (self.user.password === null) {
            console.log('Password is blank, Please Enter Password');
        } else {
        	$window.sessionStorage.setItem('sUserName', self.user.userName);        	
        	LoginService.validateUser(self.user).then(function(d) {
        			console.log('The Login User validation was Successful');
        			getUserRole(self.user.userName, role);
        		}, function(errResponse) {
        				console.error('Error while Validating User');
        				$scope.errorMessage = 'Wrong UserName or Password. Please enter valid User Credentials';
    				}
        		);
        }
    }  
    
	function getUserRole(userName, userRole) {
		console.log('userName :: ' + userName);
		LoginService.fetchUserRole(userName).then(function(d) {
			var roleCode = d;
			$window.sessionStorage.setItem('sRoleCode', roleCode);
			console.log('roleCode :: ' + roleCode);
			if(userRole === roleCode) {
				if(roleCode === 'Administrator')
					window.location = 'adminhome';
				else if(roleCode === 'Patient')
					window.location = 'clienthome';
				else if(roleCode === 'Doctor')
					window.location = 'doctorhome';
				else if(roleCode === 'Specialist')
					window.location = 'specialisthome';
				else {
					console.error('The User Credentials entered are not Authorised');
					$scope.errorMessage = 'The User Credentials entered are not Authorised';
				}			
			} else {
				console.error('The User Credentials and Role selected is not matching');
				$scope.errorMessage = 'The User Credentials and Role selected is not matching';
			}		
			
		}, function(errResponse) {
			console.error('Controller :: Error while fetching User Role');
		});
	}
    
    function reset(){
        self.user={userName:'',password:''};
        $scope.loginForm.$setPristine(); //reset Form
    }
	
}]);