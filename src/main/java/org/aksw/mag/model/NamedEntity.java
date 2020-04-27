package org.aksw.mag.model;

import lombok.Data;

import java.util.Arrays;

@Data
public class NamedEntity {

	private String namedEntity;
	private int start;
	private int offset = -1;
	private String disambiguatedURL;

}
