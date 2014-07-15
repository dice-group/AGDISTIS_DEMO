<html ng-app="AgdistisService">
<meta charset="utf-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/main.css">
<script src="resources/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-resource.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
</head>
<body>
	<div id="tabs" style="width: 700px; margin-left: auto; margin-right: auto;">
		<h3>AGDISTIS - Multilingual Disambiguation of Named Entities Using Linked Data</h3>
		<ul>
			<li><a href="#tabs-1">Demo</a></li>
			<li><a href="#tabs-2">Documentation</a></li>
			<li><a href="#tabs-3">Java Usage</a></li>
			<li><a href="#tabs-4">Command Line Usage</a></li>
		</ul>
		<div id="tabs-1">
			<div ng-controller="AgdistisCtrl">
				<div class="navbar navbar-default" role="navigation">

					<button type="button" class="btn btn-default navbar-btn" ng-click="german()">German Example</button>
					<button type="button" class="btn btn-default navbar-btn" ng-click="english()">English Example</button>
					<button type="button" class="btn btn-default navbar-btn" ng-click="chinese()">Chinese Example</button>
					<button type="button" class="btn btn-default navbar-btn" ng-click="englishUnanotated()">English unannotated</button>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="innerContainer">
							<span>Mark the entities with brackets</span>
							<form name="userInputForm" method="POST" action="/agdistis/agdistis">
								<textarea name="text" id="text" class="form-control" rows=12 ng-model="userInput"></textarea>
							</form>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-default" ng-click="input()">Get Entities</button>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-default" ng-click="foxit()">Annotate (english only)</button>
						</div>
					</div>
					<div ng-show="notSupported" class="col-md-6 entityContainer">Language "{{detectedlanguage}}" not supported</div>
					<div ng-show="show" class="col-md-6 entityContainer">
						<pre>{{namedEntities | json}}</pre>
					</div>
					<div>
						<button ng-click="dowloadInput()" class="btn btn-default navbar-btn">Download</button>
					</div>
				</div>
			</div>
		</div>
		<div id="tabs-2">
			<p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et
				lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis
				aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
		</div>
		<div id="tabs-3">Use from java</div>
		<div id="tabs-4">Use from commandline</div>
	</div>
	<script src="resources/js/scripts/agdistis.js" charset="UTF-8"></script>

</body>
</html>