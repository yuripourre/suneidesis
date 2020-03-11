package com.harium.suneidesis.knowledge.linguistic.core.nlp.pos.model;

public class Numeral {

    private long wordId;

    private String type;

    public Numeral() {
    }

    public Numeral(long wordId, String type) {
        this.wordId = wordId;
        this.type = type;
    }

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
