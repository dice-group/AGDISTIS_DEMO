<html ng-app="AgdistisService">
<%@ page pageEncoding="UTF-8" %>
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
	<div class="headerbox">
		<img class="headerimg" height="120px" src="/demo/resources/img/logo.png" alt="AGDISTIS Logo"  >
		<h1>AGDISTIS</h1>
		<h3>Multilingual Disambiguation of Named Entities Using Linked Data</h3>
	</div>
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
					<button type="button" class="buttonStyle btn btn-default navbar-btn" ng-click="german()">German Example</button>
					<button type="button" class="buttonStyle btn btn-default navbar-btn" ng-click="english()">English Example</button>
					<button type="button" class="buttonStyle btn btn-default navbar-btn" ng-click="englishUnanotated()">English unannotated</button>
					<button type="button" class="buttonStyle btn btn-default navbar-btn" ng-click="chinese()">Chinese Example</button>
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
					<button type="button" class="buttonStyle btn btn-default" ng-click="input()">Get Entities</button>
					<button type="button" class="buttonStyle btn btn-default" ng-click="foxit()" ng-show="showAnnotateButton"> Annotate (english only)</button>
					<button ng-show="show" ng-click="dowloadInput()" class="buttonStyle btn btn-default">Download</button>
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
				<ul>
					<li><a target="_blank" style="color: rgb(26, 13, 171)" href="http://aksw.org/Projects/REX.html">REX: Web-Scale Extension of RDF Knowledge Bases</a></li>
					<li><a target="_blank" style="color: rgb(26, 13, 171)" href="http://aksw.org/Projects/FOX.html">FOX: Federated knOwledge eXtraction Framework</a></li>
				</ul>
				Please let us know if you are using AGDISTIS. You can contact us <a style="color: rgb(26, 13, 171)" href="mailto:ngonga@informatik.uni-leipzig.de">here</a>.
			</div>
		</div>
		<div id="tabs-3">
			Use from java
			<div class="text">
				For running AGDISTIS on your machine go to the root directory and of AGDISTIS and execute
				<pre>mvn tomcat:run </pre>
				Now a webservice is running on localhost:8080. <br> <br> 
				The easiest way of running AGDISTIS from source is to have a look at the Java Class <a style="color: rgb(26, 13, 171)" href="https://github.com/AKSW/AGDISTIS/blob/master/src/test/java/AGDISTISTest.java">/src/test/java/AGDISTISTest.java</a>.
				<br> We hope you will enjoy using AGDISTIS!
			</div>
		</div>
		<div id="tabs-4">
			Use from commandline
			<div class="text">
				We deployed AGDISTIS as a RESTful service reachable via the following command:
				<pre>curl --data-urlencode "text='&lt;entity&gt;Barack Obama&lt;/entity&gt; arrives in &lt;entity&gt;Washington, D.C.&lt;/entity&gt;.'"\
				 -d type='agdistis' http://139.18.2.164:8080/AGDISTIS</pre>
				or
				<pre>curl --data-urlencode "text@test.txt" -d type=agdistis http://139.18.2.164:8080/AGDISTIS</pre>

				AGDISTIS also provides also a Wrapper for DBpedia Spotlight. Just change the "type" to "spotlight" instead of "agdistis" Please note that every entity you need disambiguated must be recognized
				beforehand. <br> Chinese endpoint:

				<pre>curl --data-urlencode "text='&lt;entity&gt;北京&lt;/entity&gt; 和 &lt;entity&gt;上海&lt;/entity&gt; 分别是 &lt;entity&gt;中国&lt;/entity&gt; 的政治和经济中心.'"\
				 -d type='agdistis' http://139.18.2.164:8080/AGDISTIS_ZH</pre>

				German endpoint:

				<pre>curl --data-urlencode "text='Die Stadt &lt;entity&gt;Dresden&lt;/entity&gt; liegt in &lt;entity&gt;Sachsen&lt;/entity&gt;.'" \
				-d type='agdistis' http://139.18.2.164:8080/AGDISTIS_DE</pre>
			</div>
		</div>
		<script src="/demo/resources/js/scripts/agdistis.js" charset="UTF-8"></script>
</body>
</html>
