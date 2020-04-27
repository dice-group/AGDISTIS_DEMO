package org.aksw.mag.model;

import lombok.Data;

import java.util.List;

@Data
public class Result {

	private NamedEntity[] namedEntities;
	private String detectedLanguage;

}
