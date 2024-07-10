package com.kmartita;

import com.kmartita.server.ApiRequestExecutor;
import com.kmartita.tools.data.HasId;
import com.kmartita.tools.helpers.EntityService;
import com.kmartita.tools.data.DataService;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.responses.Team;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.List;

@Listeners({ AllureTestNg.class })
public class BaseTest {

    private static final Dotenv ENV = Dotenv.configure().ignoreIfMissing().load();
    private static final String USER_NAME = ENV.get("USER_NAME", System.getenv("USER_NAME") != null ? System.getenv("USER_NAME") : "USER_NAME");

    protected ApiRequestExecutor apiService;
    protected EntityService entityService;
    protected DataService<Team> team;

    @BeforeClass(alwaysRun = true)
    public void init() {
        apiService = new ApiRequestExecutor();
        entityService = new EntityService();
        team = new DataService<>(Entity.TEAM, entityService.getTeamByUserName(USER_NAME));
    }

    protected  <E extends HasId> void deleteEntities(List<E> entities, Entity entity) {
        if (!entities.isEmpty()) {
            entities.stream()
                    .map(E::getId)
                    .forEach(id -> apiService.delete(entity, id));
        }
    }
}
