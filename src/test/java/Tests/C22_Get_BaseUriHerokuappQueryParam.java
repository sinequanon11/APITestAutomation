package Tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C22_Get_BaseUriHerokuappQueryParam extends BaseUrlHerokuapp {

    @Test
    public void test01(){

        /*
         https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini
        yazarak “firstname” degeri “Jim” ve “lastname” degeri “Jackson” olan rezervasyon
        oldugunu test edecek bir GET request gonderdigimizde, donen response’un status
        code’unun 200 oldugunu ve “Jim Jackson” ismine sahip en az bir booking oldugunu test
        edin
         */

        // 1. End-point ve request body olustur

        specHerokuapp
                .pathParam( "pp1", "booking" )
                .queryParams( "firstname", "Jim", "lastname", "Jones" );

        // 2. Expected data olustur

        // 3. Request gonder ve donen response'i kaydet

        Response response = given()
                            .when().spec( specHerokuapp )
                            .get("/{pp1}");         // Buraya sadee pathParam yeterli, queryParam yazma

        // 4. Assertion

        response.then().assertThat()
                .statusCode( 200 )
                .body( "bookingid", Matchers.hasSize( 1 ) );
    }
}
