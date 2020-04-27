package org.aksw.mag.controllers;

import com.cybozu.labs.langdetect.LangDetectException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aksw.mag.model.FoxContent;
import org.aksw.mag.model.NamedEntity;
import org.aksw.mag.service.FoxService;
import org.aksw.mag.service.LanguageDetectionService;
import org.aksw.mag.service.UtilsService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FoxController {

	private final FoxService foxService;
	private final LanguageDetectionService languageDetectionService;
	private final UtilsService utilsService;

	@PostMapping(value = "/foxit")
	public FoxContent foxEndpoint(@RequestBody FoxContent request) {
		log.info("from frontend to fox {}.", request);
		String text = utilsService.removeAnnotations(request.getText());

		FoxContent result = new FoxContent();

		result.setText(foxService.processText(text));
		return result;
	}

}
