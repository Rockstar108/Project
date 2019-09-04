'use strict';

angular.module('myApp').controller('MapController', ['$scope', 'NgMap', function($scope, NgMap) {
	
	$scope.googleMapsUrl="https://maps.googleapis.com/maps/api/js?key=AIzaSyAoNtdxqrSq2XorM2vGtUAl60LRxbziqB0";
	
	NgMap.getMap().then(function(map) {
		 console.log(map.getCenter());
		    console.log('markers', map.markers);
		    console.log('shapes', map.shapes);
	});
		
}]);