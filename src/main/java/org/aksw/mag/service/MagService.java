package org.aksw.mag.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aksw.mag.model.NamedEntity;
import org.aksw.mag.properties.AgdistisProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MagService {

	private final RestTemplate restTemplate;
	private final AgdistisProperties agdistisProperties;
	private final ObjectMapper objectMapper;

	/**
	 * Extract NamedEntities from a given Text.
	 * The entities to extract have to enclosed by an \<entity\> tag.
	 *
	 * @param text          the text to extract NamedEntities from
	 * @param knowledgeBase the knowledge base in which to search for the NamedEntities
	 * @param language      the language of the text
	 * @return a array of NamedEntities
	 */
	public NamedEntity[] extractNamedEntities(String text, String knowledgeBase, String language) {
		log.debug("Extracting Entities from {}.", text);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		LinkedMultiValueMap<String, Object> fromData = new LinkedMultiValueMap<>();
		fromData.add("type", "agdistis");
		fromData.add("text", text);

		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(fromData, headers);

		// AGDISTIS does not set the Content-Type Header, thus RestTemplate can not convert the Content for us
		ResponseEntity<String> response = restTemplate.postForEntity(getAgdistisEndpoint(knowledgeBase, language), entity, String.class);
		log.debug("Response from AGDISTIS: {}; {}", response.getStatusCode(), response.getBody());
		if (response.getStatusCode() == HttpStatus.OK) {
			try {
				return objectMapper.readValue(response.getBody(), NamedEntity[].class);
			} catch (JsonProcessingException e) {
				throw new IllegalStateException("Agdistis returned a response that is not JSON", e);
			}
		} else {
			throw new IllegalStateException("Agdistis returned an error (" + response.getStatusCode() + ")");
		}
	}

	/**
	 * Returns the AGDISTIS endpoint to use for the given knowledge base and language.
	 *
	 * @param knowledgeBase the knowledge base of the requested endpoint
	 * @param language      the language of the requested endpoint
	 * @return an endpoint that handles the given combination of knowledge base and language
	 */
	public String getAgdistisEndpoint(String knowledgeBase, String language) {
		if (knowledgeBase.equals("dbpedia")) {
			switch (language) {
				case "en":
					return agdistisProperties.getDbpediaEn();
				case "de":
					return agdistisProperties.getDbpediaDe();
				case "es":
					return agdistisProperties.getDbpediaEs();
				case "fr":
					return agdistisProperties.getDbpediaFr();
				case "it":
					return agdistisProperties.getDbpediaIt();
				case "ja":
					return agdistisProperties.getDbpediaJa();
				case "nl":
					return agdistisProperties.getDbpediaNl();
				case "pt":
					return agdistisProperties.getDbpediaPt();
				case "tr":
					return agdistisProperties.getDbpediaTr();
				case "id":
					return agdistisProperties.getDbpediaId();
				case "sv":
					return agdistisProperties.getDbpediaSv();
				case "zh_cn":
					return agdistisProperties.getDbpediaZhCn();
				case "zh_tw":
					return agdistisProperties.getDbpediaZhTw();
				case "ko":
					return agdistisProperties.getDbpediaKo();
				default:
					throw new IllegalArgumentException(String.format("Language %s not supported for knowledge base %s.", language, knowledgeBase));
			}
		} else if (knowledgeBase.equals("wikidata")) {
			if (language.equals("en")) {
				return agdistisProperties.getWikidataEn();
			} else {
				throw new IllegalArgumentException(String.format("Language %s not supported for knowledge base %s.", language, knowledgeBase));
			}
		} else {
			throw new IllegalArgumentException("Unknown knowledge base " + knowledgeBase);
		}
	}

}
