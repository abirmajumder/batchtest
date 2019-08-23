<html >
	<head >
		<title >Business Lines</title>
		<link rel = 'stylesheet' href = 'css/bootstrap.min.css' type = 'text/css' />
		<script src = 'js/jquery-1.9.1.js' type = 'text/javascript' ></script>
		<script src = 'js/bootstrap.min.js' type = 'text/javascript' ></script>
		<script src = 'js/angular.min.js' type = 'text/javascript' ></script>
		<script src = 'js/common.js' type = 'text/javascript' ></script>
		<script >
			var app = angular.module( 'config_app',[]);
			app.controller( 'config_controller', function($scope,$http) {
				$scope.file = ${file};
				$scope.types = ${types};
				$scope.mapping = ${mapping};
			});
		</script>
	</head>
	<body ng-controller = 'config_controller' ng-app = 'config_app' >
		<div class = 'container-fluid' >
			
		</div>
	</body>
</html>		
			