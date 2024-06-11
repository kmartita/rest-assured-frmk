package com.kmartita.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.generation.models.TestData;
import lombok.experimental.UtilityClass;

import static java.lang.String.*;

@UtilityClass
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.findAndRegisterModules().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    public static <Response> Response readJson(String json, Class<Response> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(format("Couldn't map json [%s] to class [%s]", json, clazz.getSimpleName()), e);
        }
    }

    public static <Response> Response readJson(String json, TypeReference<Response> typeReference) {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(format("Couldn't map json [%s] to type [%s]", json, typeReference.getType()), e);
        }
    }

    public static <Field extends Enum<Field> & HasName> ObjectNode generateJson(TestData<Field> model) {
        ObjectNode objectNode = MAPPER.createObjectNode();

        model.includedFields().forEach(field -> {
            if (model.get(field) instanceof TestData) {
                objectNode.set(field.getName(), generateJson((TestData<?>) model.get(field)));
            } else {
                objectNode.set(field.getName(), MAPPER.valueToTree(model.get(field)));
            }
        });

        return objectNode;
    }
}
