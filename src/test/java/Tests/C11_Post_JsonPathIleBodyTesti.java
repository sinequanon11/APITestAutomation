package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class C11_Post_JsonPathIleBodyTesti {
    @Test
    public void test01() {

        /*

        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
        gonderdigimizde
        {
            "firstname" : "Ahmet",
            "lastname" : “Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2023-01-10",
                "checkout" : "2023-01-20"
            },
            "additionalneeds" : "wi-fi"
        }
         */

        // 1.End-point ve request body olustur

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();
        JSONObject rezervasyonTarihleriJson = new JSONObject();

        rezervasyonTarihleriJson.put( "checkin", "2023-01-10" );
        rezervasyonTarihleriJson.put( "checkout", "2023-01-20" );

        requestBody.put( "firstname", "Ahmet" );
        requestBody.put( "lastname", "Bulut" );
        requestBody.put( "totalprice", 500 );
        requestBody.put( "depositpaid", false );
        requestBody.put( "bookingdates", rezervasyonTarihleriJson );
        requestBody.put( "additionalneeds", "wi-fi" );

        // 2. Expected data olusturma

        // 3. Request gonder ve donen response'i kaydet

        Response response = given().contentType( ContentType.JSON )
                .when().body( requestBody.toString() )
                .post( url );

        // response.prettyPrint();

        // 4. Assertion

        response.then().assertThat()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( "booking.firstname", equalTo( "Ahmet" ),     // * USING JSON PATH HERE
                        "booking.lastname", equalTo( "Bulut" ),
                        "booking.totalprice", equalTo( 500 ),
                        "booking.depositpaid", equalTo( false ),
                        "booking.bookingdates.checkin", equalTo( "2023-01-10" ),
                        "booking.bookingdates.checkout", equalTo( "2023-01-20" )
                );
    }
}
