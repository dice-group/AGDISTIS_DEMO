package org.aksw.mag.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URL;

@Data
@Component
@ConfigurationProperties("mag-demo.fox")
public class FoxProperties {

	private String foxUrl = "https://fox.demos.dice-research.org/fox";

}
