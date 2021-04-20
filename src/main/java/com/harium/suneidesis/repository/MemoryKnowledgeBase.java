package com.harium.suneidesis.repository;

import com.harium.suneidesis.concept.Concept;
import com.harium.suneidesis.generator.IdGenerator;

import java.util.HashMap;
import java.util.Map;

public class MemoryKnowledgeBase extends KnowledgeBase {

    private Map<String, Concept> data = new HashMap<>();

    public MemoryKnowledgeBase() {
        super();
    }

    public MemoryKnowledgeBase(String name) {
        super(name);
    }

    public MemoryKnowledgeBase(IdGenerator idGenerator) {
        super(idGenerator);
    }

    @Override
    public Map<String, Concept> getAll() {
        return data;
    }

    @Override
    public void insert(String key, Concept concept) {
        data.put(key, concept);
        super.insert(key, concept);
    }

    @Override
    public Concept get(String key) {
        return data.get(key);
    }

    @Override
    public boolean contains(String key) {
        return data.containsKey(key);
    }

    @Override
    public void merge(KnowledgeBase concepts) {
        // TODO Wrap concepts into concepts (like heard from something heard)
        // Concept -> merged from another base -> Real Fact = the real information
        for (Concept concept: concepts.getValues()) {
            add(concept);
        }
    }
}