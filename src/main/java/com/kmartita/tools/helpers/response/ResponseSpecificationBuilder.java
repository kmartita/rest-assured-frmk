package com.kmartita.tools.helpers.response;

import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.generation.models.TestData;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class ResponseSpecificationBuilder {

    private final ResponseSpecBuilder builder;

    public ResponseSpecificationBuilder() {
        this.builder = new ResponseSpecBuilder();
    }

    public <Field extends Enum<Field> & HasName> ResponseSpecificationBuilder withParsedFields(TestData<Field> data) {
        parseFields(data.getFieldMap());
        return this;
    }

    private void parseFields(Map<String, Object> fields) {
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            if (entry.getValue() instanceof Map) {

                @SuppressWarnings(value = "unchecked")
                Map<String, Object> nestedFields = (Map<String, Object>) entry.getValue();
                parseFields(nestedFields);
            } else {
                addExpectBody(entry.getKey(), entry.getValue());
            }
        }
    }

    private void addExpectBody(String key, Object value) {
        if (value instanceof String) {
            builder.expectBody(key, equalTo(value));
        } else if (value instanceof Boolean) {
            builder.expectBody(key, equalTo(value));
        } else if (value instanceof Integer) {
            builder.expectBody(key, equalTo(value));
        }
    }

    public ResponseSpecificationBuilder expectBody(String path, Object matcher) {
        builder.expectBody(path, (Matcher<?>) matcher);
        return this;
    }

    public ResponseSpecification build() {
        return builder.build();
    }
}

