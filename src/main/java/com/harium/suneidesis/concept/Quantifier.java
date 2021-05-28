package com.harium.suneidesis.concept;

public class Quantifier {

    private static final String ATTRIBUTE_QUANTIFIER = "quantifier";

    public static final String QUANTIFIER_ALL = "all";
    public static final String QUANTIFIER_ALMOST_ALL = "almost_all";
    public static final String QUANTIFIER_NO = "no";
    public static final String QUANTIFIER_FEW = "few";
    public static final String QUANTIFIER_MUCH = "much";

    private final Concept concept;

    public Quantifier(Concept concept) {
        this.concept = concept;
    }

    public Concept getQuantifier() {
        return concept.getAttributes().get(ATTRIBUTE_QUANTIFIER);
    }

    public Quantifier quantifier(String modifier) {
        setQuantifier(concept, modifier);
        return this;
    }

    public static void setQuantifier(Concept concept, String quantifier) {
        concept.getAttributes().insert(ATTRIBUTE_QUANTIFIER, new Concept(quantifier));
    }

}
