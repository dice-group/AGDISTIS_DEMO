var myApp = angular.module('AgdistisService', ['ngResource']);
myApp.factory('agdistis', function($resource) {
    return $resource('/demo/agdistis');
});
myApp.factory('foxit', function($resource) {
    return $resource('/demo/foxit');
});
myApp.controller('AgdistisCtrl', ['$scope', 'agdistis', 'foxit',
    function($scope, agdistis, foxit) {
        $scope.userInput = "[Leipzig University] (German: Universität Leipzig), located in [Leipzig] in the Free State of [Saxony, Germany], is one of the oldest universities in the world and the second-oldest university (by consecutive years of existence) in [Germany]. Famous alumni include [Leibniz], [Goethe], [Nietzsche], [Wagner], [Angela Merkel], [Raila Odinga], [Tycho Brahe] and nine [Nobel laureates] are associated with this university.";
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
                    //$scope.namedEntities = data;
                    $scope.show = true;
                    $("#annotated-text").html($scope.userInput);
                    $("#named-entities").html(JSON.stringify(data, undefined, 2));
                    $("#accordion").accordion("refresh");
                    
                    var entities = data.namedEntities;
                    var colorPallet = ['#a6cee3','#1f78b4','#b2df8a','#33a02c','#fb9a99','#e31a1c','#fdbf6f','#ff7f00','#cab2d6'];
                    
                    for (var i=0; i<entities.length; ++i) {
                    	var ne = entities[i].namedEntity;
                    	var url = entities[i].disambiguatedURL;
                    	var coloredAT = $("#annotated-text").html().replace("[" + ne + "]", "<a target=\"_blank\" href="+url+" title="+url+" style=\"color:" + colorPallet[i%9] + "\">[" + ne + "]</a>");
                    	var coloredPre = $("#named-entities").html().replace("\"" + ne + "\"", "<span title="+url+" style=\"color:" + colorPallet[i%9] + "\">\"" + ne + "\"</span>");
                    	
                    	$("#annotated-text").html(coloredAT);
                    	$("#named-entities").html(coloredPre);
                    }
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
                	  $scope.show = true;
              });
        };

        $scope.foxit = function() {
       	 	$scope.showAnnotateButton = false;
            $scope.show = false;
            $scope.notSupported = false;
            foxit.save({
                "text": $scope.userInput
            }, function(data) {
                $scope.userInput = data.text;
            });

        };

        $scope.german = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
            $scope.userInput = "Die [Universität Leipzig] – Alma Mater Lipsiensis (AML) – ist die größte [Hochschule] in [Leipzig]. Mit ihrem Gründungsjahr 1409 ist sie auf dem Gebiet der heutigen [Bundesrepublik Deutschland] die zweitälteste, seit ihrer Gründung ohne Unterbrechung arbeitende [Universität] nach der [Ruprecht-Karls-Universität Heidelberg] (1386).";
        };

        $scope.english = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
            $scope.userInput = "[Leipzig University] (German: Universität Leipzig), located in [Leipzig] in the Free State of [Saxony, Germany], is one of the oldest universities in the world and the second-oldest university (by consecutive years of existence) in [Germany]. Famous alumni include [Leibniz], [Goethe], [Nietzsche], [Wagner], [Angela Merkel], [Raila Odinga], [Tycho Brahe] and nine [Nobel laureates] are associated with this university.";
        };

        $scope.chinese = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
            $scope.userInput = "[北京] 和 [上海] 分别是 [中国] 的政治和经济中心.";
        };

        $scope.englishUnanotated = function() {
       	 	$scope.show = false;
       	 	$scope.showAnnotateButton = true;
            $scope.userInput = "Leipzig University (German: Universität Leipzig), located in Leipzig in the Free State of Saxony, Germany, is one of the oldest universities in the world and the second-oldest university (by consecutive years of existence) in Germany. Famous alumni include Leibniz, Goethe, Nietzsche, Wagner, Angela Merkel, Raila Odinga, Tycho Brahe and nine Nobel laureates are associated with this university.";
        };
    }
]);


