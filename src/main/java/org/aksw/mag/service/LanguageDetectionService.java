package org.aksw.mag.service;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import org.springframework.stereotype.Service;

@Service
public class LanguageDetectionService {

	/**
	 * Detects the language of a given text
	 *
	 * @param text the text to detect the language of
	 * @return the two letter language code
	 * @throws LangDetectException when the language could not be detected
	 */
	public String detectLanguage(String text) throws LangDetectException {
		Detector detector = DetectorFactory.create();
		detector.append(text);
		return detector.detect();
	}

}
