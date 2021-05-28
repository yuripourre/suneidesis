package com.harium.suneidesis.concept.word;

import com.harium.suneidesis.linguistic.nlp.pos.Tag;

public class WordAdjective extends Word {

	public static final String ATTRIBUTE_GENDER = "gender";

	public WordAdjective(String name) {
		super(name, Tag.ADJECTIVE);
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
