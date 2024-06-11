package com.kmartita.spaces;

import com.kmartita.BaseTest;
import com.kmartita.server.ResponseHandler;
import com.kmartita.tools.helpers.response.ResponseSpecHelper;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.responses.Space;
import com.kmartita.tools.data.bodyschemas.spaces.SpaceFields;
import com.kmartita.tools.data.generation.models.TestData;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static com.kmartita.tools.Utils.getRequiredFields;
import static com.kmartita.tools.helpers.StatusCodeData.OK;
import static org.hamcrest.Matchers.equalTo;

public class CreateNewSpaceWithRequiredDataTest extends BaseTest {

    private TestData<SpaceFields> requiredData;

    @BeforeClass(alwaysRun = true)
    public void generatePostData() {
        Set<SpaceFields> requiredFields = getRequiredFields(SpaceFields.class);
        requiredData = TestData.preGenerate(requiredFields).build();
    }

    @Test(groups = DELETE_SPACES_GROUP, alwaysRun = true)
    public void userCanCreateSpaceTest() {
        ResponseSpecification specByData = new ResponseSpecHelper()
                .specificationOnMatching(requiredData);

        ResponseSpecification specByRequirement = new ResponseSpecBuilder()
                .expectBody("statuses.size()", equalTo(2))
                .expectBody("features.sprints", equalTo(Collections.singletonMap("enabled", false)))
                .build();

        ResponseHandler response = apiService
                .post(team, Entity.SPACE, requiredData)
                .validate(specByData)
                .validate(specByRequirement);

        String spaceId = response
                .extractAs(Space.class)
                .getId();

        ResponseSpecification specBySuccess = new ResponseSpecBuilder()
                .expectStatusLine(OK)
                .expectBody("id", equalTo(spaceId))
                .build();

        apiService.getById(Entity.SPACE, spaceId)
                .validate(specBySuccess);
    }
}
