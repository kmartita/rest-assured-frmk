package com.kmartita.tools.helpers;

import com.kmartita.server.ApiRequestBuilder;
import com.kmartita.tools.data.DataService;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.responses.Space;
import com.kmartita.tools.data.responses.Team;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.String.*;

public class EntityService {

    private final ApiRequestBuilder request;

    public EntityService() {
        request = new ApiRequestBuilder();
    }

    private <Response> List<Response> getEntities(Entity entity, Class<Response> entityType) {
        List<Response> entities = given()
                .spec(request.basePath(entity))
                .when()
                .get()
                .jsonPath()
                .getList(entity.getPlural(), entityType);

        return entities != null ? entities : Collections.emptyList();
    }

    private <Response> List<Response> getEntities(Entity entity, DataService<Team> data, Class<Response> entityType) {
        List<Response> entities = given()
                .spec(request.get(data, entity))
                .when()
                .get()
                .jsonPath()
                .getList(entity.getPlural(), entityType);

        return entities != null ? entities : Collections.emptyList();
    }

    public List<Team> getTeams() {
        return getEntities(Entity.TEAM, Team.class);
    }

    public List<Space> getSpaces(DataService<Team> data) {
        return getEntities(Entity.SPACE, data, Space.class);
    }

    public Team getTeamByUserName(String userName) {
        return getTeams()
                .stream()
                .flatMap(team -> team.getMembers().stream()
                        .filter(member -> member.getUser().getUsername().equals(userName))
                        .map(member -> team))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(format("The team with username '%s' does not exist", userName)));
    }
}
