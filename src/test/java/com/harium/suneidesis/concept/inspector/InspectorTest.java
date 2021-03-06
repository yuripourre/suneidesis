package com.harium.suneidesis.concept.inspector;

import com.harium.suneidesis.concept.Action;
import com.harium.suneidesis.concept.Concept;
import com.harium.suneidesis.concept.beign.Taxonomy;
import com.harium.suneidesis.concept.word.WordNoun;
import org.junit.Test;

import static com.harium.suneidesis.concept.helper.Inspector.does;
import static org.junit.Assert.*;

public class InspectorTest {

    @Test
    public void testIs() {
        Taxonomy animalia = new Taxonomy("taxonomy").kingdom(new Concept("animalia"));

        Concept animal = new Concept("animal");
        animal.has(Taxonomy.ATTRIBUTE_TAXONOMY, animalia);

        Concept dog = new Concept("dog");
        dog.is(animal);

        assertTrue(does(dog).is(animal));
        assertFalse(does(dog).can(new Action("fly")));
    }

    @Test
    public void testInstance() {
        Taxonomy animalia = new Taxonomy("taxonomy").kingdom(new Concept("animalia"));

        Concept animal = new Concept("animal");
        animal.has(Taxonomy.ATTRIBUTE_TAXONOMY, animalia);

        Concept dog = new Concept("dog");
        dog.is(animal);
        dog.can(new Action("walk"));

        Concept billy = new Concept("Billy");
        billy.is(dog);
        billy.has("nickname", new WordNoun("Bill"));

        assertTrue(does(billy).is(dog));
        assertEquals("Bill", billy.get("nickname").getName());
    }

}
