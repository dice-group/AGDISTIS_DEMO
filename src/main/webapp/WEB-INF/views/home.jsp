<html ng-app="AgdistisService">
<meta charset="utf-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/demo/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/demo/resources/css/main.css">
<script src="/demo/resources/js/jquery-1.11.1.min.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-resource.min.js"></script>
<script src="/demo/resources/js/bootstrap.min.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
		$("#accordion").accordion({
			collapsible : true,
			heightStyle : "content",
			autoHeight: false,
			active : false
		});
	});
</script>
</head>
<body>
	<h1>AGDISTIS - Multilingual Disambiguation of Named Entities Using Linked Data</h1>

	<div id="tabs">
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
					<button type="button" class="btn btn-default navbar-btn" ng-click="englishUnanotated()">English unannotated</button>
					<button type="button" class="btn btn-default navbar-btn" ng-click="chinese()">Chinese Example</button>
				</div>
				<div class="innerContainer">
					<span>Mark the entities with square brackets.</span>
					<form name="userInputForm" method="POST" action="/agdistis/agdistis">
						<textarea name="text" id="text" class="form-control" rows=12 ng-model="userInput"></textarea>
					</form>
					<em ng-show="show">Annotated Text:</em>
					<p ng-show="show" id="annotated-text"></p>
				</div>
				<div id="buttons">
					<button type="button" class="btn btn-default" ng-click="input()">Get Entities</button>
					<button type="button" class="btn btn-default" ng-click="foxit()">Annotate (english only)</button>
					<button ng-show="show" ng-click="dowloadInput()" class="btn btn-default">Download</button>
				</div>
				<div ng-show="notSupported" class="entityContainer">Language "{{detectedlanguage}}" not supported!</div>
				<div ng-show="show" class="entityContainer" id="accordion">
					<h3>JSON Result</h3>
					<!--<pre id="named-entities">{{namedEntities | json}}</pre>-->
					<pre id="named-entities"></pre>
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
	<script src="/demo/resources/js/scripts/agdistis.js" charset="UTF-8"></script>
</body>
</html>
