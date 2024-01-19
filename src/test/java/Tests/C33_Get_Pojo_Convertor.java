package Tests;

import baseUrl.BaseUrlDummyExample;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummyExampleData;
import pojos.PojoDummyExampleResponse;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C33_Get_Pojo_Convertor extends BaseUrlDummyExample {

    /*

        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
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

   @Test
    public void test01(){

       // 1. End-point ve request body olustur

       specDummyExample.pathParams( "pp1", "employee", "pp2", "3" );

       // 2. Expected data olustur

                 // https://www.jsonschema2pojo.org/    USED THIS CONVERTOR (Json to Pojo)

       PojoDummyExampleData dataPojo =
               new PojoDummyExampleData(3, "Ashton Cox", 86000, 66, "");

       PojoDummyExampleResponse expectedResponseBody =
               new PojoDummyExampleResponse("success", dataPojo, "Successfully! Record has been fetched." );

       // 3. Request gonder ve donen response'i kaydet

      Response response = given().spec( specDummyExample )
              .when()
              .get("{pp1}/{pp2}");

       // 4. Assertion  --    expectedResponseBodyPojo <===> (Pojo)

       // Convertor'ler attribute isimlerini değiştirdiğinden (employee_name , employeeName) bu durumda teste devam etmek
       // için reponse'ı JsonPath'a dönüştürebiliriz. En iyiyi pojos package içindeki pojo class'larını manual yapmak

       JsonPath responseJP = response.jsonPath();

       assertEquals( expectedResponseBody.getMessage(), responseJP.getString( "message" ) );
       assertEquals( expectedResponseBody.getStatus(), responseJP.getString( "status" ) );
       assertEquals( expectedResponseBody.getData().getId(), responseJP.get( "data.id" ) );
       assertEquals( expectedResponseBody.getData().getEmployeeName(), responseJP.get("data.employee_name") );
       assertEquals( expectedResponseBody.getData().getEmployeeAge(), responseJP.get("data.employee_age") );
      assertEquals( expectedResponseBody.getData().getEmployeeSalary(), responseJP.get("data.employee_salary") );
      assertEquals( expectedResponseBody.getData().getProfileImage(), responseJP.get("data.profile_image") );

   }
}
