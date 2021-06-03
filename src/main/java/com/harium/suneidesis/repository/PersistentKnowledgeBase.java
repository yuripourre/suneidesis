package com.harium.suneidesis.repository;

import com.harium.suneidesis.concept.Concept;
import com.harium.suneidesis.concept.ConceptType;
import com.harium.suneidesis.concept.attribute.Attributes;
import com.harium.suneidesis.repository.nitrite.DocumentMapper;
import com.harium.suneidesis.repository.nitrite.RepositoryConceptCursor;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;
import org.dizitart.no2.Filter;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.dizitart.no2.IndexOptions.indexOptions;
import static org.dizitart.no2.filters.Filters.eq;

/**
 * Persistent Knowledge Base based on Nitrite DB
 * https://www.dizitart.org/nitrite-database/
 */
public class PersistentKnowledgeBase extends KnowledgeBase {

    private Nitrite database;
    private NitriteCollection collection;

    public PersistentKnowledgeBase() {
        // Init memory database
        database = Nitrite.builder()
                .compressed()
                .openOrCreate();
        init();
    }

    public PersistentKnowledgeBase(String path) {
        // Init file based database
        database = Nitrite.builder()
                .compressed()
                .filePath(path)
                .openOrCreate();
        init();
    }

    public PersistentKnowledgeBase(String path, String user, String password) {
        // Init secured / file based database
        database = Nitrite.builder()
                .compressed()
                .filePath(path)
                .openOrCreate(user, password);
        init();
    }

    private void init() {
        collection = database.getCollection("db");
        collection.createIndex(Concept.ATTRIBUTE_ID, indexOptions(IndexType.Unique));
        collection.createIndex(Attributes.ATTRIBUTE_NAME, indexOptions(IndexType.NonUnique));
    }

    @Override
    public Concept add(String key, Concept concept) {
        Document saved = collection.find(eq(Concept.ATTRIBUTE_ID, concept.getId())).firstOrDefault();
        boolean exists = saved != null;

        Document doc;
        if (!exists) {
            doc = DocumentMapper.mapToDocument(concept);
        } else {
            doc = DocumentMapper.mapToDocument(saved, concept);
        }
        upsert(doc);

        return concept;
    }

    private void upsert(Document doc) {
        Document saved = null;

        boolean exists = false;
        if (doc.containsKey(Concept.ATTRIBUTE_ID)) {
            String id = doc.get(Concept.ATTRIBUTE_ID).toString();
            saved = collection.find(eq(Concept.ATTRIBUTE_ID, id)).firstOrDefault();
            if (saved != null) {
                // Merge documents
                saved.putAll(doc);
                exists = true;
            }
        }

        if (!exists) {
            collection.insert(doc);
        } else {
            //MERGE
            //Document abd = DocumentMapper.mapToDocument(saved, concept);

            collection.update(saved);
        }

        for (Object nested : doc.values()) {
            if (nested instanceof Document) {
                Document d = (Document) nested;
                upsert(d);
            }
        }
    }

    @Override
    public String add(Concept concept) {
        Concept id = concept.getIdConcept();
        if (id.isUnknown()) {
            String idText = idGenerator.generateId();
            concept.id(idText);
        }

        add(concept.getId(), concept);
        return concept.getId();
    }

    @Override
    public void merge(KnowledgeBase concepts) {
        Map<String, Concept> map = concepts.getAll();
        for (Map.Entry<String, Concept> entry : map.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        collection.drop();
    }

    @Override
    public void close() {
        collection.close();
    }

    @Override
    public boolean isClosed() {
        return collection.isClosed();
    }

    @Override
    public Map<String, Concept> getAll() {
        Cursor cursor = collection.find();

        Map<String, Concept> conceptMap = new HashMap<>();

        for (Document document : cursor) {
            Concept concept = DocumentMapper.mapFromDocument(document);
            if (document.containsKey(Concept.ATTRIBUTE_ID)) {
                String id = document.get(Concept.ATTRIBUTE_ID).toString();
                conceptMap.put(id, concept);
            }
        }

        return conceptMap;
    }

    @Override
    public Iterator<Concept> iterator() {
        Cursor cursor = collection.find();
        return new RepositoryConceptCursor(cursor).iterator();
    }

    @Override
    public Concept get(String key) {
        Cursor cursor = collection.find(eq(Concept.ATTRIBUTE_ID, key));

        if (cursor == null) {
            return ConceptType.UNKNOWN_TYPE;
        }
        return DocumentMapper.mapFromDocument(cursor.firstOrDefault());
    }

    @Override
    public boolean contains(String key) {
        Cursor cursor = collection.find(eq(Concept.ATTRIBUTE_ID, key));
        return cursor.iterator().hasNext();
    }

    public RepositoryConceptCursor find() {
        return new RepositoryConceptCursor(collection.find());
    }

    public RepositoryConceptCursor find(Filter filter) {
        return new RepositoryConceptCursor(collection.find(filter));
    }

}