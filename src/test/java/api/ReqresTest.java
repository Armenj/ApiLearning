package api;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqresTest {

    @Test
    public void SimpleFieldCompare(){
        Specifications.InstallSpecification(Specifications.requestSpecificationGet(), Specifications.responseSpecification());
          given().log().uri()
                  .when()
                  .get("")
                  .then().body("page", equalTo(2)).log().body(); // простая проверка соответствия значения поля ожидаемому значению. Get-метод
    }

    @Test
    public void SimpleFieldCompare1(){
        Specifications.InstallSpecification(Specifications.requestSpecificationGet(), Specifications.responseSpecification());
        given().log().uri()
                .when()
                .get("")
                .then()
                .body("page", equalTo(2))
                .body("data.email[0]", equalTo("michael.lawson@reqres.in")) // проверка соответствия значения поля массива ожидаемому значению. Get-метод
                .log().body();
    }

    @Test
    public void getAllData(){
        Specifications.InstallSpecification(Specifications.requestSpecificationGet(), Specifications.responseSpecification());
        Response response = given()
                .get("")
                .then().extract().response();
        String jsonPath = response.asString(); // извлекаем тело ответа в json для дальнейшей работы с ним
        System.out.println(jsonPath);
    }

    @Test
    public void getCookie(){
        Specifications.InstallSpecification(Specifications.requestSpecificationGet(), Specifications.responseSpecification());
        Response response = given()
                .get("")
                .then().extract().response();
        Map<String, String> Cookies = response.getCookies(); // извлекаем все куки
        System.out.println(Cookies);
    }

    @Test
    public void getHeaders(){
        Specifications.InstallSpecification(Specifications.requestSpecificationGet(), Specifications.responseSpecification());
        Response response = given().log().uri()
                .get("")
                .then().extract().response();
        Headers headers = response.getHeaders(); // извлекаем все заголовки
        String contentType = response.contentType(); // извлекаем конкретный заголовок
        System.out.println(headers);
        System.out.println(contentType);
    }

    @Test
    public void getMapOfElements(){
        Specifications.InstallSpecification(Specifications.requestSpecificationGet(), Specifications.responseSpecification());
        Response response = given().log().uri()
                .get("");
        Map<String, ?> someObject = response.path("data.find {it.email = 'michael.lawson@reqres.in'}"); // Пример получения объекта по определенному ключу.
        // Извлекаем из массива data конкретное поле email, которое соответствует значению michael.lawson@reqres.in
        System.out.println("someObject --> " + someObject); // выведутся все параметры относящиеся к email равному michael.lawson@reqres.in
    }

    @Test
    public void getSingleElements(){
        Specifications.InstallSpecification(Specifications.requestSpecificationGet(), Specifications.responseSpecification());
        Response response = given().log().uri()
                .get("");
        String id = response.path("data.find {it.email = 'michael.lawson@reqres.in'}.id").toString(); // извлекаем из массива data значение id
        // относящееся к объекту с полем email равным michael.lawson@reqres.in
        System.out.println("id --> " + id);
    }

    @Test
    public void getAllElements(){
        Specifications.InstallSpecification(Specifications.requestSpecificationGet(), Specifications.responseSpecification());
        Response response = given().log().uri()
                .get("");
        List<String> nameOfHeroes = response.path("data.findAll {it.email}.first_name"); // извлекли из json-ответа только имена персонажей
        System.out.println("nameOfHeroes --> " + nameOfHeroes);
    }


}
