package Tests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_Get_ResponseDegerleriniAutomationIleTestEtme {

    @Test
    public void test01() {

         /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
        gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        olarak test ediniz.
        */

        // 1.End-point ve request body olustur

        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2.Expected body'yi olustur

        // 3.Request gonderip donen response'i kaydet

        Response response = given().when().get( url );

        // 4.Assertion

        response
                .then()
                .assertThat()
                .statusCode( 200 )
                .contentType( "application/json; charset=utf-8" )
                .header( "Server", "Cowboy" )
                .statusLine( "HTTP/1.1 200 OK" );

    }
}
