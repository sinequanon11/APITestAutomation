package Tests;

import TestDatalari.TestDataDummyExample;
import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlDummyExample;
import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C29_Get_DeSerialization extends BaseUrlDummyExample {

        @Test
        public void test01(){

    /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un
        status code’unun 200,
        content Type’inin application/json
        ve body’sinin asagidaki gibi oldugunu test edin.

        Response Body
        {
        "status": "success",
        "data": {
            "id": 3,
            "employee_name": "Ashton Cox",
            "employee_salary": 86000,
            "employee_age": 66,
            "profile_image": ""
        },
        "message": "Successfully! Record has been fetched."
        }

     */
            // 1. End-point ve request body olustur

            specDummyExample.pathParams( "pp1", "employee", "pp2", "3" );

            // 2. Expected data olustur

            Map<String, Object> expectedData = TestDataDummyExample.mapBodyOlustur();

            // 3. Request gonder ve donen response'i kaydet

            Response response = given().spec( specDummyExample )
                    .when()
                    .get("{pp1}/{pp2}");

            Map<String,Object> responseMap = response.as( HashMap.class );

            // 4. Assertion  // MAP olarak yapacagiz

            // Assertion yapabilmek için response'ı Map'a çevirmemiz lazım (De-Serialization)
            // expectedData (Map) --- responseMap (Map)

            assertEquals( TestDataDummyExample.basariliSorguStatusCode, response.statusCode() );

            assertEquals( TestDataDummyExample.contentType, response.contentType() );

            assertEquals( expectedData.get( "message" ), responseMap.get("message") );

            assertEquals( expectedData.get( "status" ), responseMap.get("status") );

            assertEquals(((Map) expectedData.get("data")).get( "profile_image" ),
                    ((Map)responseMap.get( "data" )).get( "profile_image" ));
            assertEquals(((Map) expectedData.get("data")).get( "employee_name" ),
                    ((Map)responseMap.get( "data" )).get( "employee_name" ));
            assertEquals(((Map) expectedData.get("data")).get( "employee_salary" ),
                    ((Map)responseMap.get( "data" )).get( "employee_salary" ));
            assertEquals(((Map) expectedData.get("data")).get( "employee_age" ),
                    ((Map)responseMap.get( "data" )).get( "employee_age" ));
            assertEquals(((Map) expectedData.get("data")).get( "id" ),
                    ((Map)responseMap.get( "data" )).get( "id" ));

}

}