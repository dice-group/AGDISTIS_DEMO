package org.aksw.mag.service;

import com.cybozu.labs.langdetect.LangDetectException;
import lombok.RequiredArgsConstructor;
import org.aksw.fox.binding.FoxParameter;
import org.aksw.fox.binding.IFoxApi;
import org.aksw.fox.data.Entity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class FoxService {

	private final IFoxApi foxApi;
	private final LanguageDetectionService languageDetectionService;

	/**
	 * Uses the FOX public API to extract the Entities from a text and annotates them.
	 *
	 * @param text the text to process
	 * @return the text with every found Entity annotated
	 */
	public String processText(String text) {
		String language;
		try {
			language = languageDetectionService.detectLanguage(text);
		} catch (LangDetectException e) {
			throw new IllegalArgumentException("Could not detect language of the given text", e);
		}
		FoxParameter.LANG foxLang = convertToFoxLang(language);
		List<Entity> entities = getEntities(text, foxLang);
		SortedMap<Integer, Entity> entityMap = new TreeMap<>();
		for (Entity e : entities) {
			for (int i : e.getIndices()) {
				entityMap.put(i, e);
			}
		}
		StringBuilder resultBuilder = new StringBuilder();
		int lastIndex = 0;
		for (Map.Entry<Integer, Entity> e : entityMap.entrySet()) {
			int startIndex = e.getKey();
			Entity entity = e.getValue();
			// Add the text between the last Entity and the current Entity
			resultBuilder.append(text.substring(lastIndex, startIndex));
			// Add the Entity with annotations
			resultBuilder.append('[');
			resultBuilder.append(entity.getText());
			resultBuilder.append(']');
			// Set lastIndex to the end of the current Entity
			lastIndex = startIndex + entity.getText().length();
		}
		// Add the remaining text after the last Entity
		resultBuilder.append(text.substring(lastIndex));
		return resultBuilder.toString();
	}

	/**
	 * Extracts the Entities from a text using the public FOX API
	 *
	 * @param text     the text to extract Entities from
	 * @param language the language of the text
	 * @return a list of extracted Entities
	 */
	public List<Entity> getEntities(String text, FoxParameter.LANG language) {
		return foxApi.setTask(FoxParameter.TASK.RE)
				.setOutputFormat(FoxParameter.OUTPUT.JSONLD)
				.setLang(language)
				.setInput(text)
				.send()
				.responseAsClasses()
				.getEntities();
	}

	/**
	 * Maps a language String to the enum used by FOX.
	 *
	 * @param language the language String
	 * @return a value of the FoxParameter.LANG type
	 */
	public FoxParameter.LANG convertToFoxLang(String language) {
		switch (language) {
			case "de":
				return FoxParameter.LANG.DE;
			case "en":
				return FoxParameter.LANG.EN;
			case "fr":
				return FoxParameter.LANG.FR;
			case "es":
				return FoxParameter.LANG.ES;
			case "nl":
				return FoxParameter.LANG.NL;
			default:
				throw new IllegalArgumentException("language {} not supported by fox");
		}
	}

}
