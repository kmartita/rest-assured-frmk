package com.kmartita.server;

import com.kmartita.tools.data.DataService;
import com.kmartita.tools.data.HasId;
import com.kmartita.tools.data.generation.HasName;
import com.kmartita.tools.data.Entity;
import com.kmartita.tools.data.generation.models.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import io.github.cdimascio.dotenv.Dotenv;

import static com.kmartita.tools.JsonUtil.generateJson;
import static java.lang.String.*;

public class ApiRequestBuilder {

    private static final Dotenv ENV = Dotenv.configure().ignoreIfMissing().load();

    private static final String TOKEN = (ENV.get("TOKEN") != null) ? ENV.get("TOKEN") : System.getenv("TOKEN");
    private static final String BASE_URL = ENV.get("BASE_URL", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "BASE_URL");

    private RequestSpecification baseRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader(HttpHeaders.AUTHORIZATION, TOKEN)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public RequestSpecification basePath(Entity entity){
        return baseRequest()
                .basePath(entity.getSingular());
    }

    public <Response extends HasId> RequestSpecification basePath(DataService<Response> data, Entity entity){
        return baseRequest()
                .basePath(format("%s/%s/%s", data.getEntity().getSingular(), data.getResponse().getId(), entity.getSingular()));
    }

    public RequestSpecification basePath(Entity entity, String id){
        return baseRequest()
                .basePath(format("%s/%s", entity.getSingular(), id));
    }

    public <Response extends HasId> RequestSpecification get(DataService<Response> data, Entity entity) {
        return basePath(data, entity);
    }

    public RequestSpecification getById(Entity entity, String id) {
        return basePath(entity, id);
    }

    public <Response extends HasId, Field extends Enum<Field> & HasName> RequestSpecification create(DataService<Response> data,
                                                                                                     Entity entity,
                                                                                                     TestData<Field> model) {
        return get(data, entity)
                .body(generateJson(model).toString());
    }

    public <Response extends HasId, Field extends Enum<Field> & HasName> RequestSpecification update(Entity entity,
                                                                                                     String id,
                                                                                                     TestData<Field> model) {
        return getById(entity, id)
                .body(generateJson(model).toString());
    }
}
