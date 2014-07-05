<html ng-app="AgdistisService">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/jquery-ui-1.10.0.custom.css">
<!-- Optional theme -->
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">

</head>
<body unselectable="off">
	<div ng-controller="AgdistisCtrl">
		<div class="page-header">Agdistis Demo</div>
		<div id="left-sidebar">
			<div class="subtitle">1. Input Text</div>
			<textarea ng-model="userInput"></textarea>
			<div class="subtitle">2. Mark Words</div>
			<div class="innerContainer">
				<div id="sidebar-content" unselectable="off">{{userInput}}</div>
			</div>
			<div class="buttonContainer">
				<input type="button" id="newEntity"
					value="Mark selected text as new Entity" />
			</div>
			<div class="subtitle">3. Send</div>
			<div class="buttonContainer">
				<button ng-click="input()">send</button>
			</div>
			<div ng-show="notSupported">Language {{detectedlanguage}} not
				supported</div>
			<div ng-show="show">
				Language: {{detectedlanguage}}
				<div ng-repeat="(key, value) in namedEntities">
					<br>namedEntity: {{value.namedEntity}}<br> start:
					{{value.start}}<br> disambiguatedURL: <a
						href="{{value.disambiguatedURL}}">{{value.disambiguatedURL}}</a><br>
					offset: {{value.offset}}
				</div>
			</div>
		</div>
		<div id="right-sidebar">
			<div id="entities" class="innerContainer"></div>
		</div>

	</div>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-resource.min.js"></script>
	<script src="resources/js/jquery-1.11.1.min.js"></script>
	<!-- <script src="resources/js/jquery-ui-1.10.0.custom.js"></script> -->
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/scripts/agdistis.js"></script>
	<script src="resources/js/scripts/markentity.js"></script>
</body>
</html>