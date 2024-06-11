package com.kmartita.tools.data.bodyschemas.spaces;

import com.kmartita.tools.data.generation.Generate;
import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.bodyschemas.Required;

import static java.lang.String.format;

public enum RemapDependencyFields implements HasName, Generate {

    @Required(generate = true)
    ENABLED("enabled") {
        @Override
        public Object generate() {
            return true;
        }
    };

    private final String name;

    RemapDependencyFields(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("Auto data generation is not supported for " +
                "[object=RemapDependencies, field=%s]", this));
    }
}
