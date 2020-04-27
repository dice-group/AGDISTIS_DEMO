package org.aksw.mag.configuration;

import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class LanguageDetectionConfiguration implements InitializingBean {

	@Value("classpath*:lang_profiles/*")
	private Resource[] resourceFile;

	public void afterPropertiesSet() throws IOException, LangDetectException {
		List<String> profiles = new ArrayList<>();
		for(Resource r: resourceFile) {
			profiles.add(StreamUtils.copyToString(r.getInputStream(), StandardCharsets.UTF_8));
		}
		DetectorFactory.loadProfile(profiles);
	}

}
