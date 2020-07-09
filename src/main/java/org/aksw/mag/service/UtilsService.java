package org.aksw.mag.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UtilsService {

	private Pattern entityTagPattern;

	public UtilsService() {
		entityTagPattern = Pattern.compile("(\\[|]|<entity>|</entity>)");
	}

	/**
	 * Removes all entity Annotations from a String.
	 *
	 * @param text the input String
	 * @return the String without entity Annotations
	 */
	public String removeAnnotations(String text) {
		Matcher matcher = entityTagPattern.matcher(text);
		return matcher.replaceAll("");
	}

}
