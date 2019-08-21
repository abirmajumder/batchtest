<html >
	<head >
		<title >Business Lines</title>
		<link rel = 'stylesheet' href = 'css/bootstrap.min.css' type = 'text/css' />
		<script src = 'js/jquery-1.9.1.js' type = 'text/javascript' ></script>
		<script src = 'js/bootstrap.min.js' type = 'text/javascript' ></script>
		<script src = 'js/angular.min.js' type = 'text/javascript' ></script>
		<script src = 'js/common.js' type = 'text/javascript' ></script>
		<script >
			
			var app = angular.module( 'cnfBusinessLine_app',[]);
			app.controller( 'cnfBusinessLine_controller', function($scope,$http) {
				$scope.obj = ${cnfBusinessLine};
				$scope.lines = ${lines};
				
				$scope.removeError = function() {
					$scope.errors[ nm ] = "";
				}
				$scope.persist = function() {
					doPost($http, $scope,'/batch/persist')
				}
				$scope.addRecord = function() {
					$scope.lines.push( JSON.parse( JSON.stringify(${cnfBusinessLine}) ) );
				}
			});

		</script>
	</head>
	<body ng-controller = 'cnfBusinessLine_controller' ng-app = 'cnfBusinessLine_app' >
		<form name = 'cnfBusinessLine_form' >
			<div class = 'container-fluid' >
				<div class = 'col-sm-12' >
					<h3 >Business Lines</h3>
				</div>
				<div class = 'col-sm-6' >
					<div class = 'col-sm-12' >
						<h4 >Add/Edit</h4>
						<input type="button" value="+"  class = 'btn btn-small btn-info' ng-click = 'addRecord()'/>
					</div>
					<div ng-repeat = 'x in lines' >
						<div class = 'col-sm-12' >
							<label for = 'businessLine' >Business Line</label>
							<input ng-model = 'x.businessLine' name = 'businessLine' ng-pattern = '/[a-zA-Z]*/' class = 'form-control' required = 'required' />
							<span ng-show = 'cnfBusinessLine_form.businessLine.$touched && cnfBusinessLine_form.businessLine.$invalid' >
								<span ng-show = 'cnfBusinessLine_form.businessLine.$error.pattern' >Only Alphabets</span>
								<span ng-show = 'cnfBusinessLine_form.businessLine.$error.required' >Enter Business Line</span>
							</span>
						</div>
					</div>
				</div>
				<div class = 'col-sm-12' >
					<div align = 'center' >
						<input ng-click = 'persist()' type = 'button' value = 'Save Business Lines' class = 'btn btn-small btn-info' ng-disabled = 'cnfBusinessLine_form.businessLine.$invalid' />
					</div>
				</div>
			</div>
		</form>
	</body>
</html>
