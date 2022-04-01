package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * В данном классе созданы спецификации запроса, ответа и метод, позволяющий применять их
 */

public class Specifications {
    public static RequestSpecification requestSpecificationGet(){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setBasePath("api/users")
                .addQueryParam("page", "2")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification requestSpecificationPost(){
        return new RequestSpecBuilder()
                .setBaseUri("http://mcat-aqa2-01.yc.mvideo.ru:8180/")
                .setBasePath("api/logistic/attributes/view/query/listing")
                .setBody("{\"filters\":[],\"requiredFields\":[]}")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecification(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static void InstallSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
