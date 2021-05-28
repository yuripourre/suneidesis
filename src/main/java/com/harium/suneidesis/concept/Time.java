package com.harium.suneidesis.concept;

import com.harium.suneidesis.concept.word.Word;

public class Time extends Concept {

    public static final String ATTRIBUTE_START = "time_start";
    public static final String ATTRIBUTE_END = "time_end";

    public static final Time NOW = new Time("time_now");
    public static final Time YESTERDAY = new Time("time_yesterday");

    public Time(String when) {
        super(ConceptType.TIME_UNIT);
        start(when);
        end(when);
    }

    public Time(String name, String time) {
        super(name, ConceptType.TIME_UNIT);
        start(time);
        end(time);
    }

    public Time(String name, String start, String end) {
        super(name, ConceptType.TIME_UNIT);
        start(start);
        end(end);
    }

    public Concept getStart() {
        return get(ATTRIBUTE_START);
    }

    public Time start(String start) {
        has(ATTRIBUTE_START, new Word(start));
        return this;
    }

    public Concept getEnd() {
        return get(ATTRIBUTE_END);
    }

    public Time end(String end) {
        has(ATTRIBUTE_END, new Word(end));
        return this;
    }

}
