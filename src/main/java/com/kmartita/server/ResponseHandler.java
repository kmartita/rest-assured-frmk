package com.kmartita.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kmartita.tools.JsonUtil;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;

public class ResponseHandler {

    private final ValidatableResponse validatableResponse;

    public ResponseHandler(ValidatableResponse validatableResponse) {
        this.validatableResponse = validatableResponse;
    }

    public ResponseHandler validate(ResponseSpecification responseSpecification) {
        this.validatableResponse.spec(responseSpecification);
        return this;
    }

    public <Response> Response extractAs(Class<Response> clazz) {
        return JsonUtil.readJson(this.validatableResponse.extract().body().asString(), clazz);
    }

    public <Response> Response extractAs(TypeReference<Response> typeReference) {
        return JsonUtil.readJson(this.validatableResponse.extract().body().asString(), typeReference);
    }
}
