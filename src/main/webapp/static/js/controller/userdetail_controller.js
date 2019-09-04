'use strict';

angular.module('myApp').controller('UserDetailsController', ['$scope', '$window', 'NgTableParams', 'UserDetailsService',
	
	function($scope, $window, NgTableParams, UserDetailsService) {

		$scope.image = [{
			src: 'static/images/delete.png',
		}];
		
		var self = this;

		self.user = { userId : null, userName : '', firstName : '', lastName : '', dateOfBirth : '', email : '',
			phoneNumber : '', mobileNumber : '', roleId : '', password : '', city : '', state : '', country : '',
			pinCode : '', userStatus : '', securityKey : '', roleName : '', doctorName: '', specialistName: ''
		};
		
		self.specialist = { userId : null, userName : '', firstName : '', lastName : '', dateOfBirth : '', email : '',
				phoneNumber : '', mobileNumber : '', roleId : '', password : '', city : '', state : '', country : '',
				pinCode : '', userStatus : ''
			};

		self.patient = { userId : null, userName : '', firstName : '', lastName : '', dateOfBirth : '', email : '',
			phoneNumber : '', mobileNumber : '', roleId : '', password : '', city : '', state : '', country : '',
			pinCode : '', userStatus : ''
		};

		self.users = [];
		
		self.transaction = { transactionDetailsId: null, userName: '', transactionType: '', subject: '', description: '', publicKey: '', 
				createdBy: '', createdDate: '' }
		
		self.value = '';
		self.subject = '';
		self.description = '';
		self.availableUserId = '';
		self.selectedUserId = '';
		self.doctorName = '';
		
		self.specialists = [];
		self.doctors = [];
		self.patients = [];
		self.selectedpatients = [];
		self.availablepatients = [];
		self.transactions = [];

		self.submit = submit;
		self.remove = remove;
		self.reset  = reset;
		self.editUser = editUser;
		self.generatePassword = generatePassword;
		self.getPatientDetails = getPatientDetails;
		self.fetchPatientMappedToDoctor = fetchPatientMappedToDoctor;
		self.displaySendPatient = displaySendPatient;
		self.displaySendSpecialist = displaySendSpecialist;
		self.displayDoctorTransaction = displayDoctorTransaction;
		self.displaySpecialistTransaction = displaySpecialistTransaction;
		self.gotoClientSendData = gotoClientSendData;		
		self.saveDoctorPatientMapDetails = saveDoctorPatientMapDetails;
		
		
		$scope.init = function (value) {
			console.log("Value :: " + value);
			if(value === 'adminhome') fetchAllRoles();
		 	if(value === 'mappatients') fetchDoctorDetails();
			self.value = value;
		 	if(value === 'doctor') fetchDoctorDetails();
		 	if(value === 'doctorhome') {
		 		displayLoggedUserName();
		 		findUserByName('doctorhome');					 		
		 	}
		 	if(value === 'specialisthome') {
		 		displayLoggedUserName();
		 		findUserByName('specialisthome');					 		
		 	}
		 	
		 	if(value === 'doctorsend' || value === 'doctortransaction' || value === 'clienttransaction') {
		 		displayLoggedUserName();
		 		displayPatientSelected();
		 	}
		 	
		 	if(value === 'specialistsend' ) {
		 		displayLoggedUserName();
		 		displayDoctorPatientSelected();
		 	}
		 	
		 	
		 	if(value === 'specialisttransaction') showSpecialistTransactionDetails();
		 	if(value === 'doctortransaction') showDoctorTransactionDetails('doctor');
		 	if(value === 'clienttransaction') showClientTransactionDetails();
		 	if(value === 'clienthome') {
		 		displayLoggedUserName();
		 		showDoctorTransactionDetails('client');					 		
		 	}	
		 	if(value === 'clientsenddata') {
		 		displayLoggedUserName();
		 		displayDoctorSelected();
		 	}		 	
		 	if(value === 'mapspecialist') {
		 		getSpecialistDetails();
		 	}
		};
		
		function displayLoggedUserName() {		  				
			self.user.userName = $window.sessionStorage.getItem('sUserName').toUpperCase();
			console.log("Display Logged In User Name :: " + self.user.userName);
			$scope.errorMessage = '';
			$scope.successMessage = '';
		}		
		
		function displayDoctorSelected() {
			self.doctorName = $window.sessionStorage.getItem('sDoctorName');
			self.transaction.transactionDetailsId = $window.sessionStorage.getItem('sTransactionId');
			console.log("Doctor Name :: " + self.doctorName);
			console.log("Transaction Details Id :: " + self.transaction.transactionDetailsId);
		}
		
		function displayPatientSelected() {
			console.log("Display Logged In User Name");
			self.patient = angular.fromJson($window.sessionStorage.getItem('sPatientDetails'));
			self.patient.userName = self.patient.userName.toUpperCase();
			console.log("Patient Name Selected :: " + self.patient.userName);
			$scope.errorMessage = '';
			$scope.successMessage = '';
		}
		
		function displayDoctorPatientSelected() {
			console.log("Display Doctor Patient In User Name");
			self.patient = angular.fromJson($window.sessionStorage.getItem('sPatientDetails'));
			self.patient.userName = self.patient.userName.toUpperCase();
			self.doctorName = self.patient.doctorName.toUpperCase();
			console.log("Patient Name Selected :: " + self.patient.userName);
			console.log("Doctor Name Selected :: " + self.doctorName);
			$scope.errorMessage = '';
			$scope.successMessage = '';
		}
	
		function submit(value) {
			console.log("Submit :: value :: " + value)
			if (value === 'adminhome') submitSearchUserName();
			if (value === 'mappatients') saveDoctorPatientMapDetails();
			if (value === 'mapspecialist') saveSpecialistPatientMapDetails();
			if(value === 'doctorsend')  sendDetailsToPatient();
			if(value === 'patientsend') sendDetailsToDoctor();
			if(value === 'specialistsend') sendSpecialistDetailsToDoctor();
		}
		
		function fetchAllRoles() {
			UserDetailsService.fetchAllRoles().then(function(d) {
				console.log("Roles retrieved successfully");
				self.roles = d;
				self.user.roleId = -1;
			}, function(errResponse) {
				console.error('Error while fetching Roles');
			});
		}
		
		function submitSearchUserName() {		
			console.log("Role Id     :: " + self.user.roleId);
			console.log("Search Name :: " + self.searchName);
			UserDetailsService.submitSearchUserName(self.searchName, self.user.roleId).then(function(d) {
				console.log('Response :: ' + d);
				if(d.length > 0) {
					$scope.errorMessage = '';
					document.getElementById('userDetails').style.visibility = 'visible';
					var data = d;
					console.log('Traffic Data length :: ' + data);
					self.tableParams2 = new NgTableParams({}, { dataset: data});
				} else {
					console.error('Error while searching User');
					document.getElementById('userDetails').style.visibility = 'hidden';
					$scope.errorMessage = 'There are no Users for the keyword searched. Please refine your search';
				}							
				
			}, function(errResponse) {
				console.error('Error while searching User');
				$scope.errorMessage = 'Error Occured !! Please Search the User Details again';
				
			});
		}
		
		function editUser(user) {
			console.log('Value :: ' + user.userName);
			$window.sessionStorage.setItem('sSelectedUser', user.userName);
			window.location = 'edituser';
		}
		
		function remove(user) {						
			console.log('Value :: ' + user.userName);
			UserDetailsService.removeUserByName(user.userName).then(function(d) {
				console.log('Response :: ' + d);
				submitSearchUserName();
				
			}, function(errResponse) {
				console.error('Error while removing User');
				$scope.errorMessage = 'Error Occured !! Please try again';
				
			});
		}
		
		function generatePassword(user) {						
			console.log('Value :: ' + user.userName);
			UserDetailsService.generatePassword(user.userName).then(function(d) {
					console.log('User Password Generation Completed Successfully');
					var response = d
					console.log("response :: " + response);
					$scope.successMessage = 'There is an e-Mail sent with the User Password details. Please check';
				
			}, function(errResponse) {
				console.error('Error while removing User');
				$scope.errorMessage = 'Error Occured !! Please try again';
				
			});
		}
		
		function reset(){
	        self.searchName='';
	        $scope.adminHomeForm.$setPristine(); //reset Form
		}		
		
		function fetchDoctorDetails() {
			console.log(" Doctor Details :: STARTS");
			UserDetailsService.fetchDoctorDetails().then(function(d) {
				console.log("The Doctor details retrieved successfully");
				console.log("doctors :: " + d);
				self.doctors = d;
				self.user.userId = -1;
			}, function(errResponse) {
				console.error('Error while fetching Doctor Details');
			});
		}
		
		function getSpecialistDetails() {
			console.log(" Doctor Details :: STARTS");
			self.user.userName = $window.sessionStorage.getItem('sUserName');
			console.log("Doctor Name :: " + self.user.userName);
			UserDetailsService.getSpecialistDetails().then(function(d) {
				console.log("The Specialist details retrieved successfully");
				console.log("specialists :: " + d);
				self.specialists = d;
				self.specialist.userId = -1;
				findUserByName('mapspecialist');				
				
			}, function(errResponse) {
				console.error('Error while fetching Doctor Details');
			});
		}
		
		function getPatientDetails() {
			console.log("Inside the getPatientDetails ");			
			self.user.userName = $window.sessionStorage.getItem('sUserName');
			console.log("Doctor Name :: " + self.user.userName);
			console.log("Patient Id  :: " + self.user.userId);
			UserDetailsService.fetchPatientDetails(self.user.userId).then(function(d) {
				console.log("Processing Patient details retrieved successfully");
				if(d.length > 0) {				
					$scope.errorMessage = '';
					self.patients = d;
					console.log("Map Patients :: " + self.patients.length);		
				
				} else {
					console.error('There are no Patients mapped for this Doctor.');					
					$scope.errorMessage = 'There are no Patients mapped for this Doctor.';
				}				

			}, function(errResponse) {
				console.error('Error while fetching Patient Details');
			});
		}
	
		function fetchPatientMappedToDoctor() {
			$scope.errorMessage = '';
			$scope.successMessage = '';
			console.log(" Fetch Patients Mapped to Doctor :: STARTS");
			console.log(" Selected Doctor Id :: " + self.user.userId);
			$scope.errorMessage = '';
			$window.sessionStorage.setItem('sSelectedDoctor', self.user.userId);
			if(self.user.userId > 0) {
				UserDetailsService.fetchPatientDetails(self.user.userId).then(function(d) {
					console.log("Processing Patient details retrieved successfully");
					if(d.length > 0) {				
						$scope.errorMessage = '';
						self.selectedpatients = d;						
						console.log("Map Patients :: " + self.selectedpatients.length);					
					
					} else {
						self.selectedpatients = [];
						console.error('There are no Patients mapped for this Doctor.');					
						$scope.errorMessage = 'There are no Patients mapped for this Doctor.';
					}		
					fetchPatientsNotMapped();
				
				}, function(errResponse) {
					console.error('Error while fetching Patient Details');
				});
			} else {
				self.selectedpatients = '';
				self.availablepatients = '';
			}
		}

		function fetchPatientsNotMapped() {
			console.log(" Patients Details Not Mapped :: STARTS");
			UserDetailsService.fetchPatientsNotMapped().then(function(d) {
				console.log("The Patient Details Not mapped retrieved successfully");
				if(d.length > 0) {
					self.availablepatients = d;
				} else {
					self.availablepatients = [];
				}				
				console.log(" Available Patients :: " + self.availablepatients.length);
			}, function(errResponse) {
				console.error('Error while Patient Details Not mapped ');
			});
		}
		
		function showPatientDetails() {
			console.log(" Doctor Selected :: " + self.user.userId);
			console.log(" Called From     :: " + self.value);
			
			if(self.user.userId === -1) {
				console.error('Error :: while show Patient Details ..... ');
				$scope.errorMessage = 'Please select a Doctor';							
			} else {
				$scope.errorMessage = '';
				$window.sessionStorage.setItem('sSelectedDoctor', self.user.userName);
				UserDetailsService.fetchPatientDetails(self.user.userId).then(function(d) {
					console.log("Processing Patient details retrieved successfully");
					if(d.length > 0) {						
						if(self.value === 'mappatients') {
							$scope.errorMessage = '';
							self.selectedpatients = d;
							console.log("Map Patients :: " + self.selectedpatients.length);
							fetchPatientsNotMapped();
						} else {
							$scope.errorMessage = '';
							document.getElementById('patientDetails').style.visibility = 'visible';
							var data = d;
							console.log("Response :: " + data);
							self.tableParams2 = new NgTableParams({}, { dataset: data});
						}
						
					} else {
						console.error('There are no Patients mapped for this Doctor.');
						if(self.value === 'mappatients') {
							fetchPatientsNotMapped();
						} else {
							document.getElementById('patientDetails').style.visibility = 'hidden';
						}
						$scope.errorMessage = 'There are no Patients mapped for this Doctor.';
					}				
				
				}, function(errResponse) {
					console.error('Error while fetching Patient Details');
				});
			}
		}
		
		function showSpecialistPatientDetails() {
			console.log(" Specialist Patient List Selected :: " + self.user.userId);
			console.log(" Called From     :: " + self.value);
			
			$scope.errorMessage = '';
			UserDetailsService.fetchSpecialistPatientDetails(self.user.userId).then(function(d) {
				console.log("Processing Patient details retrieved successfully");
				if(d.length > 0) {						
					$scope.errorMessage = '';
					document.getElementById('patientDetails').style.visibility = 'visible';
					var data = d;
					console.log("Response :: " + data);
					self.tableParams2 = new NgTableParams({}, { dataset: data});
					
				} else {
					console.error('There are no Patients mapped for this Doctor.');
					document.getElementById('patientDetails').style.visibility = 'hidden';
					$scope.errorMessage = 'There are no Patients mapped for this Doctor.';
				}				
			
			}, function(errResponse) {
				console.error('Error while fetching Patient Details');
			});
		}

		function saveDoctorPatientMapDetails() {
			console.log("Inside saveDoctorPatientMapDetails() ");
			var doctorName = $window.sessionStorage.getItem('sSelectedDoctor');
			console.log("Doctor Name :: " + doctorName);
			var x = document.getElementById("selectedPatients");
		    var patients = '';
		    var selPatients = x.options.length;
		    if(selPatients > 0) {
		    	for (var i = 0; i < x.options.length; i++) {
			    	patients = patients + x.options[i].text + ', ';
			    }
			    patients = patients.substring(0, patients.length - 2);
				console.log("output :: " + patients);		
				UserDetailsService.saveDoctorPatientMapDetails(doctorName, patients).then(function(d) {
					console.log("The Doctor - Patient details mapped successfully ");
					$scope.errorMessage = '';
	  				$scope.successMessage = 'The Doctor - Patient details mapped successfully ';
	  				
				}, function(errResponse) {
					console.error('Error while mapping Doctor - Patient details');
					$scope.errorMessage = 'Error while mapping Doctor - Patient details';
	  				$scope.successMessage = '';
				});
		    } else {
		    	$scope.errorMessage = 'Please map Patients to Doctor and Save ';
  				$scope.successMessage = '';
		    }
		}	
		
		function displayDoctorTransaction(value) {
			console.log("Value :: " + value);
			$window.sessionStorage.setItem('sPatientDetails', angular.toJson(value));
			window.location = 'doctortransactions';
		}
		
		function displaySpecialistTransaction(value) {
			console.log("Value :: " + value);
			$window.sessionStorage.setItem('sPatientDetails', angular.toJson(value));
			window.location = 'specialistransactions';
		}
		
		function showDoctorTransactionDetails(value) {
			var userName = '';
			if(value === 'doctor') {
				self.patient = angular.fromJson($window.sessionStorage.getItem('sPatientDetails'));
  				userName = self.patient.userName;
				
			} else {
				userName = self.user.userName;
			}
			console.log("Display Logged In User Name :: " + userName);
			UserDetailsService.showTransactionDetails(userName).then(function(d) {
				console.log("The Doctor Transaction details retrieved successfully");
				document.getElementById('transactionDetails').style.visibility = 'visible';
				var data = d;
				
				if(data.length > 0) {
					console.log("Response :: " + data);
					self.tableParams2 = new NgTableParams({}, { dataset: data});

				} else {
					console.error('There are no Transaction available for this Patient.');									
					document.getElementById('transactionDetails').style.visibility = 'hidden';
					$scope.errorMessage = 'There are no Transaction available for this Patient.';
				}
				
			}, function(errResponse) {
				console.error('Error while fetching Doctor Transaction Details');
			});
		}
		
		function showSpecialistTransactionDetails(value) {
			self.patient = angular.fromJson($window.sessionStorage.getItem('sPatientDetails'));
  			var patientName = self.patient.userName;
  			self.user.userName = $window.sessionStorage.getItem('sUserName');
			console.log("Patient Name :: " + patientName);
			console.log("Doctor Name :: " + self.user.userName);
			UserDetailsService.showSpecialistTransactionDetails(self.user.userName, patientName).then(function(d) {
				console.log("The Doctor SpecialistTransaction details retrieved successfully");
				document.getElementById('transactionDetails').style.visibility = 'visible';
				var data = d;
				
				if(data.length > 0) {
					console.log("Response :: " + data);
					self.tableParams2 = new NgTableParams({}, { dataset: data});

				} else {
					console.error('There are no Transaction available for this Patient.');									
					document.getElementById('transactionDetails').style.visibility = 'hidden';
					$scope.errorMessage = 'There are no Transaction available for this Patient.';
				}
				
			}, function(errResponse) {
				console.error('Error while fetching Doctor Transaction Details');
			});
		}


		function showClientTransactionDetails(value) {
			
			var userName = self.user.userName;
			self.patient = angular.fromJson($window.sessionStorage.getItem('sPatientDetails'));
			var patientName = self.patient.userName;
			
			console.log("Doctor Name :: " + userName);
			console.log("Patient Name :: " + patientName);
			UserDetailsService.showClientTransactionDetails(userName, patientName).then(function(d) {
				console.log("The Client Transaction details retrieved successfully");
				document.getElementById('transactionDetails').style.visibility = 'visible';
				var data = d;
				
				if(data.length > 0) {
					console.log("Response :: " + data);
					self.tableParams2 = new NgTableParams({}, { dataset: data});

				} else {
					console.error('There are no Transaction available for this Patient.');									
					document.getElementById('transactionDetails').style.visibility = 'hidden';
					$scope.errorMessage = 'There are no Transaction available for this Patient.';
				}
				
			}, function(errResponse) {
				console.error('Error while fetching Client Transaction Details');
			});
		}
		
		function saveDoctorPatientMapDetails() {
			console.log("Inside saveDoctorPatientMapDetails() ");
			var doctorName = $window.sessionStorage.getItem('sSelectedDoctor');
			console.log("Doctor Name :: " + doctorName);
			var x = document.getElementById("selectedPatients");
		    var patients = '';
		    for (var i = 0; i < x.options.length; i++) {
		    	patients = patients + x.options[i].text + ', ';
		    }
		    patients = patients.substring(0, patients.length - 2);
			console.log("output :: " + patients);		
			UserDetailsService.saveDoctorPatientMapDetails(doctorName, patients).then(function(d) {
				console.log("The Doctor - Patient details mapped successfully ");
				$scope.errorMessage = '';
  				$scope.successMessage = 'The Doctor - Patient details mapped successfully ';
			}, function(errResponse) {
				console.error('Error while mapping Doctor - Patient details');
				$scope.errorMessage = 'Error while mapping Doctor - Patient details';
  				$scope.successMessage = '';
			});
		}
		
		function fetchSpecialistPatientMappedByDoctorName() {
			$scope.errorMessage = '';
			$scope.successMessage = '';
			console.log(" Fetch Specialist - Patients Mapped by Doctor :: STARTS");
			$scope.errorMessage = '';
			var doctorName = $window.sessionStorage.getItem('sUserName');
			UserDetailsService.fetchSpecialPatientMappedByDoctorName(doctorName).then(function(d) {
				console.log("Processing Specialist - Patient details retrieved successfully");
				if(d.length > 0) {
					$scope.errorMessage = '';
					document.getElementById('specialistPatientDetails').style.visibility = 'visible';
					var data = d;
					console.log('Data length :: ' + data);
					self.tableParams2 = new NgTableParams({}, { dataset: data});
				} else {
					console.error('Error while get the Specialist Patient map details User');
					document.getElementById('specialistPatientDetails').style.visibility = 'hidden';
					$scope.errorMessage = 'There are no Specialist - Patient mapped for this Doctor';
				}						
			
			}, function(errResponse) {
				console.error('Error while fetching Patient Details');
			});
		}
		
		function saveSpecialistPatientMapDetails() {
			console.log("Inside saveSpecialistPatientMapDetails() ");
			var doctorName = $window.sessionStorage.getItem('sUserName');
			var specialistId = self.specialist.userId;
			var patientId = self.patient.userId;
			console.log("specialistId :: " + specialistId);
			console.log("patientId :: " + patientId);
			
			if(specialistId === -1 && patientId ===  null) {
				$scope.errorMessage = 'Please select a Specialist and a Patient';
				return false;
			} else {
				if(specialistId === -1) {
					$scope.errorMessage = 'Please select a Specialist';
					return false;
				}
				if(patientId === null) {
					$scope.errorMessage = 'Please select a Patient';
					return false;
				}				
			}
			
			UserDetailsService.saveSpecialistPatientMapDetails(doctorName, specialistId, patientId).then(function(d) {
				console.log("The Specialist - Patient details mapped successfully ");
				$scope.errorMessage = '';
  				$scope.successMessage = 'The Specialist - Patient details mapped successfully ';
  				fetchSpecialistPatientMappedByDoctorName();
  				
			}, function(errResponse) {
				console.error('Error while mapping Specialist - Patient details');
				$scope.errorMessage = 'Error while mapping Specialist - Patient details';
  				$scope.successMessage = '';
			});
		}
		
		function displaySendPatient(value) {
			console.log("Value :: " + value);
			$window.sessionStorage.setItem('sPatientDetails', angular.toJson(value));
			window.location="doctorsenddata";
		}
		
		function displaySendSpecialist(value) {
			console.log("Value :: " + value);
			$window.sessionStorage.setItem('sPatientDetails', angular.toJson(value));
			window.location="specialistsenddata";
		}		
		
		function gotoClientSendData(value) {
			console.log("Doctor Name :: " + value.createdBy);
			console.log("Transaction Id :: " + value.transactionDetailsId);
			$window.sessionStorage.setItem('sDoctorName', value.createdBy);
			$window.sessionStorage.setItem('sTransactionId', value.transactionDetailsId);
			window.location = "clientsenddata";
		}
		
		function findUserByName(value) {
			console.log(" Find User Details By Name :: STARTS");
			UserDetailsService.findUserByName(self.user.userName).then(function(d) {
				console.log("The Doctor details By Doctor Id retrieved successfully");
				self.user = d;
				console.log("User Id :: " + self.user.userId);
				if(value === 'doctorhome') showPatientDetails();
				if(value === 'specialisthome') showSpecialistPatientDetails();
				if(value === 'mapspecialist') { 
					fetchSpecialistPatientMappedByDoctorName();
					getPatientDetails();
				}
				
			}, function(errResponse) {
				console.error('Error while fetching Doctor Details By Doctor Id');
			});
		}
		
		function displayDoctorUserName() {
			console.log("Display Selected Doctor Name");
			self.user.userId = $window.sessionStorage.getItem('sSelectedDoctor');
			
			console.log("Select Doctor Id :: " + self.user.userId);
			$scope.errorMessage = '';
			$scope.successMessage = '';
			
			console.log(" Doctor Details :: STARTS");
			UserDetailsService.fetchDoctorDetailsById(self.user.userId).then(function(d) {
				console.log("The Doctor details By Doctor Id retrieved successfully");
				self.user.userName = d.toUpperCase();
				showPatientDetails();
			}, function(errResponse) {
				console.error('Error while fetching Doctor Details By Doctor Id');
			});
			
		}			
	
		function fetchDoctorDetails() {
			console.log(" Doctor Details :: STARTS");
			UserDetailsService.fetchDoctorDetails().then(function(d) {
				console.log("The Doctor details retrieved successfully");
				self.doctors = d;
				self.user.userId = -1;
			}, function(errResponse) {
				console.error('Error while fetching Doctor Details');
			});
		}
		
		function sendDetailsToPatient() {
			console.log(" Send Details To Patient :: STARTS");
			console.log(" Doctor Name :: " + self.user.userName);
			console.log(" Patient Name :: " + self.patient.userName);
			console.log(" Subject :: " + self.subject);
			console.log(" Description :: " + self.description);						
			UserDetailsService.sendDetailsToPatient(self.user.userName, self.patient.userName, self.subject, self.description).then(function(d) {
				console.log("The Patient Details are send successfully");
				$scope.successMessage = 'The Patient Details are send successfully.';
				$scope.errorMessage = '';
				
			}, function(errResponse) {
				console.error('Error while sending the Patient Details. Please try again');
				$scope.errorMessage = 'The Patient details are not send. Please try again.';
				$scope.successMessage = '';
			});
		}
		
		function sendDetailsToDoctor() {
			console.log(" Send Details To Doctor :: STARTS");
			console.log(" Doctor Name :: " + self.doctorName);
			console.log(" Patient Name :: " + self.user.userName);
			console.log(" Subject :: " + self.subject);
			console.log(" Description :: " + self.description);	
			console.log(" Transaction Id :: " + self.transaction.transactionDetailsId);	
			
			UserDetailsService.sendDetailsToDoctor(self.user.userName, self.doctorName, self.subject, 
					self.description, self.transaction.transactionDetailsId ).then(function(d) {
				console.log("The Doctor Details are send successfully");
				$scope.successMessage = 'The Doctor Details are send successfully.'; 
				$scope.errorMessage = '';
			
			}, function(errResponse) {
				console.error('Error while sending the Doctor Details. Please try again');
				$scope.errorMessage = 'The Doctor details are not send. Please try again.';
				$scope.successMessage = '';
			});
		}
		
		function sendSpecialistDetailsToDoctor() {
			console.log(" Send Details To Doctor :: STARTS");
			console.log(" Specialist Name :: " + self.user.userName);
			console.log(" Doctor Name :: " + self.doctorName);
			console.log(" Patient Name :: " + self.patient.userName);
			console.log(" Subject :: " + self.subject);
			console.log(" Description :: " + self.description);	
			console.log(" Transaction Id :: " + self.transaction.transactionDetailsId);	
			
			UserDetailsService.sendSpecialistDetailsToDoctor(self.user.userName, self.doctorName, self.patient.userName,
					self.subject, self.description).then(function(d) {
				console.log("The Specialist Details are send successfully");
				$scope.successMessage = 'The Specialist Details are send successfully.'; 
				$scope.errorMessage = '';
			
			}, function(errResponse) {
				console.error('Error while sending the Specialist Details. Please try again');
				$scope.errorMessage = 'The Specialist details are not send. Please try again.';
				$scope.successMessage = '';
			});
		}
					
} ]);
