package com.kmartita.tools.data.bodyschemas.spaces;

import com.kmartita.tools.data.generation.Generate;
import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.bodyschemas.Required;

import static java.lang.String.format;

public enum DueDateFields implements HasName, Generate {

    @Required(generate = true)
    ENABLED("enabled") {
        @Override
        public Object generate() {
            return true;
        }
    },

    @Required(generate = true)
    START_DATE("start_date") {
        @Override
        public Object generate() {
            return false;
        }
    },

    @Required(generate = true)
    REMAP_DUE_DATES("remap_due_dates") {
        @Override
        public Object generate() {
            return true;
        }
    },

    @Required(generate = true)
    REMAP_CLOSED_DUE_DATE("remap_closed_due_date") {
        @Override
        public Object generate() {
            return false;
        }
    };

    private final String name;

    DueDateFields(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("Auto data generation is not supported for " +
                "[object=DueDates, field=%s]", this));
    }
}
