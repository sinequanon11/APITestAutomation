package Tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C21_Post_BaseUriHerokuappQueryParam extends BaseUrlHerokuapp {

    @Test
    public void test01(){

        /*
        https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini
        yazarak “firstname” degeri “Jim” olan rezervasyon oldugunu test edecek bir GET
        request gonderdigimizde, donen response’un status code’unun 200 oldugunu ve “Jim”
        ismine sahip 16 booking oldugunu test edin
         */

        // 1. End-point ve request body olustur

        specHerokuapp
                .pathParam( "pp1", "booking" )
                .queryParam( "firstname", "Jim" );

        // 2. Expected data olustur


        // 3. Request gonder ve donen response'i kaydet

        Response response = given()
                .when().spec( specHerokuapp )
                .get("/{pp1}");          // Buraya sadee pathParam yeterli, queryParam yazma

        // 4. Assertion

        response.then().assertThat()
                .statusCode( 200 )
                .body( "bookingid", Matchers.hasSize( 16) );
    }
}
