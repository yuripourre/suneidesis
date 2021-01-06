package com.harium.suneidesis.concept.word;

public class WordVerbConjugation extends Word {

	public static final String ATTRIBUTE_PERSON = "person";
	public static final String ATTRIBUTE_TENSE = "tense";

	public WordVerbConjugation(String name) {
		super(name, Word.TAG_VERB_CONJUGATION);
	}
	
	public Word getPerson() {
		return (Word) attributes.get(ATTRIBUTE_PERSON);
	}

	public String getPersonWord() {
		return attributes.get(ATTRIBUTE_PERSON).getName();
	}

	public void setPerson(Word gender) {
		attributes.set(ATTRIBUTE_PERSON, gender);
	}

	public Word getTense() {
		return (Word) attributes.get(ATTRIBUTE_TENSE);
	}

	public String getTenseWord() {
		return getTense().getName();
	}

	public void setTense(Word Tense) {
		attributes.set(ATTRIBUTE_TENSE, Tense);
	}
}