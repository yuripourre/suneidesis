package com.harium.suneidesis.knowledge.linguistic.portuguese.nlp.pos;

import com.harium.suneidesis.knowledge.linguistic.core.nlp.pos.TagPair;
import com.harium.suneidesis.knowledge.linguistic.core.nlp.pos.database.WordDatabase;
import com.harium.suneidesis.knowledge.linguistic.core.nlp.pos.model.Verb;
import com.harium.suneidesis.knowledge.linguistic.core.nlp.pos.model.VerbConjugation;
import com.harium.suneidesis.knowledge.linguistic.core.nlp.pos.model.Word;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.harium.suneidesis.knowledge.linguistic.core.nlp.pos.Tag.*;

/**
 * Multipass POSTagger with few words
 */
public class MultipassPOSTagger extends DatabasePOSTagger {

    public static final String PASSIVE_VOICE_PARTICLE = "foi";

    public MultipassPOSTagger(WordDatabase database) {
        super(database);
    }

    @Override
    public TagPair[] posTag(String[] tokens) {
        TagPair[] output = initTagArray(tokens);

        handleFixedTags(output);
        handlePrepositions(output);
        handleDeterminers(output);
        turnUnknownIntoNouns(output);

        return output;
    }

    private void turnUnknownIntoNouns(TagPair[] output) {
        for (int i = 0; i < output.length; i++) {
            TagPair tagPair = output[i];
            if (tagPair.getTag() == UNKNOWN) {
                tagPair.setTag(NOUN);
            }
        }
    }

    private void handleDeterminers(TagPair[] output) {
        for (int i = 0; i < output.length; i++) {
            TagPair tagPair = output[i];
            if (tagPair.getTag() != UNKNOWN) {
                continue;
            }
            List<Word> words = database.findAllWords(tagPair.getWord());
            if (words == null) {
                continue;
            }

            if (words.stream().anyMatch(word -> DETERMINER.name().equals(word.getTag()))) {
                tagPair.setTag(DETERMINER);
                // Verify next words (they could be adjective, numerals and so on)
                if (output.length > i + 1) {
                    TagPair next = output[i + 1];
                    next.setTag(NOUN);
                }
            }
        }
    }

    private void handlePrepositions(TagPair[] output) {
        for (int i = 0; i < output.length; i++) {
            TagPair tagPair = output[i];
            if (VERB == tagPair.getTag()) {
                List<Word> words = database.findAllWords(tagPair.getWord());

                Verb verb;
                for(Word w: words) {
                    if (isVerb(w.getTag())) {
                        VerbConjugation verbConjugation = database.findVerbConjugationByWordId(w.getWordId());
                        verb = database.findVerbByWordId(verbConjugation.getVerbWordId());

                        String[] prepositionsList = verb.getPrepositions().split("\\|");
                        Set<String> prepositions = new HashSet<>(Arrays.asList(prepositionsList));

                        if (output.length > i + 1 && prepositions.contains(output[i + 1].getWord())) {
                            output[i + 1].setTag(PREPOSITION);
                        }
                        if (i > 0 && PASSIVE_VOICE_PARTICLE.equals(output[i - 1].getWord())) {
                            //Passive voice
                        }
                        return;
                    }
                }
            }
        }
    }

    private boolean isVerb(String tag) {
        if (tag == null) {
            return false;
        }
        return tag.startsWith("VERB");
    }

    private void handleFixedTags(TagPair[] output) {
        for (int i = 0; i < output.length; i++) {
            String word = output[i].getWord();
            List<Word> words = database.findAllWords(word);
            if (words == null) {
                continue;
            }
            if (words.stream().anyMatch(w -> PUNCTUATION.name().equals(w.getTag()))) {
                output[i].setTag(PUNCTUATION);
            } else if (words.stream().anyMatch(w -> VERB.name().equals(w.getTag())||VERB_PAST_TENSE.name().equals(w.getTag()))) {
                output[i].setTag(VERB);
            }
        }

    }

}
