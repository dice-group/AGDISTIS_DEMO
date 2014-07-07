<html ng-app="AgdistisService">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/jquery-ui-1.10.0.custom.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">

<script src="resources/js/jquery-1.11.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-resource.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</head>
<body>

	<div ng-controller="AgdistisCtrl">
		<div class=row>
			<div class="col-md-6 col-md-offset-2">
				<h1>Agdistis Demo</h1>
			</div>
		</div>
		<div ng-hide="showAnnotation" class="row">

			<div class="col-md-6 col-md-offset-2">

				<div class="innerContainer">
					<textarea class="form-control" rows=3 ng-model="userInput"></textarea>
				</div>
				<div class="col-md-6">
					<button type="button" class="btn btn-default"
						ng-click="showAnnotation=true">Submit</button>
				</div>
			</div>
		</div>

		<div ng-show="showAnnotation">
			<div class="row">
				<div class="col-md-6 col-md-offset-2">
					<div class="innerContainer">
						<div id="sidebar-content" unselectable="off">{{userInput}}</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-md-offset-2">
					<button id="newEntity" type="button" class="btn btn-default">Mark
						selected text as new Entity</button>
					<button type="button" class="btn btn-default"
						ng-click="resetEverything()">Reset/Edit Text</button>
					<div id="entities" class="innerContainer"></div>
					<button type="button" class="btn btn-default" ng-click="input()">Get
						Entities</button>
					<div ng-show="show" class="innerContainer">
						<div ng-show="notSupported">Language "{{detectedlanguage}}"
							not supported</div>
						<pre>{{namedEntities | json}}</pre>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

	</div>
	<script src="resources/js/scripts/agdistis.js"></script>
	<script src="resources/js/scripts/markentity.js"></script>
</body>
</html>