package com.kmartita.tools.data.bodyschemas.spaces;

import com.kmartita.tools.data.generation.Generate;
import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.bodyschemas.Required;
import com.kmartita.tools.data.generation.models.TestData;

import java.util.Set;

import static com.kmartita.tools.Utils.getRequiredFields;
import static java.lang.String.format;

public enum FeatureFields implements HasName, Generate {

    @Required(generate = true)
    DUE_DATES("due_dates") {
        @Override
        public Object generate() {
            Set<DueDateFields> fields = getRequiredFields(DueDateFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    },

    @Required(generate = true)
    TIME_TRACKING("time_tracking") {
        @Override
        public Object generate() {
            Set<TimeTrackingFields> fields = getRequiredFields(TimeTrackingFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    },

    @Required(generate = true)
    TAGS("tags") {
        @Override
        public Object generate() {
            Set<TagFields> fields = getRequiredFields(TagFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    },

    @Required(generate = true)
    TIME_ESTIMATES("time_estimates") {
        @Override
        public Object generate() {
            Set<TimeEstimateFields> fields = getRequiredFields(TimeEstimateFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    },

    @Required(generate = true)
    CHECKLISTS("checklists") {
        @Override
        public Object generate() {
            Set<ChecklistFields> fields = getRequiredFields(ChecklistFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    },

    @Required(generate = true)
    CUSTOM_FIELDS("custom_fields") {
        @Override
        public Object generate() {
            Set<CustomFieldFields> fields = getRequiredFields(CustomFieldFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    },

    @Required(generate = true)
    REMAP_DEPENDENCIES("remap_dependencies") {
        @Override
        public Object generate() {
            Set<RemapDependencyFields> fields = getRequiredFields(RemapDependencyFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    },

    @Required(generate = true)
    DEPENDENCY_WARNING("dependency_warning") {
        @Override
        public Object generate() {
            Set<DependencyWarningFields> fields = getRequiredFields(DependencyWarningFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    },

    @Required(generate = true)
    PORTFOLIOS("portfolios") {
        @Override
        public Object generate() {
            Set<DependencyWarningFields> fields = getRequiredFields(DependencyWarningFields.class);
            return TestData.preGenerate(fields)
                    .build();
        }
    };

    private final String name;

    FeatureFields(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("Auto data generation is not supported for " +
                "[object=Features, field=%s]", this));
    }
}
