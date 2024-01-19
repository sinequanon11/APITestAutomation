package Tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C19_Get_BaseUriHerokuappPathParam extends BaseUrlHerokuapp {

    @Test
    public void test01(){

        /*
        https://restful-booker.herokuapp.com/booking endpointine bir GET request gonderdigimizde
        donen response’un
        status code’unun 200 oldugunu ve
        Response’ta 12 booking oldugunu test edin
         */

        // 1. End-point ve request body olustur

        specHerokuapp.pathParam( "pp1", "booking" );

        // 2. Expected data olustur

        // 3. Request gonder ve donen response'i kaydet

        Response response = given()
                .when().spec( specHerokuapp)
                .get("/{pp1}");

        // 4. Assertion

        JsonPath responsejsonpath = response.jsonPath();
       // System.out.println(responsejsonpath.getList( "bookingid" ).size());

        int listOfSize = responsejsonpath.getList( "bookingid" ).size();

        response.then().assertThat()
                .statusCode( 200 )
                .body( "bookingid", Matchers.hasSize( listOfSize ));
    }
}
