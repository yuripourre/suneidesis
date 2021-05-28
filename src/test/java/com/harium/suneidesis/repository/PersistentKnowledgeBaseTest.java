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
        Concept appleTree = new Concept("apple tree").id("1");
        Concept apple = new Concept("apple").id("2");
        appleTree.set("fruit", apple);

        persistentKnowledgeBase.insert("randomKey1", appleTree);
        persistentKnowledgeBase.insert("randomKey2", apple);

        Concept result = persistentKnowledgeBase.get("1");
        assertEquals("1", result.getIdText());
        assertEquals(appleTree.getName(), result.getName());
        assertTrue(result.hasKey("fruit"));
        assertEquals("2", result.get("fruit").getIdText());
    }

}
