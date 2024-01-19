package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C09_Get_TestYaparkenTekrarlardanKurtulma {
    @Test
    public void test01(){

        /*

        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde
        donen Response’un,
                status code’unun 200,
                ve content type’inin application-json,
                ve response body’sindeki
                "firstname“in, "xxx",
                ve "lastname“in, "xxxx",
                ve "totalprice“in, xxxx,
                ve "depositpaid“in, xxxxxxx,
                ve "additionalneeds“in, ""  null
        oldugunu test edin
         */

        // 1.End-point ve request body hazirla

        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2.Expected data olustur

        // 3. Request gonderip donen response'i kaydet

        Response response = given().when().get(url);

        // 4. Assertion

       // response.prettyPrint();

        /* 1. YONTEM

        response
                .then()
                .assertThat()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( "firstname", Matchers.equalTo( "Jim" ) )
                .body( "lastname", Matchers.equalTo( "Jones" ) )
                .body( "totalprice", Matchers.lessThan( 1000 ) )
                .body( "depositpaid", Matchers.equalTo( true ))
                .body( "additionalneeds" ,Matchers.nullValue() );
         */

        // 2. YONTEM / SADE

        response
                .then()
                .assertThat()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( "firstname", equalTo("Mark"),
                        "lastname",equalTo( "Jones" ),
                       "totalprice", lessThan( 1000 ),
                        "depositpaid", equalTo( true ),
                        "additionalneeds", nullValue()
                );

    }
}