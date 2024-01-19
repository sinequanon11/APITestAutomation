package Tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C17_BaseUriJsonPlaceholder extends BaseUrlJsonPlaceholder {

    @Test
    public void test01(){

        // 1. End-point ve request body olustur

        specJsonPlaceholder.pathParams( "pp1", "posts" );

        // 2. Expected data olustur

        // 3. Request gonder ve donen response'i kaydet

        Response response = given()
                .when().spec( specJsonPlaceholder )
                .get("/{pp1}");
        response
                .then()
                .assertThat()
                .body( "title", Matchers.hasSize( 100 ) );
    }

    @Test
    public void test02(){

        // 1. End-point ve request body olustur

        specJsonPlaceholder.pathParams( "pp1", "posts", "pp2", 44 );

        // 2. Expected data olustur

        // 3. Request gonder ve donen response'i kaydet

        Response response = given()
                .when().spec( specJsonPlaceholder )
                .get ("/{pp1}/{pp2}");

        response
                .then()
                .assertThat()
                .statusCode( 200 )
                .body("title", Matchers.equalTo( "optio dolor molestias sit" ));

    }
}
