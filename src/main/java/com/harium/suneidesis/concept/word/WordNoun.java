package com.harium.suneidesis.concept.word;

import com.harium.suneidesis.linguistic.nlp.pos.Tag;

public class WordNoun extends Word {

	private static final String ATTRIBUTE_GENDER = "gender";

	public static final Word GENDER_MASCULINE = new Word("masculine");
	public static final Word GENDER_FEMININE = new Word("feminine");
	public static final Word GENDER_NEUTRAL = new Word("neutral");

	public WordNoun(String name) {
		super(name, Tag.NOUN);
	}
	
	public Word getGender() {
		return (Word) get(ATTRIBUTE_GENDER);
	}

	public String getGenderWord() {
		return get(ATTRIBUTE_GENDER).getName();
	}

	public void setGender(Word gender) {
		getAttributes().insert(ATTRIBUTE_GENDER, gender);
	}

}
