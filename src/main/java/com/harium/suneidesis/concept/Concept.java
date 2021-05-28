package com.harium.suneidesis.concept;


import com.harium.suneidesis.concept.primitive.Text;

public class Concept extends Thing {

	public static final String ATTRIBUTE_ID = "id";
	public static final String ATTRIBUTE_TYPE = "type";

	public static final Concept UNKNOWN = new Concept(ConceptType.UNKNOWN);
	public static final Concept SECRET = new Concept(ConceptType.SECRET);

	private String id;

	public Concept() {
		super();
	}

	public Concept(String name) {
		super();
		setName(name);
	}

	public Concept(Concept type) {
		super();
		type(type);
	}

	public Concept(Concept type, DataType dataType) {
		super();
		type(type);
		dataType(dataType);
	}

	public Concept(String name, Concept type) {
		super();
		type(type);
		setName(name);
	}

	public Concept getType() {
		return getAttributes().get(ATTRIBUTE_TYPE);
	}

	public Concept type(Concept type) {
		getAttributes().insert(ATTRIBUTE_TYPE, type);
		return this;
	}

	public String getIdText() {
		return id;
	}

	public Concept getId() {
		return getAttributes().get(ATTRIBUTE_ID);
	}

	public Concept id(String id) {
		this.id = id;
		getAttributes().insert(ATTRIBUTE_ID, new Text(id));
		return this;
	}

	public DataType getDataType() {
		return getAttributes().getDataType();
	}

	public Concept dataType(DataType dataType) {
		getAttributes().setDataType(dataType);
		return this;
	}

	public Concept is(Concept concept) {
		getAttributes().is(concept);
		return this;
	}

	public Concept can(Action action) {
		getAttributes().can(action);
		return this;
	}

	public Concept has(String key, Concept property) {
		return set(key, property);
	}

	public boolean hasKey(String key) {
		return getAttributes().contains(key);
	}

	public Concept set(String key, Concept property) {
		getAttributes().insert(key, property);
		return this;
	}

	public Concept hasQuantity(Concept property, Measurement measurement) {
		getAttributes().hasPart(property, measurement);
		return this;
	}

	public Concept hasQuantity(String property, Measurement measurement) {
		return hasQuantity(new Concept(property), measurement);
	}

	public Concept hasNoQuantity(Concept property) {
		return hasQuantity(property, Measurement.ZERO);
	}

	public Concept hasNoQuantity(String property) {
		return hasNoQuantity(new Concept(property));
	}

	public Concept get(String key) {
		return getAttributes().get(key);
	}

	public Concept isLocatedAt(Place place) {
		getAttributes().isLocatedAt(place);
		return this;
	}

	public Concept isLocatedAt(String placeName) {
		return this.isLocatedAt(new Place(placeName));
	}

	public boolean isUnknown() {
		return (ConceptType.UNKNOWN.equals(getType()));
	}

	public boolean isSecret() {
		return (ConceptType.SECRET.equals(getType()));
	}

}
