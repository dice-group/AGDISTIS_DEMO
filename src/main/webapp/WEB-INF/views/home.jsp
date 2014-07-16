<html ng-app="AgdistisService">
<meta charset="utf-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/demo/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/demo/resources/css/main.css">
<script src="/demo/resources/js/jquery-1.11.1.min.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="/demo/resources/js/angular.min.js"></script>
<script src="/demo/resources/js/angular-resource.min.js"></script>
<script src="/demo/resources/js/bootstrap.min.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
		$("#accordion").accordion({
			collapsible : true,
			heightStyle : "content",
			autoHeight : false,
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
			<li><a href="#tabs-2">About</a></li>
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
						<textarea name="text" id="text" class="form-control" rows="6" ng-model="userInput"></textarea>
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
			<div class="text">
				<h3>What is AGDISTIS?</h3>
				AGDISTIS is an Open Source Named Entity Disambiguation Framework able to link entities against every Linked Data knowledge base.
				<h3>Where can I learn more about AGDISTIS?</h3>
				To learn more about AGDISTIS visit our <a target="_blank" style="color: rgb(26, 13, 171)" href="http://aksw.org/Projects/AGDISTIS.html">project homepage</a>.
				<h3>Who maintains AGDISTIS?</h3>
				We are a big team of maintainers:
				<ul>
					<li><a target="_blank" style="color: rgb(26, 13, 171)" href="http://aksw.org/RicardoUsbeck.html">Ricardo Usbeck (Principle Contact / Maintainer)</a></li>
					<li><a target="_blank" style="color: rgb(26, 13, 171)" href="http://aksw.org/AxelNgonga.html">Dr. Axel-C. Ngonga Ngomo</a></li>
					<li>Andreas Both</li>
					<li>Sandro Coelho</li>
					<li>Prof. Dr. Sören Auer</li>
					<li>Daniel Gerber</li>
				</ul>
				Furthermore, we thank Maximilian Speicher, Lars Wesemann and Dave Boden.
				<h3>How to cite AGDISTIS?</h3>
				If you want to cite this publication please use the Bibtex below:
				<pre>
@incollection{AGDISTIS,
 author = {Usbeck, Ricardo and {Ngonga Ngomo}, Axel-Cyrille and Michael, Röder and Auer, Sören and Gerber, Daniel and Both, Andreas},
 booktitle = {International Semantic Web Conference },
 title = {AGDISTIS - Agnostic Disambiguation of Named Entities Using Linked  Open Data},
 year = 2014
}</pre>
				<h3>Where is AGDISTIS used?</h3>
				Temporarily, we know our approach is used in:
				<ul>
					<li><a target="_blank" style="color: rgb(26, 13, 171)" href="http://aksw.org/Projects/REX.html">REX: Web-Scale Extension of RDF Knowledge Bases</a></li>
					<li><a target="_blank" style="color: rgb(26, 13, 171)" href="http://aksw.org/Projects/FOX.html">FOX: Federated knOwledge eXtraction Framework</a></li>
				</ul>
			</div>
		</div>
		<div id="tabs-3">Use from java</div>
		<div id="tabs-4">Use from commandline</div>
	</div>
	<script src="/demo/resources/js/scripts/agdistis.js" charset="UTF-8"></script>
</body>
</html>
