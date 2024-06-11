package com.kmartita.tools.data.generation.models;

import com.kmartita.tools.data.generation.HasName;

import java.util.HashMap;
import java.util.Map;

public class TestDataBuilder<Field extends Enum<Field> & HasName> {

    private final Map<Field, Object> fields = new HashMap<>();

    public TestData<Field> build() {
        return new TestData<>(fields);
    }

    public TestDataBuilder<Field> setField(Field field, Object value) {
        this.fields.put(field, value);
        return this;
    }
}
