var app = angular.module('test', []);

// this represents the state of the dialog: a visible flag and the person being edited
var EditPersonDialogModel = function () {
  this.visible = false;
};
EditPersonDialogModel.prototype.open = function(person) {
	this.person = person;
	this.visible = true;
};
EditPersonDialogModel.prototype.close = function() {
	this.visible = false;
};

app.controller('ctrl', ['$scope', function ($scope) {     
  $scope.editDialog = new EditPersonDialogModel();
  $scope.person = {"name":""};
  $scope.persons = [{name: 'John'}, {name: 'Barbara'}];
  
  $scope.add = function() {
    $scope.persons.push({name: 'New Person'});
  };
  
  $scope.quickAdd = function() {
	  $scope.persons.push({name: 'New Person'});
	  $scope.editDialog.open( $scope.persons[ $scope.persons.length - 1 ] );
  };
  
  $scope.quickEdit = function() {
	  $scope.editDialog.open( $scope.person );
  };
  
}]);

app.directive('editPersonDialog', [function() {
  return {  restrict: 'E', scope: {  model: '=',  },
    link: function(scope, element, attributes) {
      scope.$watch('model.visible', function(newValue) {
    	//alert(newValue);
        var modalElement = element.find('.modal');
        //alert ( modalElement.modal( ) );
        modalElement.modal(newValue ? 'show' : 'hide');
      });
      
      element.on('shown.bs.modal', function() {
        scope.$apply(function() {
        	//alert( 'scope.model.visible shown' + scope.model.visible);
          scope.model.visible = true;
        });
      });

      element.on('hidden.bs.modal', function() {
        scope.$apply(function() {
        	//alert( 'scope.model.visible hidden' + scope.model.visible);
          scope.model.visible = false;
        });
      });
      
    },
    templateUrl: 'editPerson.htm',
  };
}]);