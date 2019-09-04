'use strict';

var myApp = angular.module('myApp',[]);

myApp.directive('fileModel', ['$parse', function ($parse) {
	 return {
		 restrict: 'A',
		 link: function(scope, element, attrs) {
	 
			 var model = $parse(attrs.fileModel);
			 var modelSetter = model.assign;
			 element.bind('change', function(){
				 scope.$apply(function(){
					 modelSetter(scope, element[0].files[0]);
				 });
			 });
		 }
	 };
}]);

myApp.directive('compareTo', [function() {
    return {
        require: 'ngModel',
        scope: {
          otherModelValue: '=compareTo'
        },
        link: function(scope, element, attributes, ngModel) {

          ngModel.$validators.compareTo = function(modelValue) {
            return modelValue == scope.otherModelValue;
          };

          scope.$watch('otherModelValue', function() {
            ngModel.$validate();
          });
        }
      };
    }
]);
