package com.kmartita.spaces;

import com.kmartita.BaseTest;
import com.kmartita.tools.data.Entity;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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
}
