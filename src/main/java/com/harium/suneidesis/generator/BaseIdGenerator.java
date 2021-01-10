package com.harium.suneidesis.generator;

public class BaseIdGenerator implements IdGenerator {

    private long value = 0;

    @Override
    public String generateId() {
        return Long.toString(value++);
    }
}
