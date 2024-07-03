package com.kmartita.spaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.bodyschemas.spaces.SpaceFields;
import com.kmartita.tools.data.generation.models.TestData;
import com.kmartita.tools.data.responses.Space;
import com.kmartita.tools.data.responses.Spaces;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kmartita.tools.helpers.StatusCodeData.OK;
import static com.kmartita.tools.helpers.StatusCodeData.STATUS_CODE_200;
import static com.kmartita.tools.helpers.response.ResponseSpecHelper.specOnSchemaValidating;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.*;

public class TestSecondGetAll extends BaseSpaceTest {

    @BeforeClass(alwaysRun = true)
    public void beforeActions() {
        TestData<SpaceFields> testData = generateSpaceDataWithRequiredFields();
        apiService.post(team, Entity.SPACE, testData)
                .validate(specOnSchemaValidating("schemas/space_required.json"));
    }

    @Test
    public void test_() {
        ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .expectBody("spaces", not(empty()))
                .expectBody("spaces.id", everyItem(notNullValue()))
                .expectBody("spaces.name", everyItem(notNullValue()))
                .build();

        Spaces responseWrapper = apiService
                .get(team, Entity.SPACE)
                .validate(specification)
                .extractAs(new TypeReference<>() {});

        List<Space> spaces = responseWrapper.getSpaces();
        Assert.assertFalse(spaces.isEmpty(), "List of spaces should not be empty.");
    }
}
