package Tests;

import TestDatalari.TestDataHerokuapp;
import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlHerokuapp;
import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C28_Put_DeSerialization  extends BaseUrlJsonPlaceholder {

        @Test
        public void test01(){

    /*

        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
        request yolladigimizda donen response’in response body’sinin asagida verilen ile ayni
        oldugunu test ediniz

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

            specJsonPlaceholder.pathParams( "pp1", "posts", "pp2", 70 );

                // Request body MAP olarak oluşturacağız

            Map<String, Object> requestBodyMap = TestDataJsonPlaceholder.bodyOlusturMap();

            // 2. Expected data olustur

            Map<String , Object> expectedData = TestDataJsonPlaceholder.bodyOlusturMap();

            // 3. Request gonder ve donen response'i kaydet

            Response response  = given().spec( specJsonPlaceholder ).contentType( ContentType.JSON )
                    .when().body( requestBodyMap )
                    .put("{pp1}/{pp2}");

            // 4. Assertion  // MAP olarak yapacagiz

            // Assertion yapabilmek için response'ı Map'a çevirmemiz lazım (De-Serialization)

            Map<String,Object> responseMap = response.as( HashMap.class);

            // expectedData (Map) --- responseMap (Map)

            Assert.assertEquals( expectedData.get( "title" ), responseMap.get( "title" ) );
            Assert.assertEquals( expectedData.get( "body" ), responseMap.get( "body" ) );
            Assert.assertEquals( expectedData.get( "id" ), responseMap.get( "id" ) );
            Assert.assertEquals( expectedData.get( "userId" ), responseMap.get( "userId" ) );
}

}