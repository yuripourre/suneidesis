package com.harium.suneidesis.concept;

import org.junit.Test;

import static com.harium.suneidesis.concept.inspector.Inspector.does;
import static org.junit.Assert.*;

public class MeasurementTest {

    @Test
    public void testInit() {
        Measurement measurement = new Measurement("5", "m");
        assertEquals("5", measurement.getValueText());

        assertTrue(does(measurement.getValueConcept()).is(new Concept("5")));
        assertTrue(does(measurement.getUnit()).is(new Concept("m")));

        Measurement toCompare = new Measurement("5", "m");
        assertTrue(measurement.equals(toCompare));
    }

    @Test
    public void testCompare_DifferentUnits() {
        Measurement centimeter = new Measurement("5", "cm");
        Measurement meter = new Measurement("5", "m");

        assertFalse(does(meter).is(centimeter));
        assertFalse(meter.equals(centimeter));
    }

    @Test
    public void testCompare_DifferentValues() {
        Measurement measurement = new Measurement("5", "m");
        Measurement anotherMeasurement = new Measurement("6", "m");

        assertFalse(does(measurement).is(anotherMeasurement));
        assertFalse(measurement.equals(anotherMeasurement));
    }

}
