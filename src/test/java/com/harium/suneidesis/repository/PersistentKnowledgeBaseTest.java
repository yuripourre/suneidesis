package com.harium.suneidesis.repository;

import com.harium.suneidesis.concept.Concept;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersistentKnowledgeBaseTest {

    private PersistentKnowledgeBase persistentKnowledgeBase;

    @Before
    public void setUp() {
        persistentKnowledgeBase = new PersistentKnowledgeBase();
        persistentKnowledgeBase.clear();
    }

    @Test
    public void testInsert() {
        Concept appleTree = new Concept("apple tree").id("123");
        appleTree.set("fruit", new Concept("apple"));

        persistentKnowledgeBase.insert("randomKey", appleTree);

        Concept result = persistentKnowledgeBase.get("123");
        assertEquals("123", result.getIdText());
        assertEquals(appleTree.getName(), result.getName());
        assertTrue(result.hasKey("fruit"));
    }

}
