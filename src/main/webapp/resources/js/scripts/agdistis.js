var myApp = angular.module('AgdistisService', ['ngResource']);
myApp.factory('agdistis', function($resource) {
    return $resource('agdistis');
});
myApp.controller('AgdistisCtrl', ['$scope', 'agdistis',
    function($scope, agdistis_en) {
        $scope.userInput = "[University of Leipzig] is in [Leipzig]";
        $scope.input = function() {
            $scope.show = false
            $scope.notSupported = false;
            agdistis_en.save({
                "text": $scope.userInput
            }, function(data) {
                if (data.nosup) {
                    $scope.notSupported = true;
                    $scope.detectedlanguage = data.nosup;
                } else {
                    $scope.namedEntities = data
                    $scope.show = true
                }
            });
        }

        $scope.german = function() {
            $scope.userInput = "Die [Universität Leipzig] – Alma Mater Lipsiensis (AML) – " +
                "ist die größte [Hochschule] in [Leipzig]. " +
                "Mit ihrem Gründungsjahr 1409 ist sie auf dem Gebiet der heutigen " +
                "[Bundesrepublik Deutschland] die zweitälteste, " +
                "seit ihrer Gründung ohne Unterbrechung arbeitende " +
                "[Universität] nach der [Ruprecht-Karls-Universität Heidelberg] (1386).";
        }

        $scope.english = function() {
            $scope.userInput = "[Leipzig University] (German: Universität Leipzig), located in [Leipzig] in the Free State of [Saxony, Germany], is one of the oldest universities in the world and the second-oldest university (by consecutive years of existence) in [Germany]. Famous alumni include [Leibniz], [Goethe], [Nietzsche], [Wagner], [Angela Merkel], [Raila Odinga], [Tycho Brahe] and nine [Nobel] laureates are associated with this university.";
        }

        $scope.chinese = function() {
            $scope.userInput = "[莱比锡大学]（Universität Leipzig）位于德国萨克森州的莱比锡，创立于1409年，是欧洲最古老的大学之一，也是现今德国管辖地区内历史第二悠久的大学，仅次于海德堡大学（1386年），另两所创建曾早于莱比锡大学的大学科隆大学（1388年—1798年，1919年重建）和埃尔福特大学（1392年—1816年，1994年重建）都曾关闭后又重开。1953年至1991年间，莱比锡大学曾名为“莱比锡卡尔·马克思大学”。";
        }
    }
]);