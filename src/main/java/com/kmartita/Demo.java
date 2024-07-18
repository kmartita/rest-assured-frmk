package com.kmartita;

import com.kmartita.tools.Utils;
import com.kmartita.tools.data.bodyschemas.spaces.DueDateFields;
import com.kmartita.tools.data.bodyschemas.spaces.FeatureFields;
import com.kmartita.tools.data.bodyschemas.spaces.SpaceFields;
import com.kmartita.tools.data.bodyschemas.spaces.TimeTrackingFields;
import com.kmartita.tools.data.generation.models.TestData;
import com.kmartita.tools.data.generation.models.TestDataBuilder;

import java.util.Set;

import static com.kmartita.tools.JsonUtil.generateJson;
import static com.kmartita.tools.Utils.getRequiredFields;

public class Demo {


    public static void main(String[] args) {
        Set<SpaceFields> requiredFields = getRequiredFields(SpaceFields.class);
        TestData<SpaceFields> requiredModel = TestData
                .preGenerate(requiredFields)
                .build();
        System.out.println(requiredModel);
        System.out.println(generateJson(requiredModel));

        Set<SpaceFields> customFields = getRequiredFields(SpaceFields.class);
        TestData<SpaceFields> customModel1 = TestData
                .preGenerate(customFields)
                .build()
                .removeFields(SpaceFields.FEATURES);
        System.out.println(customFields);
        System.out.println(generateJson(customModel1));

        TestData<SpaceFields> customModel2 = TestData.builder(SpaceFields.class)
                .setField(SpaceFields.NAME, "Custom")
                .setField(SpaceFields.MULTIPLE_ASSIGNEES, false)
                .setField(SpaceFields.FEATURES, TestData.builder(FeatureFields.class)
                        .setField(FeatureFields.DUE_DATES, TestData.builder(DueDateFields.class)
                                .setField(DueDateFields.ENABLED, true)
                                .setField(DueDateFields.START_DATE, true)
                                .build())
                        .setField(FeatureFields.TIME_TRACKING, TestData.builder(TimeTrackingFields.class)
                                .setField(TimeTrackingFields.ENABLED, false)
                                .build())
                        .build())
                .build();

        System.out.println(customModel2);
        System.out.println(generateJson(customModel2));

    }


}
