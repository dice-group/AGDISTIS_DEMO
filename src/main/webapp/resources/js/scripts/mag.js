var myApp = angular.module('MagService', ['ngResource']);
myApp.factory('mag', function($resource) {
    return $resource('/mag/mag');
});
myApp.factory('foxit', function($resource) {
    return $resource('/mag/foxit');
});
myApp.controller('MagCtrl', ['$scope', 'mag', 'foxit',
    function($scope, mag, foxit) {
        $scope.userInput = "[Leipzig University] located in [Leipzig] in the Free State of [Saxony, Germany], is one of the oldest universities in the world and the second-oldest university (by consecutive years of existence) in [Germany]. Famous alumni include [Leibniz], [Goethe], [Nietzsche], [Wagner], [Angela Merkel], [Raila Odinga], [Tycho Brahe] and nine [Nobel laureates] are associated with this university.";
        $scope.knowledgeBase = "";
        $scope.input = function() {
            $scope.show = false;
            $scope.notSupported = false;
            mag.save({
                "text": $scope.userInput,
                "knowledgeBase" : $scope.knowledgeBase
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

              mag.save({
                  "text": $scope.userInput,
                  "knowledgeBase" : $scope.knowledgeBase
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

        $scope.english = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "dbpedia";
            $scope.userInput = "[Leipzig] is a city in the federal state of [Saxony], [Germany].";
        };
              
        $scope.german = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "dbpedia";
            $scope.userInput = "[Leipzig] ist eine kreisfreie Großstadt im [Freistaat Sachsen].";
        };
        
        $scope.spanish = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "dbpedia";
            $scope.userInput = "[Leipzig] (en [alemán estándar]) o [Lipsia], en español, es una ciudad [alemana] en el noroeste del estado de [Sajonia].";
        };
        
        $scope.french = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "dbpedia";
            $scope.userInput = "[Leipzig] (parfois [Leipsick] en français, dans un contexte historique) est une ville d'[Allemagne].";
        };
        
        $scope.italian = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "dbpedia";
            $scope.userInput = "[Lipsia] è una [città extracircondariale] di 531 562 abitanti della [Sassonia], in [Germania].";
        };
        
        $scope.japanese = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "dbpedia";
            $scope.userInput = "[ライプツィヒ] は、[ザクセン州]に属する[ドイツ]の都市である";
        };
        
        $scope.dutch = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "dbpedia";
            $scope.userInput = "[Leipzig] is een kreisfreie Stadt in [Duitsland] gelegen aan de [Pleiße] met 521.000 inwoners (2012).";
        };
        
        $scope.portuguese = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "dbpedia";
            $scope.userInput = "[Lípsia] é uma cidade independente do estado da [Saxônia] na [Alemanha].";
        };
        
        $scope.chinese = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "dbpedia";
            $scope.userInput = "[北京] 和 [上海] 分别是 [中国] 的政治和经济中心.";
        };
        
        $scope.wikidata = function() {
        	$scope.show = false;
       	 	$scope.showAnnotateButton = false;
       	 	$scope.knowledgeBase = "wikidata";
            $scope.userInput = "Today, [Barack Obama] visited [Berlin] and met [Angela Merkel].";
        };

//        $scope.englishUnanotated = function() {
//       	 	$scope.show = false;
//       	 	$scope.showAnnotateButton = true;
//       	 $scope.knowledgeBase = "dbpedia";
//            $scope.userInput = "Leipzig University (German: Universität Leipzig), located in Leipzig in the Free State of Saxony, Germany, is one of the oldest universities in the world and the second-oldest university (by consecutive years of existence) in Germany. Famous alumni include Leibniz, Goethe, Nietzsche, Wagner, Angela Merkel, Raila Odinga, Tycho Brahe and nine Nobel laureates are associated with this university.";
//        };
    }
]);


