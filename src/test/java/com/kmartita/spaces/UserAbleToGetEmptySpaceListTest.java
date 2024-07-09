package com.kmartita.spaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.responses.Space;
import com.kmartita.tools.data.responses.Spaces;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.kmartita.tools.helpers.StatusCodeData.OK;
import static com.kmartita.tools.helpers.StatusCodeData.STATUS_CODE_200;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.*;

public class UserAbleToGetEmptySpaceListTest extends BaseSpaceTest {

    @Test
    public void userIsAbleToGetEmptyListOfSpacesForValidTeamId() {
        ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .expectBody("spaces", is(empty()))
                .build();

        Spaces responseWrapper = apiService
                .get(team, Entity.SPACE)
                .validate(specification)
                .extractAs(new TypeReference<>() {});

        List<Space> spaces = responseWrapper.getSpaces();
        Assert.assertTrue(spaces.isEmpty(), "List of spaces should be empty.");
    }
}
