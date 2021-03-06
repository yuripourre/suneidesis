package com.harium.suneidesis.repository.nitrite;

import com.harium.suneidesis.concept.Concept;
import com.harium.suneidesis.concept.ConceptType;
import com.harium.suneidesis.repository.RepositoryCursor;
import com.harium.suneidesis.repository.nitrite.DocumentMapper;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;

public class RepositoryConceptCursor implements RepositoryCursor<Concept> {

    private final Cursor cursor;

    public RepositoryConceptCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public int size() {
        return cursor.size();
    }

    public Concept firstOrDefault() {
        Document document = cursor.firstOrDefault();
        if (document == null) {
           return ConceptType.UNKNOWN;
        }
        return DocumentMapper.mapFromDocument(document);
    }

    public java.util.Iterator<Concept> iterator() {
        return new Iterator(cursor.iterator());
    }

    public static class Iterator implements java.util.Iterator<Concept> {
        final java.util.Iterator<Document> iterator;

        public Iterator(java.util.Iterator<Document> iterator) {
            this.iterator = iterator;
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public Concept next() {
            return DocumentMapper.mapFromDocument(iterator.next());
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }

}
