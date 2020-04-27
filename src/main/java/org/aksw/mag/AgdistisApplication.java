package org.aksw.mag;

import lombok.extern.slf4j.Slf4j;
import org.aksw.fox.binding.FoxApi;
import org.aksw.fox.binding.FoxParameter;
import org.aksw.fox.binding.FoxResponse;
import org.aksw.fox.binding.IFoxApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@SpringBootApplication
public class AgdistisApplication {

	public static void main(String[] args) throws MalformedURLException {
		SpringApplication.run(AgdistisApplication.class, args);
	}

}
