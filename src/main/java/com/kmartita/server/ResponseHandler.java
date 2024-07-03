package com.kmartita.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kmartita.tools.JsonUtil;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class ResponseHandler {

    private final Response response;

    public ResponseHandler(Response response) {
        this.response = response;
    }

    public ResponseHandler validate(ResponseSpecification responseSpecification) {
        this.response.then().spec(responseSpecification).assertThat();
        return this;
    }

    public <R> R extractAs(Class<R> clazz) {
        return JsonUtil.readJson(this.response.then().extract().body().asString(), clazz);
    }

    public <R> R extractAs(TypeReference<R> typeReference) {
        return JsonUtil.readJson(this.response.then().extract().body().asString(), typeReference);
    }
}
