package com.kmartita.spaces;

import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.bodyschemas.spaces.SpaceFields;
import com.kmartita.tools.data.generation.models.TestData;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.kmartita.tools.helpers.StatusCodeData.BAD_REQUEST;
import static com.kmartita.tools.helpers.StatusCodeData.STATUS_CODE_400;
import static com.kmartita.tools.helpers.response.ResponseSpecHelper.specOnSchemaValidating;
import static org.hamcrest.Matchers.*;

public class UserUnableToCreateSpaceTest extends BaseSpaceTest {

    private TestData<SpaceFields> testData;
    private TestData<SpaceFields> withoutNameData;

    @BeforeClass(alwaysRun = true)
    public void beforeActions() {
        testData = generateSpaceDataWithRequiredFields();
        withoutNameData = generateSpaceDataExcludingName();

        apiService.post(team, Entity.SPACE, testData)
                .validate(specOnSchemaValidating("schemas/space_required.json"));
    }

    @DataProvider(name = "negative")
    public Object[][] negativeSpaceDataProvider() {
        return new Object[][]{
                {withoutNameData, "Space name invalid"},
                {testData, "Space with this name already exists"},
        };
    }

    @Test(dataProvider = "negative", alwaysRun = true)
    public void userUnableToCreateSpaceByNegativeData(TestData<SpaceFields> data, String error) {
        ResponseSpecification specByFailure = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_CODE_400)
                .expectStatusLine(BAD_REQUEST)
                .expectBody("err", equalTo(error))
                .build();

        apiService
                .post(team, Entity.SPACE, data)
                .validate(specByFailure);
    }

}
