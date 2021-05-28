package com.harium.suneidesis.concept;

/**
 * Provenance is a model that wraps concepts to represent actions from being's perspective
 * The same fact(action) can be perceived differently by observers and/or actors
 */

public class Provenance {

    public static final String ATTRIBUTE_SOURCE = "source";
    public static final String ATTRIBUTE_SOURCE_ID = "source_id";
    public static final String ATTRIBUTE_ACQUISITION_METHOD = "acquisition_method";
    public static final String ATTRIBUTE_ACQUISITION_MEDIUM = "acquisition_medium";

    public static final String ATTRIBUTE_SUBJECT = "subject";
    public static final String ATTRIBUTE_PREDICATE = "predicate";
    public static final String ATTRIBUTE_OBJECT = "object";
    public static final String ATTRIBUTE_OBJECT_PART = "object_part";
    public static final String ATTRIBUTE_PLACE = "place";
    public static final String ATTRIBUTE_TIME = "time";
    public static final String ATTRIBUTE_PHENOMENON = "phenomenon";

    private final Concept concept;

    public Provenance(Concept concept) {
        this.concept = concept;
    }

    public Concept getSource() {
        return concept.getAttributes().get(ATTRIBUTE_SOURCE);
    }

    public Provenance source(Concept source) {
        setSource(concept, source);
        return this;
    }

    public static void setSource(Concept concept, Concept source) {
        concept.getAttributes().insert(ATTRIBUTE_SOURCE, source);
    }

    public Concept getSourceId() {
        return concept.getAttributes().get(ATTRIBUTE_SOURCE_ID);
    }

    public Provenance sourceId(Concept sourceId) {
        setSourceId(concept, sourceId);
        return this;
    }

    public static void setSourceId(Concept concept, Concept sourceId) {
        concept.getAttributes().insert(ATTRIBUTE_SOURCE_ID, sourceId);
    }

    public Concept getSubject() {
        return concept.getAttributes().get(ATTRIBUTE_SUBJECT);
    }

    public Provenance subject(Concept subject) {
        setSubject(concept, subject);
        return this;
    }

    public static void setSubject(Concept concept, Concept subject) {
        concept.getAttributes().insert(ATTRIBUTE_SUBJECT, subject);
    }

    public Concept getPredicate() {
        return concept.getAttributes().get(ATTRIBUTE_PREDICATE);
    }

    public Provenance predicate(Concept predicate) {
        setPredicate(concept, predicate);
        return this;
    }

    public static void setPredicate(Concept concept, Concept predicate) {
        concept.getAttributes().insert(ATTRIBUTE_PREDICATE, predicate);
    }

    public Concept getObject() {
        return concept.getAttributes().get(ATTRIBUTE_OBJECT);
    }

    public Provenance object(Concept object) {
        setObject(concept, object);
        return this;
    }

    public static void setObject(Concept concept, Concept object) {
        concept.getAttributes().insert(ATTRIBUTE_OBJECT, object);
    }

    public Concept getObjectPart() {
        return concept.getAttributes().get(ATTRIBUTE_OBJECT_PART);
    }

    public Provenance objectPart(Concept objectPart) {
        setObjectPart(concept, objectPart);
        return this;
    }

    public static void setObjectPart(Concept concept, Concept objectPart) {
        concept.getAttributes().insert(ATTRIBUTE_OBJECT_PART, objectPart);
    }

    public Concept getPlace() {
        return concept.getAttributes().get(ATTRIBUTE_PLACE);
    }

    public Provenance place(Place place) {
        setPlace(concept, place);
        return this;
    }

    public static void setPlace(Concept concept, Concept place) {
        concept.getAttributes().insert(ATTRIBUTE_PLACE, place);
    }

    public Concept getTime() {
        return concept.getAttributes().get(ATTRIBUTE_TIME);
    }

    public Provenance time(Time time) {
        setTime(concept, time);
        return this;
    }

    public static void setTime(Concept concept, Concept time) {
        concept.getAttributes().insert(ATTRIBUTE_TIME, time);
    }

    public Concept getWeather() {
        return concept.getAttributes().get(ATTRIBUTE_PHENOMENON);
    }

    public Provenance weather(Phenomenon phenomenon) {
        return phenomenon(phenomenon);
    }

    public Provenance phenomenon(Phenomenon phenomenon) {
        setPhenomenon(concept, phenomenon);
        return this;
    }

    public static void setPhenomenon(Concept concept, Concept phenomenon) {
        concept.getAttributes().insert(ATTRIBUTE_PHENOMENON, phenomenon);
    }

    public Concept getAcquisitionMethod() {
        return concept.getAttributes().get(ATTRIBUTE_ACQUISITION_METHOD);
    }

    public Provenance acquisitionMethod(Action action) {
        setAcquisitionMethod(concept, action);
        return this;
    }

    public static void setAcquisitionMethod(Concept concept, Action action) {
        concept.getAttributes().insert(ATTRIBUTE_ACQUISITION_METHOD, action);
    }

    public Concept getAcquisitionMedium() {
        return concept.getAttributes().get(ATTRIBUTE_ACQUISITION_MEDIUM);
    }

    public Provenance acquisitionMedium(Concept medium) {
        setAcquisitionMedium(concept, medium);
        return this;
    }

    public static void setAcquisitionMedium(Concept concept, Concept medium) {
        concept.getAttributes().insert(ATTRIBUTE_ACQUISITION_MEDIUM, medium);
    }
}
