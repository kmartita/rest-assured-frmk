package com.kmartita;

import com.kmartita.server.ApiRequestExecutor;
import com.kmartita.tools.helpers.EntityService;
import com.kmartita.tools.data.DataService;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.responses.Space;
import com.kmartita.tools.data.responses.Team;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Listeners;

import java.util.List;

import static com.kmartita.tools.data.DataMapping.USER_NAME;

@Listeners({ AllureTestNg.class })
public class BaseTest {

    protected ApiRequestExecutor apiService;
    protected EntityService entityService;
    protected DataService<Team> team;

    protected final String DELETE_SPACES_GROUP = "Space";

    @BeforeClass(alwaysRun = true)
    public void init() {
        apiService = new ApiRequestExecutor();
        entityService = new EntityService();
        team = new DataService<>(Entity.TEAM, entityService.getTeamByUserName(USER_NAME));
    }

    @BeforeGroups(value = DELETE_SPACES_GROUP, alwaysRun = true)
    public void beforeSpaces() {
        deleteSpacesFromTeam(team);
    }

    @Step("Delete all spaces from team: {team}")
    private void deleteSpacesFromTeam(DataService<Team> team) {
        List<Space> spaces = entityService.getSpaces(team);

        if (!spaces.isEmpty()) {
            spaces.stream()
                    .map(Space::getId)
                    .forEach(id -> apiService.delete(Entity.SPACE, id));
        }
    }
}
