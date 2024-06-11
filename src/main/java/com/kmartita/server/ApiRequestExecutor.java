package com.kmartita.server;

import com.kmartita.tools.data.DataService;
import com.kmartita.tools.data.HasId;
import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.generation.models.TestData;
import io.restassured.response.ValidatableResponse;

import static com.kmartita.tools.helpers.StatusCodeData.STATUS_CODE_200;
import static io.restassured.RestAssured.given;

public class ApiRequestExecutor {

    private final ApiRequestBuilder request;
    private ValidatableResponse validatableResponse;

    public ApiRequestExecutor() {
        request = new ApiRequestBuilder();
    }

    public <Response extends HasId> ResponseHandler get(DataService<Response> data, Entity entity) {
        validatableResponse = given()
                .spec(request.get(data, entity))
                .when()
                .get()
                .then()
                .statusCode(STATUS_CODE_200)
                .assertThat();
        return new ResponseHandler(validatableResponse);
    }

    public ResponseHandler getById(Entity entity, String id) {
        validatableResponse = given()
                .spec(request.getById(entity, id))
                .when()
                .get()
                .then()
                .statusCode(STATUS_CODE_200)
                .assertThat();
        return new ResponseHandler(validatableResponse);
    }

    public <Response extends HasId, Field extends Enum<Field> & HasName> ResponseHandler post(DataService<Response> data,
                                                                                              Entity entity,
                                                                                              TestData<Field> model) {
        validatableResponse = given()
                .spec(request.create(data, entity, model))
                .when()
                .post()
                .then()
                .statusCode(STATUS_CODE_200)
                .assertThat();
        return new ResponseHandler(validatableResponse);
    }

    public ResponseHandler delete(Entity entity, String id) {
        validatableResponse = given()
                .spec(request.getById(entity, id))
                .when()
                .delete()
                .then()
                .statusCode(STATUS_CODE_200)
                .assertThat();
        return new ResponseHandler(validatableResponse);
    }
}
