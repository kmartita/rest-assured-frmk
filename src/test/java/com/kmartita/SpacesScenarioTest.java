package com.kmartita;

import com.kmartita.server.ResponseHandler;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.bodyschemas.spaces.SpaceFields;
import com.kmartita.tools.data.generation.models.TestData;
import com.kmartita.tools.data.responses.Space;
import io.qameta.allure.Step;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.*;

import static com.kmartita.tools.Utils.getRequiredFields;
import static com.kmartita.tools.data.bodyschemas.spaces.SpaceFields.*;
import static com.kmartita.tools.helpers.StatusCodeData.*;
import static com.kmartita.tools.helpers.StatusCodeData.BAD_REQUEST;
import static com.kmartita.tools.helpers.response.ResponseSpecHelper.*;
import static java.lang.String.format;
import static org.hamcrest.Matchers.*;

public class SpacesScenarioTest extends BaseTest {

    private final String ONE_SPACE_EXISTS = "One space exists";
    private final String POSITIVE_DATA = "Positive data";
    private final String NEGATIVE_DATA = "Negative data";
    private final String NEGATIVE_TYPE_DATA = "Negative type data";

    private static final TestData<SpaceFields> REQUIRED_DATA = TestData
            .preGenerate(getRequiredFields(SpaceFields.class))
            .build();

    private String spaceId;

    @Step("Delete all spaces")
    private void deleteSpaces() {
        deleteEntities(entityService.getSpaces(team), Entity.SPACE);
    }

    @BeforeClass(alwaysRun = true) @AfterMethod(alwaysRun = true)
    public void clean() {
        deleteSpaces();
    }

    @BeforeMethod(onlyForGroups = ONE_SPACE_EXISTS, alwaysRun = true)
    public void oneSpaceExists() {
        spaceId = apiService
                .post(team, Entity.SPACE, REQUIRED_DATA)
                .validate(specOnSchemaValidating("schemas/space_required.json"))
                .extractAs(Space.class)
                .getId();
    }

    @DataProvider(name = POSITIVE_DATA)
    public Object[][] positiveSpaceDataProvider() {
        TestData<SpaceFields> requiredData = TestData
                .preGenerate(getRequiredFields(SpaceFields.class))
                .build();
        TestData<SpaceFields> oneNameData = TestData
                .builder(SpaceFields.class)
                .setField(NAME, NAME.generate())
                .build();

        return new Object[][]{
                {requiredData, "schemas/space_required.json"},
                {requiredData.removeFields(FEATURES).edit(NAME, NAME.generate()), "schemas/space_not_required_1.json"},
                {oneNameData, "schemas/space_not_required_2.json"}
        };
    }

    @DataProvider(name = NEGATIVE_DATA)
    public Object[][] negativeSpaceDataProvider() {
        return new Object[][]{
                {REQUIRED_DATA.removeFields(NAME), "Space name invalid"},
                {REQUIRED_DATA, "Space with this name already exists"},
        };
    }

    @DataProvider(name = NEGATIVE_TYPE_DATA)
    public Object[][] negativeResponseSpaceDataProvider() {
        TestData<SpaceFields> testData = TestData
                .preGenerate(getRequiredFields(SpaceFields.class))
                .build()
                .removeFields(FEATURES);

        String text = "bad data";
        TestData<SpaceFields> invalidBoolean = testData.edit(MULTIPLE_ASSIGNEES, text);
        ResponseSpecification specByBooleanFailure = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_500)
                .expectStatusLine(INTERNET_SERVER_ERROR)
                .expectBody("err", equalTo(format("invalid input syntax for type boolean: \"%s\"", text)))
                .build();

        TestData<SpaceFields> invalidString = testData.edit(NAME, 0.8);
        ResponseSpecification specByStringFailure = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_400)
                .expectStatusLine(BAD_REQUEST)
                .expectBody("err", equalTo("Space name invalid"))
                .build();

        return new Object[][]{
                {invalidBoolean, specByBooleanFailure},
                {invalidString, specByStringFailure},
        };
    }

    @Test(priority = 1, alwaysRun = true)
    public void userIsAbleToGetEmptyListOfSpacesForTeamIfItHasNoSpaces() {
        ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .expectBody("spaces", is(empty()))
                .build();

        apiService
                .get(team, Entity.SPACE)
                .validate(specification);
    }

    @Test(priority = 1, groups = ONE_SPACE_EXISTS, alwaysRun = true)
    public void userIsAbleToGetListOfSpacesForValidTeamId() {
        ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .expectBody("spaces", not(empty()))
                .expectBody("spaces.id", everyItem(notNullValue()))
                .expectBody("spaces.name", everyItem(notNullValue()))
                .build();

        apiService
                .get(team, Entity.SPACE)
                .validate(specification);
    }

    @Test(priority = 1, groups = ONE_SPACE_EXISTS, alwaysRun = true)
    public void userIsAbleToGetSpecificSpace() {
         ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .expectBody("id", equalTo(spaceId))
                .build();

        apiService
                .getById(Entity.SPACE, spaceId)
                .validate(specification);
    }

    @Test(priority = 2, dataProvider = POSITIVE_DATA, alwaysRun = true)
    public void userIsAbleToCreateSpaceByData(TestData<SpaceFields> data, String path) {
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

    @Test(priority = 2, groups = ONE_SPACE_EXISTS, dataProvider = NEGATIVE_DATA, alwaysRun = true)
    public void userIsUnableToCreateSpaceByData(TestData<SpaceFields> data, String error) {
        ResponseSpecification specByFailure = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_400)
                .expectStatusLine(BAD_REQUEST)
                .expectBody("err", equalTo(error))
                .build();

        apiService
                .post(team, Entity.SPACE, data)
                .validate(specByFailure);
    }

    @Test(priority = 2, dataProvider = NEGATIVE_TYPE_DATA, alwaysRun = true)
    public void userIsUnableToCreateSpaceByInvalidResponseData(TestData<SpaceFields> data, ResponseSpecification specByFailure) {
        apiService
                .post(team, Entity.SPACE, data)
                .validate(specByFailure);
    }

    @Test(priority = 3, groups = ONE_SPACE_EXISTS, alwaysRun = true)
    public void userIsAbleToUpdateSpecificSpaceWithPartialValidData() {
        String name = apiService
                .getById(Entity.SPACE, spaceId)
                .extractAs(Space.class)
                .getName();

        String color = String.valueOf(COLOR.generate());
        TestData<SpaceFields> updatedData = TestData
                .builder(SpaceFields.class)
                .setField(COLOR, color)
                .setField(NAME, "Updated")
                .build();

        ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .expectBody("id", equalTo(spaceId))
                .expectBody("color", equalTo(color))
                .expectBody("name", allOf(not(equalTo(name)), equalTo("Updated")))
                .build();

        apiService
                .put(Entity.SPACE, spaceId, updatedData)
                .validate(specification);
    }

    @Test(priority = 3, groups = ONE_SPACE_EXISTS, alwaysRun = true)
    public void userIsAbleToUpdateSpecificSpaceWithPossibilityOfSharingForOnlyOnePerson() {
        TestData<SpaceFields> updatedData = TestData
                .builder(SpaceFields.class)
                .setField(PRIVATE, true)
                .build();

        ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .expectBody("id", equalTo(spaceId))
                .expectBody("members.size()", equalTo(1))
                .expectBody("name", nullValue())
                .build();

        apiService
                .put(Entity.SPACE, spaceId, updatedData)
                .validate(specification);
    }

    @Test(priority = 4, groups = ONE_SPACE_EXISTS, alwaysRun = true)
    public void userIsAbleToDeleteSpecificSpace() {
        ResponseSpecification specification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_200)
                .expectStatusLine(OK)
                .build();

        apiService
                .delete(Entity.SPACE, spaceId)
                .validate(specification)
                .validate(specOnDeleting());
    }
}
