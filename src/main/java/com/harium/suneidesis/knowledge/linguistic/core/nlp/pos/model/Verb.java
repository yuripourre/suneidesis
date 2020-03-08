package com.harium.suneidesis.knowledge.linguistic.core.nlp.pos.model;

public class Verb {

    private String word;

    // The same verb may have none or more than one
    private String preposition;

    public Verb() {
    }

    public Verb(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPreposition() {
        return preposition;
    }

    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }
}