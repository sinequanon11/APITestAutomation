package Tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C18_BaseUriJsonPlaceholder2  extends BaseUrlJsonPlaceholder{

    @Test
    public void test01(){

        /*
        https://jsonplaceholder.typicode.com/posts/50
        delete request
        status code 200
        response body null
         */

        // 1. End-point ve request body olustur

        specJsonPlaceholder.pathParams( "pp1", "posts", "pp2", 50 );

        // 2. Expected data olustur

        // 3. Request gonder ve donen response'i kaydet

        Response response = given()
                .when().spec( specJsonPlaceholder )
                .delete("/{pp1}/{pp2}");

        // 4. Assertion

        response.then().assertThat()
                .statusCode( 200 )
                .body( "title", Matchers.nullValue() );
    }
}
