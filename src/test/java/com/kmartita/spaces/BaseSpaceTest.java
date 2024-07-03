package com.kmartita.spaces;

import com.kmartita.BaseTest;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.bodyschemas.spaces.SpaceFields;
import com.kmartita.tools.data.generation.models.TestData;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Set;

import static com.kmartita.tools.Utils.getRequiredFields;

public class BaseSpaceTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClean() {
        deleteSpaces();
    }

    @AfterClass(alwaysRun = true)
    public void afterClean() {
        deleteSpaces();
    }

    @Step("Delete all spaces")
    private void deleteSpaces() {
        deleteEntities(entityService.getSpaces(team), Entity.SPACE);
    }

    public TestData<SpaceFields> generateSpaceDataWithRequiredFields(){
        Set<SpaceFields> requiredFields = getRequiredFields(SpaceFields.class);
        return TestData.preGenerate(requiredFields).build();
    }

    public TestData<SpaceFields> generateSpaceDataExcludingFeatures(){
        return generateSpaceDataWithRequiredFields().removeFields(SpaceFields.FEATURES);
    }

    public TestData<SpaceFields> generateSpaceDataExcludingName(){
        return generateSpaceDataWithRequiredFields().removeFields(SpaceFields.NAME);
    }
}
