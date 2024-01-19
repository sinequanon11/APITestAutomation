package Tests;

import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.Reporter;
import pojos.PojoJsonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C31_Put_PojoClass extends BaseUrlJsonPlaceholder {

    /*

            https://jsonplaceholder.typicode.com/posts/70 url'ine
            asagidaki body’e sahip bir PUT
            request yolladigimizda donen response’in response body’sinin
            asagida verilen ile ayni oldugunu test ediniz

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

    @Test
    public void test01(){

        // 1. End-point ve request body olustur

        specJsonPlaceholder.pathParams( "pp1", "posts", "pp2", "70" );

        PojoJsonPlaceholder requestBodyPojo =
                new PojoJsonPlaceholder("Ahmet", "Merhaba", 10, 70);

        // 2. Expected data olustur

        PojoJsonPlaceholder expectedDataPojo =
                new PojoJsonPlaceholder("Ahmet", "Merhaba", 10, 70);

        // 3. Request gonder ve donen response'i kaydet

        Response response = given().spec( specJsonPlaceholder ).contentType( ContentType.JSON )
                .when().body( requestBodyPojo )
                .put("{pp1}/{pp2}");

        PojoJsonPlaceholder responsePojo = response.as( PojoJsonPlaceholder.class);

        // 4. Assertion  --    expected data (Pojo) <===> (Pojo)

        assertEquals( TestDataJsonPlaceholder.basariliSorguStatusCode, response.statusCode() );
        assertEquals( TestDataJsonPlaceholder.contentType, response.contentType() );
        assertEquals( TestDataJsonPlaceholder.headerConnection, response.header( "Connection" ) );

        assertEquals( expectedDataPojo.getTitle(), responsePojo.getTitle() );
        assertEquals( expectedDataPojo.getBody(), responsePojo.getBody() );
        assertEquals( expectedDataPojo.getUserId(), responsePojo.getUserId() );
        assertEquals( expectedDataPojo.getId(), responsePojo.getId() );
    }

}
