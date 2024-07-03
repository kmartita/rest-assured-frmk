package com.kmartita.server;

import com.kmartita.tools.data.DataService;
import com.kmartita.tools.data.HasId;
import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.generation.models.TestData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiRequestExecutor {

    private final ApiRequestBuilder request;
    private Response response;

    public ApiRequestExecutor() {
        request = new ApiRequestBuilder();
    }

    public <R extends HasId> ResponseHandler get(DataService<R> data, Entity entity) {
        response = given()
                .spec(request.get(data, entity))
                .when()
                .get();
        return new ResponseHandler(response);
    }

    public ResponseHandler getById(Entity entity, String id) {
        response = given()
                .spec(request.getById(entity, id))
                .when()
                .get();
        return new ResponseHandler(response);
    }

    public <R extends HasId, Field extends Enum<Field> & HasName> ResponseHandler post(DataService<R> data,
                                                                                       Entity entity,
                                                                                       TestData<Field> model) {
        response = given()
                .spec(request.create(data, entity, model))
                .when()
                .post();
        return new ResponseHandler(response);
    }

    public ResponseHandler delete(Entity entity, String id) {
        response = given()
                .spec(request.getById(entity, id))
                .when()
                .delete();
        return new ResponseHandler(response);
    }
}
