package org.aksw.mag.configuration;

import lombok.RequiredArgsConstructor;
import org.aksw.fox.binding.FoxApi;
import org.aksw.fox.binding.IFoxApi;
import org.aksw.mag.properties.FoxProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@RequiredArgsConstructor
public class FoxConfiguration {

	private final FoxProperties foxProperties;

	@Bean
	@Scope("prototype")
	public IFoxApi foxApi() throws MalformedURLException {
		URL url = new URL(foxProperties.getFoxUrl());
		return new FoxApi()
				.setApiURL(url);
	}

}
