var myApp = angular.module('AgdistisService', ['ngResource']);

myApp.factory('agdistis_en', function($resource) {
    console.log("agdistis_en");
    return $resource('agdistisen');
});
//TODO - get text language endpoint
myApp.controller('AgdistisCtrl', ['$scope', 'agdistis_en',
    function($scope, agdistis_en) {
        // Controller magic
        $scope.userInput = "<entity>University of Leipzig</entity>";
        $scope.text = "Agdistis";

        $scope.input = function(text) {
            console.log("entities " + $scope.entities)
            console.log("user input: " + text);
            agdistis_en.save(
                $scope.entities,
                function(data) {
                    console.log(data)
                    $scope.result = data.result
                });
        }
    }
]);