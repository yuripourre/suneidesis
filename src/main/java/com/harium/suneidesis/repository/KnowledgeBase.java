package com.harium.suneidesis.repository;

import com.harium.suneidesis.concept.Concept;
import com.harium.suneidesis.concept.DataType;
import com.harium.suneidesis.concept.attribute.Inheritance;
import com.harium.suneidesis.repository.generator.BaseIdGenerator;
import com.harium.suneidesis.repository.generator.BaseTimeGenerator;
import com.harium.suneidesis.repository.generator.IdGenerator;
import com.harium.suneidesis.repository.decorator.EntryDecorator;
import com.harium.suneidesis.repository.decorator.TimeDecorator;
import com.harium.suneidesis.repository.listener.RepositoryListener;

import java.util.*;

import static com.harium.suneidesis.concept.Concept.ATTRIBUTE_ID;
import static com.harium.suneidesis.concept.attribute.Attributes.ATTRIBUTE_INHERITANCE;
import static com.harium.suneidesis.concept.attribute.Attributes.ATTRIBUTE_NAME;
import static com.harium.suneidesis.repository.decorator.TimeDecorator.ATTRIBUTE_CREATED_AT;

public abstract class KnowledgeBase implements Repository<Concept> {

    // Reserved word for introspection
    public static final String SELF = "self";

    private String name;

    private IdGenerator idGenerator = new BaseIdGenerator();

    protected List<EntryDecorator> decorators = new ArrayList<>();
    protected List<RepositoryListener> listeners = new ArrayList<>();

    public KnowledgeBase() {
        super();
    }

    public KnowledgeBase(String name) {
        super();
        this.name = name;
    }

    public KnowledgeBase(IdGenerator idGenerator) {
        super();
        this.idGenerator = idGenerator;
    }

    protected void initDecorators() {
        decorators.add(new TimeDecorator(new BaseTimeGenerator()));
    }

    @Override
    public Collection<Concept> getValues() {
        return getAll().values();
    }

    @Override
    public Concept insert(String key, Concept concept) {
        for (RepositoryListener repositoryListener: listeners) {
            repositoryListener.onInsert(key, concept);
        }
        return concept;
    }

    public abstract void merge(KnowledgeBase concepts);

    public List<Concept> query(Search search) {
        List<Concept> result = new ArrayList<>();
        for (Concept concept : getValues()) {
            // Search nested concepts
            if(!checkConcept(search, result, concept)) {
                for (Concept attribute : concept.getAttributes().getValues()) {
                    checkConcept(search, result, attribute);
                }
            }
        }
        return result;
    }

    private boolean checkConcept(Search search, List<Concept> result, Concept concept) {
        if (search.term.equals(concept.getName())) {
            // Check permission
            if (!concept.isSecret()) {
                result.add(concept);
            } /*else if (concept.getSourceId().getName().equals(search.userId)) {
                result.add(concept);
            }*/
            return true;
        }
        return false;
    }

    public void addAll(Collection<Concept> concepts) {
        for (Concept concept : concepts) {
            add(concept);
        }
    }

    public String add(Concept concept) {
        Concept id = concept.getId();
        if (!id.isUnknown() && contains(id.getName())) {
            return id.getName();
        }
        Concept wrap = decorate(concept);

        String idText = idGenerator.generateId();
        wrap.id(idText);
        insert(idText, wrap);

        if (DataType.OBJECT == concept.getDataType()) {
            for (Map.Entry<String, Concept> entry : concept.getAttributes().entrySet()) {
                if (ATTRIBUTE_CREATED_AT.equals(entry.getKey())) {
                    continue;
                }
                if (ATTRIBUTE_NAME.equals(entry.getKey())) {
                    continue;
                }
                if (ATTRIBUTE_ID.equals(entry.getKey())) {
                    continue;
                }
                if (ATTRIBUTE_INHERITANCE.equals(entry.getKey())) {
                    Inheritance inheritance = (Inheritance) entry.getValue();
                    addAll(inheritance.getMap().values());
                    continue;
                }
                add(entry.getValue());
            }
        }

        return idText;
    }

    protected Concept decorate(Concept info) {
        for (EntryDecorator decorator : decorators) {
            decorator.decorate(info);
        }
        return info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public void addListener(RepositoryListener listener) {
        listeners.add(listener);
    }

    public abstract void clear();
}
