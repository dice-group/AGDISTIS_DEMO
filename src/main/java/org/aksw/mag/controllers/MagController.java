package org.aksw.mag.controllers;

import com.cybozu.labs.langdetect.LangDetectException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aksw.mag.model.MagRequest;
import org.aksw.mag.model.NamedEntity;
import org.aksw.mag.model.Result;
import org.aksw.mag.model.UnsupportedResult;
import org.aksw.mag.service.LanguageDetectionService;
import org.aksw.mag.service.MagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MagController {

	private final LanguageDetectionService languageDetectionService;
	private final MagService magService;

	@ExceptionHandler(LangDetectException.class)
	public UnsupportedResult handleLangDetectException(LangDetectException exception) {
		UnsupportedResult unsupportedResult = new UnsupportedResult();
		unsupportedResult.setNosup(exception.getMessage());
		return unsupportedResult;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public UnsupportedResult handleLangDetectException(IllegalArgumentException exception) {
		UnsupportedResult unsupportedResult = new UnsupportedResult();
		unsupportedResult.setNosup(exception.getMessage());
		return unsupportedResult;
	}

	@PostMapping(value = "/mag")
	public Result magEndpoint(@RequestBody MagRequest request)
			throws LangDetectException {
		log.info("from Frontend to agdistis {}.", request);
		String detectedLanguage = languageDetectionService.detectLanguage(request.getText());
		log.debug("language: {}", detectedLanguage);
		String knowledgeBase = request.getKnowledgeBase();
		String text = request.getText().replace("[", "<entity>").replace("]", "</entity>");
		NamedEntity[] magResult = magService.extractNamedEntities(text, knowledgeBase, detectedLanguage);
		Result result = new Result();
		result.setDetectedLanguage(detectedLanguage);
		result.setNamedEntities(magResult);
		return result;
	}

}
