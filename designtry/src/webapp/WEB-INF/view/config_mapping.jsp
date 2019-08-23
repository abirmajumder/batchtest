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
				$scope.md = ${mapping};
				$scope.mappings = ${mappings};
				$scope.types = ${types};
				$scope.file = ${file};
				$scope.ttypes = [ "Direct", "Java", "JS" ];
				$scope.persist = function() {
					doPost($http, $scope, 'persistMapping');
				}
				$scope.reset = function() {
					$scope.md = JSON.parse( JSON.stringify( ${mapping} ) );	
				}
				$scope.removeRow = function( obj ) {
					$scope.mappings.slice(0,obj);	
				}
				$scope.addRow = function( ) {
					$scope.mappings.push( JSON.parse( JSON.stringify( ${mapping} ) ) );	
				}
			});
		</script>
		<style>
			table th {
				font-weight: bold;
				font-size: 14;
				color: blue;
			}
			table td {
				font-weight: normal;
				font-size: 12;
			}
		</style>
	</head>
	<body ng-controller = 'config_controller' ng-app = 'config_app' >
		<form name = 'theForm' >
			<div class = 'container-fluid' >
				<h3>Field Mapping</h3>
				<div class='col-sm-6' >
					<label>Business Line & File</label>
					<h4><b>{{ md.cnfFileGeneral.cnfBusinessLine.businessLine }} > {{ md.cnfFileGeneral.name }}</b></h4>
					<input type="button" ng-click='addRow()' class='btn btn-sm btn-success' value="+" />
				</div>
				<div class='col-sm-12'>
					<table class='table table-striped'>
						<thead>
							<tr>
								<th>Column</th>
								<th>Type</th>
								<th>Regex</th>
								<th>Transformation Type</th>
								<th>Transformation</th>
								<th/>
							</tr>								
						</thead>
						<tbody>
							<tr ng-repeat="x in mappings">
								<td><input class='form-control form-control-sm' ng-model='x.csvColumn' /></td>
								<td>
									<select class='form-control form-control-sm' ng-model='x.cnfColType'>
										<option ng-repeat="t in types" value="{{t}}">{{t.type}}</option>
									</select>
								</td>
								<td><input class='form-control form-control-sm' ng-model='x.validRegex' /></td>
								<td>
									<select class='form-control form-control-sm'>
										<option ng-repeat="tt in ttypes" >{{tt}}</option>
									</select>
								</td>
								<td>
									<textarea class='form-control form-control-sm' ng-model='x.dataLogic' rows="3" > </textarea>
								</td>
								<td> <input type="button" ng-click='removeRow(x)' class='btn btn-sm btn-danger' value="-" /> </td>
							</tr>
						</tbody>
					</table>	
				</div>
				<div class='col-sm-12' style="margin-top: 10px;" align="center">
					<input type="button" ng-click='persist()' class='btn btn-sm btn-success' value="Save Mapping" />
				</div>
			</div>
		</form>
	</body>
</html>				