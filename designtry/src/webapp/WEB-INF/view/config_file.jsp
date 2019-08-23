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
				$scope.md = ${file};
				$scope.types = ${types};
				$scope.mapping = ${mapping};
				$scope.persist = function() {
					doPost($http, $scope, 'persistFile');
				}
				$scope.reset = function() {
					$scope.md = JSON.parse( JSON.stringify( ${file} ) );	
				}
			});
		</script>
	</head>
	<body ng-controller = 'config_controller' ng-app = 'config_app' >
		<form name = 'theForm' >
			<div class = 'container-fluid' >
				<h3>File Information</h3>
				<div class='col-sm-12' >
					<label>Business Line</label>
					<h4><b>{{ md.cnfBusinessLine.businessLine }}</b></h4>
				</div>
				<div class='col-sm-12' >
					<label>File Name</label>
					<input class='form-control' name='fileName' ng-model='md.name' required="required" />
				</div>
				<div class='col-sm-6' >
					<label>Status</label>
					<input type="radio" ng-model="md.inVouge" value="Y">Enabled
	  				<input type="radio" ng-model="md.inVouge" value="N">Disabled
				</div>
				<div class='col-sm-6' >
					<label>File Type</label>
					<input type="radio" ng-model="md.type" value="Primary">Primary
	  				<input type="radio" ng-model="md.type" value="Child">Child
				</div>
				<div class='col-sm-12' >
					<label>Staging Directory</label>
					<input class='form-control' ng-model='md.stageDirectory' name='stagingDirectory' required="required" />
				</div>
				<div class='col-sm-12' >
					<label>Archive Directory</label>
					<input class='form-control' ng-model='md.archiveDirectory' required="required" />
				</div>
				<div class='col-sm-12' >
					<label>Error Directory</label>
					<input class='form-control' ng-model='md.errorDirectory' required="required" />
				</div>
				<div class='col-sm-12' >
					<label>Persistence Query</label>
					<textarea class='form-control' ng-model='md.cnfQuery.sqlQuery' rows=5 name='query' required="required"> </textarea>
				</div>
				<div class='col-sm-12' align="center" style="margin-top: 10px;">
					<input type="button" ng-click="persist()" value="Save File" class="btn btn-sm btn-success"
						ng-disabled = 'theForm.fileName.$invalid || theForm.stagingDirectory.$invalid || theForm.query.$invalid'
						/>
					<input type="button" ng-click="reset()" value="Reset" class="btn btn-sm btn-warning"/>
				</div>
			</div>
		</form>
	</body>
</html>		
			