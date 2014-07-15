var myApp = angular.module('AgdistisService', ['ngResource']);
myApp.factory('agdistis', function($resource) {
    return $resource('agdistis');
});
myApp.factory('foxit', function($resource) {
    return $resource('foxit');
});
myApp.controller('AgdistisCtrl', ['$scope', 'agdistis', 'foxit',
    function($scope, agdistis, foxit) {
        $scope.userInput = "University of Leipzig is in Leipzig";
        $scope.input = function() {
            $scope.show = false;
            $scope.notSupported = false;
            agdistis.save({
                "text": $scope.userInput
            }, function(data) {
                if (data.nosup) {
                    $scope.notSupported = true;
                    $scope.detectedlanguage = data.nosup;
                } else {
                    $scope.namedEntities = data;
                    $scope.show = true;
                }
            });

        };
        
        $scope.dowloadInput = function(){
        		
        	  $scope.show = false;
              $scope.notSupported = false;

              agdistis.save({
                  "text": $scope.userInput
              }, function(data) {
        
                	  var blob = new Blob([ JSON.stringify(data) ],{ type : "application/json"});
                	  var url = (window.URL || window.webkitURL).createObjectURL(blob);
                	  	
                	  var link = document.createElementNS("http://www.w3.org/1999/xhtml", "a");
                	  link.href = url;
                	  link.download = 'blob.json'; 

                	  var event = document.createEvent("MouseEvents");
                	  event.initEvent("click", true, false);
                	  link.dispatchEvent(event);  
                	  
              });
        };

        $scope.foxit = function() {
            $scope.show = false;
            $scope.notSupported = false;
            foxit.save({
                "text": $scope.userInput
            }, function(data) {
                $scope.userInput = data.text;
            });

        };

        $scope.german = function() {
            $scope.userInput = "Die [Universität Leipzig] – Alma Mater Lipsiensis (AML) – " +
                "ist die größte [Hochschule] in [Leipzig]. " +
                "Mit ihrem Gründungsjahr 1409 ist sie auf dem Gebiet der heutigen " +
                "[Bundesrepublik Deutschland] die zweitälteste, " +
                "seit ihrer Gründung ohne Unterbrechung arbeitende " +
                "[Universität] nach der [Ruprecht-Karls-Universität Heidelberg] (1386).";
        };

        $scope.english = function() {
            $scope.userInput = "[Leipzig University] (German: Universität Leipzig), located in [Leipzig] in the Free State of [Saxony, Germany], is one of the oldest universities in the world and the second-oldest university (by consecutive years of existence) in [Germany]. Famous alumni include [Leibniz], [Goethe], [Nietzsche], [Wagner], [Angela Merkel], [Raila Odinga], [Tycho Brahe] and nine [Nobel] laureates are associated with this university.";
        };

        $scope.chinese = function() {
            $scope.userInput = "[北京] 和 [上海] 分别是 [中国] 的政治和经济中心.";
        };

        $scope.englishUnanotated = function() {
            $scope.userInput = "Leipzig University (German: Universität Leipzig), located in Leipzig in the Free State of Saxony, Germany, is one of the oldest universities in the world and the second-oldest university (by consecutive years of existence) in Germany. Famous alumni include Leibniz, Goethe, Nietzsche, Wagner, Angela Merkel, Raila Odinga, Tycho Brahe and nine Nobel laureates are associated with this university.";
        };
    }
]);


