<html ng-app="AgdistisService">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/jquery-ui-1.10.0.custom.css">
<!-- Optional theme -->
<!-- <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-resource.min.js"></script>
<script src="resources/js/jquery-1.11.1.min.js"></script>
<!-- <script src="resources/js/jquery-ui-1.10.0.custom.js"></script> -->
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/scripts/agdistis.js"></script>
</head>
<body unselectable="on">
<div ng-controller="AgdistisCtrl">
<div id="left-sidebar">
		<div class="subtitle">Text to be annotated</div>
		<!-- <div class="innerContainer"> -->
		<textarea ng-model="userInput"></textarea>
		<div class="innerContainer">
			<div id="sidebar-content" unselectable="off">{{userInput}}</div>
		</div>
		<div class="buttonContainer">
			<input type="button" id="newEntity"
				value="Mark selected text as new Entity" />
		</div>
	<button ng-click="input(entities)"></button>
</div>
<div id="right-sidebar">
	<div class="innerContainer"ng-model="entities">
		
	</div>

</div>



<div ng-repeat="(key, value) in result">
namedEntity: {{value.namedEntity}}<br>
start: {{value.start}}<br>
disambiguatedURL: {{value.disambiguatedURL}}<br>
offset {{value.offset}}<br>
</div>
</div>
<script src="resources/js/scripts/markentity.js"></script>
</body>
</html>