package com.kmartita;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kmartita.server.ApiRequestExecutor;
import com.kmartita.tools.helpers.EntityService;
import com.kmartita.tools.data.DataService;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.responses.Space;
import com.kmartita.tools.data.responses.Spaces;
import com.kmartita.tools.data.responses.Team;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kmartita.tools.data.DataMapping.USER_NAME;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.*;

public class TestSecondGetAll {

    private ApiRequestExecutor apiService;
    private EntityService entityService;

    private DataService<Team> team;

    @BeforeClass(alwaysRun = true)
    public void beforeActions() {
        apiService = new ApiRequestExecutor();
        entityService = new EntityService();

        team = new DataService<>(Entity.TEAM, entityService.getTeamByUserName(USER_NAME));
    }

    @Test
    public void test_() {
        ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("spaces", not(empty()))
                .expectBody("spaces.id", everyItem(notNullValue()))
                .expectBody("spaces.name", everyItem(notNullValue()))
                .build();

        Spaces responseWrapper = apiService
                .get(team, Entity.SPACE)
                .validate(specification)
                .extractAs(new TypeReference<>() {});



        //TODO
        List<Space> spaces = responseWrapper.getSpaces();

        Assert.assertFalse(spaces.isEmpty(), "List of spaces should not be empty");

        for (Space space : spaces) {
            Assert.assertNotNull(space.getId(), "Space ID should not be null");
            Assert.assertNotNull(space.getName(), "Space name should not be null");
        }

    }
}
