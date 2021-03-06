package com.harium.suneidesis.concept.beign.ancestry;

import com.harium.suneidesis.concept.Being;
import com.harium.suneidesis.concept.Concept;
import org.junit.Assert;
import org.junit.Test;

public class AncestryTest {

    @Test
    public void testHeteroSexualCoupleAncestry() {
        Being mother = new Being("Mary");
        Being father = new Being("John");

        BeingCouple couple = new BeingCouple(father, mother);

        Assert.assertEquals(father, couple.getFather());
        Assert.assertEquals(mother, couple.getMother());
    }

    @Test
    public void testInterspeciesCoupleAncestry() {
        Being mother = new Being("Mary");
        Concept father = new Concept("Spirit");

        GenericAncestry ancestry = new GenericAncestry(mother, father);

        Assert.assertEquals(2, ancestry.getProgenitors().size());
        Assert.assertEquals(mother, ancestry.getProgenitors().get(0));
        Assert.assertEquals(father, ancestry.getProgenitors().get(1));
    }

    @Test
    public void testSingleAncestor() {
        Being dolly = new Being("Dolly");

        SingleAncestor ancestor = new SingleAncestor(dolly);
        dolly.setAncestry(ancestor);

        Assert.assertEquals(dolly, ancestor.getSource());
    }

}
