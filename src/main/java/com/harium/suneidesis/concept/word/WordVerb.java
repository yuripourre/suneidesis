package com.harium.suneidesis.concept.word;

import com.harium.suneidesis.linguistic.nlp.pos.Tag;

public class WordVerb extends Word {

	// The same verb may have none or more than one
	// E.g: to,from,of,X <- None
	// TODO change to list of concepts instead
	public static final String ATTRIBUTE_PREPOSITIONS = "prepositions";
	public static final String ATTRIBUTE_TRANSITIVITY = "transitivity";

	public WordVerb(String name) {
		super(name, Tag.VERB);
	}
	
	public Word getPrepositions() {
		return (Word) get(ATTRIBUTE_PREPOSITIONS);
	}

	public String getPrepositionsWord() {
		return get(ATTRIBUTE_PREPOSITIONS).getName();
	}

	public void setPrepositions(Word gender) {
		getAttributes().insert(ATTRIBUTE_PREPOSITIONS, gender);
	}

	public Word getTransitivity() {
		return (Word) get(ATTRIBUTE_TRANSITIVITY);
	}

	public String getTransitivityWord() {
		return getTransitivity().getName();
	}

	public void setTransitivity(Word Transitivity) {
		getAttributes().insert(ATTRIBUTE_TRANSITIVITY, Transitivity);
	}
}
