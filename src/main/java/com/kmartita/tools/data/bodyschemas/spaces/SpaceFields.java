package com.kmartita.tools.data.bodyschemas.spaces;

import com.github.javafaker.Faker;
import com.kmartita.tools.data.generation.Generate;
import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.bodyschemas.Required;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.generation.models.TestData;
import org.apache.commons.lang3.StringUtils;


import java.util.Locale;
import java.util.Set;

import static com.kmartita.tools.Utils.getRequiredFields;
import static java.lang.String.format;

public enum SpaceFields implements HasName, Generate {

    @Required(generate = true)
    NAME("name") {
        @Override
        public Object generate() {
            return FAKER.name().firstName().replace("'", StringUtils.EMPTY);
        }
    },

    @Required(generate = true)
    MULTIPLE_ASSIGNEES("multiple_assignees") {
        @Override
        public Object generate() {
            return true;
        }
    },

    @Required(generate = true)
    FEATURES("features") {
        @Override
        public Object generate() {
            Set<FeatureFields> fields = getRequiredFields(FeatureFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    },

    COLOR("color") {
        @Override
        public Object generate() {
            return "#53c654";
        }
    },

    PRIVATE("private") {
        @Override
        public Object generate() {
            return false;
        }
    };


    private final String name;

    SpaceFields(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private static final Faker FAKER = new Faker(Locale.US);

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("Auto data generation is not supported for " +
                "[entity=%s, field=%s]", Entity.SPACE.getSingular(), this));
    }
}
