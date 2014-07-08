<html ng-app="AgdistisService">
<meta charset="utf-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/main.css">
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-resource.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
	<div ng-controller="AgdistisCtrl">
		<div class="navbar navbar-default" role="navigation">
			<a class="navbar-brand" href="../">Agdistis Demo</a>
			<button type="button" class="btn btn-default navbar-btn"
				ng-click="german()">German Example</button>
			<button type="button" class="btn btn-default navbar-btn"
				ng-click="english()">English Example</button>
			<button type="button" class="btn btn-default navbar-btn"
				ng-click="chinese()">Chinese Example</button>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="innerContainer">
					<span>Mark the entities with brackets</span>
					<textarea class="form-control" rows=12 ng-model="userInput"></textarea>
				</div>
				<div class="col-md-1">
					<button type="button" class="btn btn-default" ng-click="input()">Get
						Entities</button>
				</div>
			</div>
			<div ng-show="show" class="col-md-6 entityContainer">
				<div ng-show="notSupported">Language "{{detectedlanguage}}"
					not supported</div>
				<pre>{{namedEntities | json}}</pre>
			</div>
		</div>
	</div>

	</div>
	<script src="resources/js/scripts/agdistis.js" charset="UTF-8"></script>
</body>
</html>