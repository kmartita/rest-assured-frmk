package com.kmartita;

import com.kmartita.tools.Utils;

public class Demo {


    public static void main(String[] args) {
       /*Set<SpaceFields> requiredFields = UTILS.getRequiredFields(SpaceFields.class);
       GenericModel<SpaceFields> requiredModel = GenericModel.preGenerate(requiredFields).build();
       System.out.println(requiredModel);
       System.out.println(UTILS.generateJson(requiredModel));*/

        /*Set<SpaceFields> customFields = UTILS.getRequiredFields(SpaceFields.class);
        TestData<SpaceFields> customModel = TestData
                .preGenerate(customFields)
                .build()
                .removeFields(SpaceFields.FEATURES);
        System.out.println(customFields);
        System.out.println(generateJson(customModel));*/

        /*GenericModel<SpaceFields> customModel = GenericModel.builder(SpaceFields.class)
                .setField(SpaceFields.NAME, "Custom")
                .setField(SpaceFields.MULTIPLE_ASSIGNEES, false)
                .setField(SpaceFields.FEATURES, GenericModel.builder(FeatureFields.class)
                        .setField(FeatureFields.DUE_DATES, GenericModel.builder(DueDateFields.class)
                                .setField(DueDateFields.ENABLED, true)
                                .setField(DueDateFields.START_DATE, true)
                                .build())
                        .setField(FeatureFields.TIME_TRACKING, GenericModel.builder(TimeTrackingFields.class)
                                .setField(TimeTrackingFields.ENABLED.ENABLED, false)
                                .build())
                        .build())
                .build();

        System.out.println(customModel);
        System.out.println(UTILS.generateJson(customModel));*/

       /* ApiService apiService = new ApiService();
        Data team = new Fetches().getTeamByName(USER_NAME);
        List<ResponseSpace> spaces = new Fetches().getSpaces(team);
        List<String> id = spaces.stream().map(ResponseSpace::getId).collect(toList());
        System.out.println(id);*/
    }


}
