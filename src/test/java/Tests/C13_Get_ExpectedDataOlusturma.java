package Tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;


public class C13_Get_ExpectedDataOlusturma {

    @Test
    public void test01(){

        /*
            https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request
            yolladigimizda donen response body’sinin asagida verilen ile ayni oldugunu test ediniz

            Response body :
                    {
                    "userId": 3,
                    "id": 22,
                    "title": "dolor sint quo a velit explicabo quia nam",
                    "body": "eos qui et ipsum ipsam suscipit autsed omnis non odioexpedita ear
                    um mollitia molestiae aut atque rem suscipitnam impedit esse"
            }
         */

        // 1. End-point ve request body hazirla

        String url = "https://jsonplaceholder.typicode.com/posts/22";

        // 2. Expected data olustur

        JSONObject expectedData = new JSONObject();

        expectedData.put( "userId", 3 );
        expectedData.put( "id", 22 );
        expectedData.put( "title", "dolor sint quo a velit explicabo quia nam" );
        expectedData.put("body", "eos qui et ipsum ipsam suscipit autsed omnis non odioexpedita earum mollitia molestiae aut atque rem suscipitnam impedit esse");

        // 3. Request gonder ve donen response'i kaydet

        Response response = given().when().get(url);
        // response.prettyPrint();

        // 4. Assertion

        /* Bugune kadar yaptigimiz yontemle de tesimizi yyapabiliriz. Ancak framework'umuzu gelistirmeliyiz
        response
                .then()
                .assertThat()
                .body( "userId", equalTo( 3 ),
                        "id", equalTo( 22 ),
                        "title", equalTo( "dolor sint quo a velit explicabo quia nam" ));
        */

        JsonPath responseJsonPath = response.jsonPath();

        Assert.assertEquals( expectedData.get("id"), responseJsonPath.get("id") );
        Assert.assertEquals( expectedData.get("title"), responseJsonPath.get("title") );
        Assert.assertEquals( expectedData.get("userId"), responseJsonPath.get("userId") );

    }
}
