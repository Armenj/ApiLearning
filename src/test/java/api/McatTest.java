package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class McatTest {
    @Test
    public void mcatTest(){
        Specifications.InstallSpecification(Specifications.requestSpecificationPost(), Specifications.responseSpecification());
        Response response = given().log().uri()
                .post("")
                .then().log().body()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Map<String,?> attributes = jsonPath.get("content.attributes[0].find {attributeId = 'product_sap_code'}");
        /**
         * Выводим первый блок ответа в виде мапа
         *     "content": [
         *         {
         *             "productId": 20,
         *             "attributes": [
         *                 {
         *                     "attributeId": "product_sap_code",
         *                     "value": "400000065",
         *                     "details": null,
         *                     "valid": true
         *                 },
         */
        String singleAttributes = jsonPath.get("content.attributes[0].find {attributeId = 'product_sap_code'}.value");
        /**
         * Выводим конкретное значение - 400000065 из первого массива
         */
        List<String> allValue = jsonPath.get("content.attributes[0].findAll {it.attributeId = 'product_sap_code'}.value");
        /**
         * Выводим в список все значения поля value из первого массива
         */
        String allValueMax = jsonPath.get("content.attributes[0].max {it.attributeId}.value");
        /**
         * Выводим максимальное значение поля value
         */
//        String allValueMin = jsonPath.get("content.attributes[0].findAll {it.attributeId = 'product_sap_code'}.value");
        System.out.println(attributes);
        System.out.println(singleAttributes);
        System.out.println(allValue);
        System.out.println(allValueMax);


    }
}
