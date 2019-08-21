function doPost( $http, $scope, url ) {
	alert( url );
	$http.post( url, $scope.md).then( 
		function(response) {
			alert( JSON.stringify( response ) );
			if( response.status == 200 ) {
				if( response.data.success ) {
					$scope.md = response.data.item;		
					$scope.errors = {};
				} else 
					$scope.errors = response.data.errors;
			}
		}, 
		function(err) { 
			alert(JSON.stringify(err)); 
		} 
	)
}