'use strict';

angular.module('myApp').factory('LoginService', ['$http', '$q', function($http, $q) {
	
	var REST_SERVICE_URI = 'http://localhost:8080/mobilehealthcare/user/';
	
	var factory = {
			validateUser: validateUser,
	    	fetchUserRole: fetchUserRole
	};
	
	return factory;
	
	function validateUser(user) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'validateuser', user)
			.then(
					function(response) {
						deferred.resolve(response.data)
					},
					function(errResponse) {
						console.error('Error while validating Login Credentials');
						deferred.reject(errResponse);
					}			
			);
			return deferred.promise;
	}	
	
   function fetchUserRole(userName) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'userrole/' + userName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Service :: Error while fetching User role');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
}]);