package com.kmartita.tools.helpers;

import com.kmartita.server.ApiRequestBuilder;
import com.kmartita.tools.data.DataService;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.HasId;
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

    private <R> List<R> getEntities(Entity entity, Class<R> entityType) {
        List<R> entities = given()
                .spec(request.basePath(entity))
                .when()
                .get()
                .jsonPath()
                .getList(entity.getPlural(), entityType);

        return entities != null ? entities : Collections.emptyList();
    }

    private <E extends HasId, R> List<R> getEntities(Entity entity, DataService<E> data, Class<R> entityType) {
        List<R> entities = given()
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
                .orElseThrow(() -> new RuntimeException(format("The team with username " +
                                                               "'%s' does not exist", userName)));
    }

    public static void main(String[] args) {
        Team team = new EntityService().getTeamByUserName("Marta Kravchuk");
        System.out.println(team.getName());

        DataService<Team> dataService_team = new DataService<>(Entity.TEAM, team);
        List<Space> spaces = new EntityService().getSpaces(dataService_team);
        System.out.println(spaces.size());
        spaces.forEach(s -> System.out.println("Id: " + s.getId()));
    }
}
