'use strict';

angular.module('myApp').factory('UserDetailsService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/mobilehealthcare/userdetails/';
    
    var REST_SERVICE_URI_USER = 'http://localhost:8080/mobilehealthcare/user/';

    var factory = {    
    	submitSearchUserName: submitSearchUserName,
    	findUserByName:findUserByName,
    	removeUserByName: removeUserByName,
    	generatePassword: generatePassword,
    	fetchAllRoles: fetchAllRoles,
    	fetchDoctorDetails:fetchDoctorDetails,
    	getSpecialistDetails: getSpecialistDetails,
    	fetchPatientDetails:fetchPatientDetails,
    	fetchSpecialistPatientDetails:fetchSpecialistPatientDetails,
    	fetchDoctorDetailsById:fetchDoctorDetailsById,
    	fetchPatientsNotMapped:fetchPatientsNotMapped,
    	saveDoctorPatientMapDetails:saveDoctorPatientMapDetails,
    	saveSpecialistPatientMapDetails:saveSpecialistPatientMapDetails,
    	fetchSpecialPatientMappedByDoctorName: fetchSpecialPatientMappedByDoctorName,
    	sendDetailsToPatient:sendDetailsToPatient,
    	sendDetailsToDoctor:sendDetailsToDoctor,
    	sendSpecialistDetailsToDoctor:sendSpecialistDetailsToDoctor,
    	showTransactionDetails:showTransactionDetails,
    	showClientTransactionDetails:showClientTransactionDetails,
    	showSpecialistTransactionDetails:showSpecialistTransactionDetails
    	
    };
    return factory;
    
    function fetchAllRoles() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER + 'roles')
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
    
    function submitSearchUserName(searchName, roleId) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + roleId + "/" + searchName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while Searching User Name');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function findUserByName(userName) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER + 'userName/' + userName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching User details By User Name');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    function removeUserByName(userName) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'remove/' + userName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while Searching User Name');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
   
    function generatePassword(userName) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'generatePassword/' + userName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while Generating the Security Key');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
        
    function submitAuthenticateSecurityKey(userName, securityKey) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'authenticateSecuritykey/' + userName + '/' + securityKey)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while Generating the Security Key');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function sendDetailsToDoctor(userName, doctorName, subject, description, transactionId) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER + 'senddetailstodoctor/' + description + '/' + subject + '/' 
        		+ userName + '/' + doctorName + '/' + transactionId).then(
            
        	function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while sending Doctor Details ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function sendSpecialistDetailsToDoctor(specialistName, doctorName, patientName, subject, description) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER + 'sendspecialistdetailstodoctor/' + description + '/' + subject + '/' 
        		+ specialistName + '/' + doctorName + '/' + patientName).then(
            
        	function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while sending Doctor Details ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function sendDetailsToPatient(userName, patientName, subject, description) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER + 'senddetailstopatient/' + description + '/' + subject + '/' + userName + '/' + patientName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while sending Patient Details ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
  
    function submitGenerateUserAuthenticate(userName) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'generateuserauthenticate/' + userName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while Generating User Authentication');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchDoctorDetails() {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'doctordetails')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Doctor details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getSpecialistDetails() {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'specialistdetails')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Doctor details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function showTransactionDetails(userName) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER + 'getdoctortransactiondetails/' + userName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Doctor details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function showClientTransactionDetails(userName, patientName) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER + 'getclienttransactiondetails/' + userName + '/' + patientName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Client details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function showSpecialistTransactionDetails(userName, patientName) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_USER + 'getspecialisttransactiondetails/' + userName + '/' + patientName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Client details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchDoctorDetailsById(doctorId) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'doctordetailsbyid/' + doctorId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Doctor details By DoctorId');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    function fetchPatientDetails(doctorId) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'patientdetails/' + doctorId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Patient details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function fetchSpecialistPatientDetails(specialistId) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'specialistpatientdetails/' + specialistId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Patient details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    function fetchPatientsNotMapped() {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'patientdetailsnotmapped')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Patient details Not mapped');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function saveDoctorPatientMapDetails(doctorName, patientName) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'savedoctorpatientmapdata/' + doctorName + '/' + patientName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while mapping Doctor Patient details ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchSpecialPatientMappedByDoctorName(doctorName) {
		var deferred = $q.defer();
	    $http.get(REST_SERVICE_URI + 'getspecialistpatientmapdata/' + doctorName)
	        .then(
	        function (response) {
	            deferred.resolve(response.data);
	        },
	        function(errResponse) {
	            console.error('Error while mapping Specialilst Patient details ');
	            deferred.reject(errResponse);
	        }
	    );
	    return deferred.promise;
    }
    
    function saveSpecialistPatientMapDetails(doctorName, specialistId, patientId) {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'savespecialistpatientmapdata/' + doctorName + '/' + specialistId + '/' + patientId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while mapping Specialilst Patient details ');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
}]);
