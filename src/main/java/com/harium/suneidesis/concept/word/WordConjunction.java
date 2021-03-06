package com.harium.suneidesis.concept.word;

import com.harium.suneidesis.concept.Concept;
import com.harium.suneidesis.linguistic.nlp.pos.Tag;

public class WordConjunction extends Word {

    public WordConjunction(String name) {
        super(name, Tag.CONJUCTION);
    }

    @Override
    public WordConjunction wrap(Concept concept) {
        super.wrap(concept);
        return this;
    }
}
