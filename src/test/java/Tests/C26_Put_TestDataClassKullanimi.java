package Tests;

import TestDatalari.TestDataDummyExample;
import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlDummyExample;
import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_Put_TestDataClassKullanimi  extends BaseUrlDummyExample {

        @Test
        public void test01(){

    /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde
        donen response’un status code’unun 200, content Type’inin application/json ve body’sinin
        asagidaki gibi oldugunu test edin.

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

            JSONObject expectedData = TestDataDummyExample.jsonResponseBodyOlustur(
                    3, "Ashton Cox", 86000, 66, ""  );

            // 3. Request gonder ve donen response'i kaydet

            Response response = given().spec( specDummyExample )
                    .when()
                    .get("{pp1}/{pp2}");

            // 4. Assertion

            JsonPath responseJP = response.jsonPath();

            assertEquals( TestDataDummyExample.basariliSorguStatusCode, response.statusCode() );
            assertEquals( TestDataDummyExample.contentType, response.contentType() );
            assertEquals( expectedData.getJSONObject( "data" ).get( "profile_image" ),
                    responseJP.get( "data.profile_image" ));
            assertEquals( expectedData.getJSONObject( "data" ).get( "employee_name" ),
                    responseJP.get( "data.employee_name" ));
            assertEquals( expectedData.getJSONObject( "data" ).get( "employee_salary" ),
                    responseJP.get( "data.employee_salary" ));
            assertEquals( expectedData.getJSONObject( "data" ).get( "id" ),
                    responseJP.get( "data.id" ));
            assertEquals( expectedData.getJSONObject( "data" ).get( "employee_age" ),
                    responseJP.get( "data.employee_age" ));
            assertEquals( expectedData.get( "message" ), responseJP.get("message") );
            assertEquals( expectedData.get( "status" ), responseJP.get("status") );
}

}