package com.kmartita.spaces;

import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.bodyschemas.spaces.SpaceFields;
import com.kmartita.tools.data.generation.models.TestData;
import com.kmartita.tools.data.responses.Space;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kmartita.tools.helpers.StatusCodeData.OK;
import static com.kmartita.tools.helpers.StatusCodeData.STATUS_CODE_200;
import static com.kmartita.tools.helpers.response.ResponseSpecHelper.specOnSchemaValidating;
import static org.hamcrest.Matchers.*;

public class UserAbleToGetSpaceByIdTest extends BaseSpaceTest {

    private String spaceId;

    @BeforeClass(alwaysRun = true)
    public void beforeActions() {
        TestData<SpaceFields> testData = generateSpaceDataWithRequiredFields();
        spaceId = apiService.post(team, Entity.SPACE, testData)
                .validate(specOnSchemaValidating("schemas/space_required.json"))
                .extractAs(Space.class)
                .getId();
    }

    @Test
    public void userIsAbleToGetSpecificSpace() {
        ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .expectBody("id", equalTo(spaceId))
                .build();

        apiService.getById(Entity.SPACE, spaceId)
                .validate(specification);
    }
}
