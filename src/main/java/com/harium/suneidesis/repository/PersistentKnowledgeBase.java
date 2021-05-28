package com.harium.suneidesis.repository;

import com.harium.suneidesis.concept.Concept;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.HashMap;
import java.util.Map;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 * Persistent Knowledge Base based on Nitrite DB
 * https://www.dizitart.org/nitrite-database/
 */
public class PersistentKnowledgeBase extends KnowledgeBase {

    private static final String DEFAULT_USER = "user";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String DEFAULT_FILE = "/tmp/test.db";

    private Nitrite database;

    public PersistentKnowledgeBase() {
        this(DEFAULT_USER, DEFAULT_PASSWORD);
    }

    public PersistentKnowledgeBase(String user, String password) {
        this(user, password, DEFAULT_FILE);
    }

    public PersistentKnowledgeBase(String user, String password, String filePath) {
        database = Nitrite.builder()
                .compressed()
                .filePath(filePath)
                .openOrCreate(user, password);
    }

    @Override
    public Concept insert(String key, Concept concept) {
        ObjectRepository<Concept> repository = database.getRepository(Concept.class);
        repository.insert(concept);
        super.insert(key, concept);
        return concept;
    }

    @Override
    public void merge(KnowledgeBase concepts) {

    }

    @Override
    public void clear() {
        ObjectRepository<Concept> repository = database.getRepository(Concept.class);
        repository.drop();
    }

    @Override
    public Map<String, Concept> getAll() {
        ObjectRepository<Concept> repository = database.getRepository(Concept.class);
        Cursor<Concept> cursor = repository.find();

        Map<String, Concept> conceptMap = new HashMap<>();
        for (Concept concept:cursor) {
            conceptMap.put(concept.getIdText(), concept);
        }

        return conceptMap;
    }

    @Override
    public Concept get(String key) {
        ObjectRepository<Concept> repository = database.getRepository(Concept.class);
        Cursor<Concept> cursor = repository.find(eq("id", key));

        if (cursor == null) {
           return Concept.UNKNOWN;
        }
        return cursor.firstOrDefault();
    }

    @Override
    public boolean contains(String key) {
        ObjectRepository<Concept> repository = database.getRepository(Concept.class);
        Cursor<Concept> cursor = repository.find(eq("id", key));

        return cursor.iterator().hasNext();
    }
}
