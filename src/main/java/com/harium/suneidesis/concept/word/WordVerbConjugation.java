package com.harium.suneidesis.concept.word;

import com.harium.suneidesis.linguistic.nlp.pos.Tag;

public class WordVerbConjugation extends Word {

	public static final String ATTRIBUTE_PERSON = "person";
	public static final String ATTRIBUTE_TENSE = "tense";

	public WordVerbConjugation(String name) {
		super(name, Tag.VERB_CONJUGATION);
	}
	
	public Word getPerson() {
		return (Word) get(ATTRIBUTE_PERSON);
	}

	public String getPersonWord() {
		return get(ATTRIBUTE_PERSON).getName();
	}

	public void setPerson(Word gender) {
		getAttributes().insert(ATTRIBUTE_PERSON, gender);
	}

	public Word getTense() {
		return (Word) get(ATTRIBUTE_TENSE);
	}

	public String getTenseWord() {
		return getTense().getName();
	}

	public void setTense(Word Tense) {
		getAttributes().insert(ATTRIBUTE_TENSE, Tense);
	}
}
