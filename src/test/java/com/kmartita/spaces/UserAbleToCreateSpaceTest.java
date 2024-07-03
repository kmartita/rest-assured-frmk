package com.kmartita.spaces;

import com.kmartita.server.ResponseHandler;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.responses.Space;
import com.kmartita.tools.data.bodyschemas.spaces.SpaceFields;
import com.kmartita.tools.data.generation.models.TestData;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static com.kmartita.tools.Utils.getRequiredFields;
import static com.kmartita.tools.helpers.StatusCodeData.*;
import static com.kmartita.tools.helpers.response.ResponseSpecHelper.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class UserAbleToCreateSpaceTest extends BaseSpaceTest {

    private TestData<SpaceFields> requiredData;
    private TestData<SpaceFields> withoutFeaturesData;
    private TestData<SpaceFields> onlyNameData;

    @BeforeClass(alwaysRun = true)
    public void generateData() {
        Set<SpaceFields> requiredFields = getRequiredFields(SpaceFields.class);
        requiredData = TestData.preGenerate(requiredFields).build();

        withoutFeaturesData = TestData.preGenerate(requiredFields)
                .build()
                .removeFields(SpaceFields.FEATURES);

        onlyNameData = TestData.builder(SpaceFields.class)
                .setField(SpaceFields.NAME, SpaceFields.NAME.generate())
                .build();
    }

    @DataProvider(name = "positive")
    public Object[][] positiveSpaceDataProvider() {
        return new Object[][]{
                {requiredData, "schemas/space_required.json"},
                {withoutFeaturesData, "schemas/space_not_required_1.json"},
                {onlyNameData, "schemas/space_not_required_2.json"}
        };
    }

    @Test(dataProvider = "positive", groups = "Space", alwaysRun = true)
    public void userAbleToCreateSpaceByData(TestData<SpaceFields> data, String path) {
        ResponseSpecification specByRequirement = new ResponseSpecBuilder()
                .expectBody("statuses.size()", equalTo(2))
                .expectBody("color", nullValue())
                .expectBody("private", equalTo(false))
                .expectBody("avatar", nullValue())
                .expectBody("admin_can_manage", nullValue())
                .expectBody("archived", equalTo(false))
                .build();

        ResponseHandler response = apiService
                .post(team, Entity.SPACE, data)
                .validate(specOnSchemaValidating(path))
                .validate(specByRequirement)
                .validate(specOnCreating());

        String spaceId = response
                .extractAs(Space.class)
                .getId();

        ResponseSpecification specBySuccess = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .expectBody("id", equalTo(spaceId))
                .build();

        apiService.getById(Entity.SPACE, spaceId)
                .validate(specBySuccess);
    }
}
