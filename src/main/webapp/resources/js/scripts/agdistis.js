var myApp = angular.module('AgdistisService', ['ngResource']);
myApp.factory('agdistis_en', function($resource) {
    return $resource('agdistis');
});
//TODO - get text language endpoint
myApp.controller('AgdistisCtrl', ['$scope', 'agdistis_en',
    function($scope, agdistis_en) {
        // Controller magic
        $scope.userInput = "University of Leipzig";
        $scope.input = function() {
            $scope.show = false
            $scope.notSupported = false;
            var entitiesDom = angular.element(document.querySelector('#entities'))
            var entities = []
            angular.forEach(entitiesDom[0].childNodes, function(value, key) {
                entities[entities.length] = value.childNodes[1].name;
            });
            agdistis_en.save({
                "text": $scope.userInput,
                "entities": entities
            }, function(data) {
                if (data.nosup) {
                    $scope.notSupported = true;
                    $scope.detectedlanguage = data.nosup;
                } else {
                    $scope.namedEntities = data.namedEntities
                    $scope.show = true
                    $scope.detectedlanguage = data.detectedlanguage
                }
            });
        }
    }
]);