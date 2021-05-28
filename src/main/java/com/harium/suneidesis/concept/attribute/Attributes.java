package com.harium.suneidesis.concept.attribute;

import com.harium.suneidesis.concept.*;
import com.harium.suneidesis.concept.Measurement;
import com.harium.suneidesis.concept.primitive.Text;
import com.harium.suneidesis.repository.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Attributes implements Repository<Concept> {

    public static final String ATTRIBUTE_ABILITIES = "abilities";
    public static final String ATTRIBUTE_PROPERTIES = "props";
    public static final String ATTRIBUTE_INHERITANCE = "inheritance";
    public static final String ATTRIBUTE_LOCATION = "location";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_DATA_TYPE = "dataType";

    public static final Text UNKNOWN_WORD = new Text("?");

    // TODO Unify with Primitive's value
    private String value;
    private DataType dataType = DataType.OBJECT;

    // What the concept can do
    private Abilities abilities;
    // What the concept has
    private Properties properties;
    // Primitive concepts that this concept borrows from
    private Inheritance inheritance;
    // How the concept is defined
    private final Map<String, Concept> attributeMap = new HashMap<>();

    public Concept get(String key) {
        Concept concept = attributeMap.get(key);
        if (concept != null) {
            return concept;
        }

        return getInheritance().getKey(key);
    }

    public boolean contains(String key) {
        return !get(key).isUnknown();
    }

    public boolean queryIs(String key) {
        return getInheritance().queryIs(key);
    }

    @Override
    public Map<String, Concept> getAll() {
        return attributeMap;
    }

    @Override
    public Collection<Concept> getValues() {
        return attributeMap.values();
    }

    public Concept insert(String key, Concept concept) {
        return attributeMap.put(key, concept);
    }

    public boolean remove(Concept concept) {
        return attributeMap.values().remove(concept);
    }

    public boolean removeByName(String key) {
        for (Iterator<Concept> iterator = attributeMap.values().iterator(); iterator.hasNext(); ) {
            Concept concept = iterator.next();
            if (key.equals(concept.getName())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public Abilities getAbilities() {
        if (abilities == null) {
            abilities = new Abilities();
            attributeMap.put(ATTRIBUTE_ABILITIES, abilities);
        }
        return abilities;
    }

    public Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            attributeMap.put(ATTRIBUTE_PROPERTIES, properties);
        }
        return properties;
    }

    public Inheritance getInheritance() {
        if (inheritance == null) {
            inheritance = new Inheritance();
            attributeMap.put(ATTRIBUTE_INHERITANCE, inheritance);
        }
        return inheritance;
    }

    /**
     * Helper Methods
     */
    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public Concept getValueConcept() {
        if (DataType.PRIMITIVE.equals(getDataType())) {
            return new Text(value);
        } else {
            Concept value = attributeMap.get(ATTRIBUTE_NAME);
            if (value != null) {
                return value;
            }
        }
        return UNKNOWN_WORD;
    }

    public void setNameText(String value) {
        this.value = value;
        Text nameWord = new Text(value);
        setNameConcept(nameWord);
    }

    public void setNameConcept(Concept name) {
        attributeMap.put(ATTRIBUTE_NAME, name);
    }

    // Assign a super class
    public void is(Concept concept) {
        getInheritance().add(concept);

        // TODO Optimize super classes (if a super class has the same inheritance, it can be removed)

        // For each attribute
        for (Map.Entry<String, Concept> entry : attributeMap.entrySet()) {
            String key = entry.getKey();
            Concept c = entry.getValue();

            // If both attributes and super class' attributes has the same concept, remove the concept from map
            if (c.equals(concept.getAttributes().get(key))) {
                attributeMap.remove(key);
            }
        }
    }

    public void merge(Attributes attributes) {
        for (Map.Entry<String, Concept> entry : attributes.attributeMap.entrySet()) {
            String key = entry.getKey();
            if (ATTRIBUTE_NAME.equals(key)) {
                continue;
            }
            if (ATTRIBUTE_PROPERTIES.equals(key)) {
                Properties properties = (Properties) entry.getValue();
                getProperties().merge(properties);
            } else if (ATTRIBUTE_ABILITIES.equals(key)) {
                Abilities abilities = (Abilities) entry.getValue();
                getAbilities().merge(abilities);
            } else if (ATTRIBUTE_INHERITANCE.equals(key)) {
                Inheritance inheritance = (Inheritance) entry.getValue();
                getInheritance().merge(inheritance);
            } else {
                attributeMap.put(key, entry.getValue());
            }
        }
    }

    public void can(Action concept) {
        getAbilities().add(concept);
    }

    public boolean can(String actionKey) {
        if (getAbilities().query(actionKey)) {
            return true;
        }

        return inheritance.can(actionKey);
    }

    public void hasPart(Concept part, Measurement measurement) {
        getProperties().add(part, measurement);
    }

    public void isLocatedAt(Place concept) {
        attributeMap.put(ATTRIBUTE_LOCATION, concept);
    }

    public Concept getProperty(String key) {
        return getProperties().query(key);
    }

    public boolean equals(Attributes attributes) {
        // Skip name from root
        return equals(this, attributes, false);
    }

    private static boolean equals(Attributes a, Attributes b, boolean checkName) {
        boolean equals = true;
        for (Map.Entry<String, Concept> entry: b.attributeMap.entrySet()) {
            String key = entry.getKey();
            if (checkName && ATTRIBUTE_NAME.equals(key)) {
                equals &= b.getValueConcept().equals(a.getValueConcept());
                continue;
            }
            if (ATTRIBUTE_PROPERTIES.equals(key)) {
                equals &= propertiesEquals(a.properties, b.properties);
            } else if (ATTRIBUTE_ABILITIES.equals(key)) {
                equals &= abilitiesEquals(a.abilities, b.abilities);
            } else if (ATTRIBUTE_INHERITANCE.equals(key)) {
                equals &= inheritanceEquals(a.inheritance, b.inheritance);
            } else {
                Concept value = entry.getValue();
                Concept toCompare = a.get(key);
                equals &= equals(value.getAttributes(), toCompare.getAttributes(), true);
            }
        }

        return equals;
    }

    private static boolean abilitiesEquals(Abilities a, Abilities b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    private static boolean propertiesEquals(Properties a, Properties b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    private static boolean inheritanceEquals(Inheritance a, Inheritance b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    public Set<Map.Entry<String, Concept>> entrySet() {
        return attributeMap.entrySet();
    }

}
