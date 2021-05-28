package com.harium.suneidesis.concept;

/**
 * Class that represents primitive data
 */
public class Primitive extends Concept {

    private String value;

    public Primitive(String value) {
        super(ConceptType.DATA, DataType.PRIMITIVE);
        setValue(value);
    }

    public Primitive(String value, Concept type) {
        super(type, DataType.PRIMITIVE);
        setValue(value);
    }

    private void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(boolean value) {
        setValue(String.valueOf(value));
    }

    public void setValue(byte value) {
        setValue(String.valueOf(value));
    }

    public void setValue(char value) {
        setValue(String.valueOf(value));
    }

    public void setValue(double value) {
        setValue(String.valueOf(value));
    }

    public void setValue(float value) {
        setValue(String.valueOf(value));
    }

    public void setValue(int value) {
        setValue(String.valueOf(value));
    }

    public void setValue(long value) {
        setValue(String.valueOf(value));
    }

    public void setValue(short value) {
        setValue(String.valueOf(value));
    }
}
