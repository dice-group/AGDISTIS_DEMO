package org.aksw.mag.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("mag-demo.agdistis")
public class AgdistisProperties {

	private String dbpediaEn = "https://agdistis.demos.dice-research.org/api/en/";
	private String dbpediaDe = "https://agdistis.demos.dice-research.org/api/de/";
	private String dbpediaEs = "https://agdistis.demos.dice-research.org/api/es/";
	private String dbpediaFr = "https://agdistis.demos.dice-research.org/api/fr/";
	private String dbpediaIt = "https://agdistis.demos.dice-research.org/api/it/";
	private String dbpediaJa = "https://agdistis.demos.dice-research.org/api/ja/";
	private String dbpediaNl = "https://agdistis.demos.dice-research.org/api/nl/";
	private String dbpediaPt = "https://agdistis.demos.dice-research.org/api/pt/";
	private String dbpediaZhCn = "https://agdistis.demos.dice-research.org/api/zh/";
	private String dbpediaZhTw = "https://agdistis.demos.dice-research.org/api/zh/";
	private String wikidataEn = "https://agdistis.demos.dice-research.org/api/en-wikidata/";

}
