package com.kmartita.tools.helpers.response;

import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.generation.models.TestData;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;

public class ResponseSpecHelper {

    public <Field extends Enum<Field> & HasName> ResponseSpecification specificationOnMatching(TestData<Field> data) {
        return new ResponseSpecificationBuilder()
                .withParsedFields(data)
                .expectBody("id", notNullValue())
                .build();
    }

    public static ResponseSpecification specificationOnCreating() {
        return new ResponseSpecBuilder()
                .expectBody("id", notNullValue())
                .build();
    }

    public static ResponseSpecification specificationOnDeleting() {
        return new ResponseSpecBuilder()
                .expectBody(emptyString())
                .build();
    }

    //TODO
    public static ResponseSpecification custom__(){
        return new ResponseSpecBuilder()
                .expectBody("spaces", not(empty()))
                .expectBody("spaces.id", everyItem(notNullValue()))
                .expectBody("spaces.name", everyItem(notNullValue()))
                .build();
    }
}
