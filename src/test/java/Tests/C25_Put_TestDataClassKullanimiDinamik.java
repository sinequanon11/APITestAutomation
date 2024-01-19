package Tests;

import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_Put_TestDataClassKullanimiDinamik extends BaseUrlJsonPlaceholder {

        @Test
        public void test01(){

    /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT request
        yolladigimizda donen response’in
        status kodunun 200,
        content type’inin “application/json; charset=utf-8”,
        Connection header degerinin “keep-alive”
        ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

        Request Body
        {
        "title": "Ahmet",
        "body": "Merhaba",
        "userId": 10,
        "id": 70
        }

        Expected Data :
        {
        "title": "Ahmet",
        "body": "Merhaba",
        "userId": 10,
        "id": 70
        }
     */

            // 1. End-point ve request body olustur

            specJsonPlaceholder.pathParams( "pp1", "posts", "pp2", "70" );

            JSONObject requestBody = TestDataJsonPlaceholder
                    .JsonBodyOlustur( 10, 70, "Ahmet", "Merhaba" );

            // 2. Expected data olustur

            JSONObject expectedData = TestDataJsonPlaceholder
                    .JsonBodyOlustur( 10, 70, "Ahmet", "Merhaba" );

            // 3. Request gonder ve donen response'i kaydet

            Response response = given()
                    .spec( specJsonPlaceholder )
                    .when()
                    .body( requestBody.toString() )
                    .contentType( ContentType.JSON )
                    .put("{pp1}/{pp2}");

            // 4. Assertion

            JsonPath responseJP = response.jsonPath();

            assertEquals( TestDataJsonPlaceholder.basariliSorguStatusCode, response.statusCode() );
            assertEquals( TestDataJsonPlaceholder.contentType, response.contentType() );
            assertEquals( TestDataJsonPlaceholder.headerConnection, response.header( "Connection") );
            assertEquals( expectedData.get( "id" ), responseJP.get("id") );
            assertEquals( expectedData.get( "userId" ), responseJP.get("userId") );
            assertEquals( expectedData.get( "title" ), responseJP.get("title") );
            assertEquals( expectedData.get( "body" ), responseJP.get("body") );
}

}
