package com.kmartita.spaces;

import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.bodyschemas.spaces.SpaceFields;
import com.kmartita.tools.data.generation.models.TestData;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.kmartita.tools.helpers.StatusCodeData.*;
import static java.lang.String.*;
import static org.hamcrest.Matchers.equalTo;

public class UserUnableToCreateSpaceWithInvalidResponseTest extends BaseSpaceTest {

    private TestData<SpaceFields> invalidBoolean;
    private TestData<SpaceFields> invalidString;

    private ResponseSpecification specByBooleanFailure;
    private ResponseSpecification specByStringFailure;

    @BeforeClass(alwaysRun = true)
    public void beforeActions() {
        TestData<SpaceFields> testData = generateSpaceDataExcludingFeatures();
        String text = "bad data";

        invalidBoolean = testData.edit(SpaceFields.MULTIPLE_ASSIGNEES, text);
        invalidString = testData.edit(SpaceFields.NAME, 0.8);

        specByBooleanFailure = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_500)
                .expectStatusLine(INTERNET_SERVER_ERROR)
                .expectBody("err", equalTo(format("invalid input syntax for type boolean: \"%s\"", text)))
                .build();

        specByStringFailure = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_400)
                .expectStatusLine(BAD_REQUEST)
                .expectBody("err", equalTo("Space name invalid"))
                .build();
    }

    @DataProvider(name = "negative")
    public Object[][] negativeSpaceDataProvider() {
        return new Object[][]{
                {invalidBoolean, specByBooleanFailure},
                {invalidString, specByStringFailure},
        };
    }

    @Test(dataProvider = "negative", alwaysRun = true)
    public void userUnableToCreateSpaceByInvalidResponseData(TestData<SpaceFields> data, ResponseSpecification specByFailure) {
        apiService
                .post(team, Entity.SPACE, data)
                .validate(specByFailure);
    }
}
