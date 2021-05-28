package com.harium.suneidesis.concept;

import com.harium.suneidesis.concept.attribute.Attributes;

import java.util.Objects;

public class Thing {

	private Attributes attributes;

	protected Thing() {
		super();
	}

	public Attributes getAttributes() {
		if (attributes == null) {
			attributes = new Attributes();
		}
		return attributes;
	}

	public String getName() {
		return getAttributes().getValue();
	}

	public void setName(String name) {
		getAttributes().setNameText(name);
	}

	public Concept getNameConcept() {
		return attributes.getValueConcept();
	}

	@Override
	public int hashCode() {
		return Objects.hash(attributes);
	}
}
