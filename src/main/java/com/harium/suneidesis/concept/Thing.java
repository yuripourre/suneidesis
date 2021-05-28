package com.harium.suneidesis.concept;

import com.harium.suneidesis.concept.attribute.Attributes;
import com.harium.suneidesis.concept.primitive.Text;

import java.util.Objects;

public class Thing {

	protected final Attributes attributes = new Attributes();

	protected Thing() {
		super();
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public String getName() {
		return attributes.getValueContent();
	}

	public Concept getNameConcept() {
		return attributes.getValue();
	}

	public void setName(String name) {
		attributes.setValue(name);
	}

	public void setNameConcept(Concept nameConcept) {
		attributes.setNameConcept(nameConcept);
	}

	@Override
	public int hashCode() {
		return Objects.hash(attributes);
	}
}
