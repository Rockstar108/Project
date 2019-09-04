'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/mobilehealthcare/user/';

    var factory = {
    	fetchAllRoles: fetchAllRoles,
    	fetchAllUsers: fetchAllUsers,
    	getUserDetailsById: getUserDetailsById,
        createUser: createUser,
        updateUser: updateUser,
        deleteUser:deleteUser
    }

    return factory;

    function fetchAllRoles() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'roles')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Roles');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getUserDetailsById(userName) {
      	 var deferred = $q.defer();
           $http.get(REST_SERVICE_URI + 'getuserdetailbyid/' + userName)
               .then(
               function (response) {
                   deferred.resolve(response.data);
               },
               function(errResponse){
                   console.error('Error while fetching Roles');
                   deferred.reject(errResponse);
               }
           );
           return deferred.promise;
      }

    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, user)
            .then(
		            function (response) {
		                deferred.resolve(response.data);
		            },
		            function(errResponse){
		                console.error('Error while creating User');
		                deferred.reject(errResponse);
		            }
	        );
	        return deferred.promise;
    }

    function updateUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + 'edit', user)
            .then(
		            function (response) {
		                deferred.resolve(response.data);
		            },
		            function(errResponse){
		                console.error('Error while creating User');
		                deferred.reject(errResponse);
		            }
	        );
	        return deferred.promise;
    }

    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
   
}]);
