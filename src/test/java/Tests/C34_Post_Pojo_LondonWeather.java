package Tests;

import baseUrl.BaseUrlDummyExample;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PojoDummyExampleData;
import pojos.PojoDummyExampleResponse;
import pojos.pojosHavaDurumu.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C34_Post_Pojo_LondonWeather  {

    /*

    https://api.openweathermap.org/data/2.5/weather?q=London&a
    ppid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0 url’ine
    bir post request gonderdigimizde donen response’un asagidaki
    body’ye sahip oldugunu test ediniz

    {
    "coord": {
        "lon": -0.1257,
        "lat": 51.5085
    },
    "weather": [
        {
            "id": 804,
            "main": "Clouds",
            "description": "overcast clouds",
            "icon": "04n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 287.1,
        "feels_like": 286.9,
        "temp_min": 285.37,
        "temp_max": 288.99,
        "pressure": 1007,
        "humidity": 90
    },
    "visibility": 10000,
    "wind": {
        "speed": 2.06,
        "deg": 200
    },
    "clouds": {
        "all": 100
    },
    "dt": 1693079941,
    "sys": {
        "type": 2,
        "id": 2075535,
        "country": "GB",
        "sunrise": 1693026182,
        "sunset": 1693076536
    },
    "timezone": 3600,
    "id": 2643743,
    "name": "London",
    "cod": 200
}

     */

   @Test
    public void test01(){

       // 1. End-point ve request body olustur

       String url = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0";

       // 2. Expected data olustur

       Coord coordpojo =
               new Coord(-0.1257f,
                       51.5085f);

       Weather weatherPojo = new Weather(804, "Clouds", "overcast clouds", "04n");
       List<Weather> weatherList = new ArrayList<>();
       weatherList.add( weatherPojo );

       Main mainPojo =
               new Main(287.1f,
                       286.9f,
                       285.37f, 288.99f,
                       1007,
                       90 );

       Wind windPojo =
               new Wind(2.06f,
                       200);

       Clouds cloudsPojo = new Clouds(100);

       Sys sysPojo =
               new Sys(2,
                       2075535 ,
                       "GB" ,
                       1693026182 ,
                       1693076536);

       PojoHavaDurumu expectedResponseBody =
               new PojoHavaDurumu(coordpojo,
                       weatherList,"base",
                       mainPojo, 10000, windPojo,
                       cloudsPojo,
                       1693079941,
                       sysPojo,3600,
                       2643743, "London",
                       200);

       // 3. Request gonder ve donen response'i kaydet

       Response response = given().when().post(url);

       // 4. Assertion  --    expectedResponseBody Pojo <===> response

       PojoHavaDurumu responsePojo = response.as( PojoHavaDurumu.class );
       // response'i Pojo'ya cevirdigimizde tum bilgileri getirirse,
       // responsePojo'yu assertion'da kullanabiliriz
       // Eger null deger donerse, response'i JsonPath yapip assertion'da kullanabiliriz
       // NULL DONDU - alt cizgi olan attribute'larda sorun oluyoe Convertor'da, o yuzden JsonPath kullanmak lazim

       JsonPath responseJP = response.jsonPath();
       // expectedResponseBody Pojo <==> responseJP

       assertEquals( expectedResponseBody.getCoord().getLon(), responseJP.get("coord.lon") );

       assertEquals( expectedResponseBody.getCoord().getLat(), responseJP.get( "coord.lat" ) );

       assertEquals( expectedResponseBody.getSys().getCountry(), responseJP.get("sys.country") );

       assertEquals( expectedResponseBody.getName(), responseJP.get("name") );

   }
}
